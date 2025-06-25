package com.luizpais.encurtator.services;

import com.luizpais.encurtator.infrastructure.QueueSender;
import com.luizpais.encurtator.model.ClickEventDTO;
import com.luizpais.encurtator.model.UrlMapping;
import com.luizpais.encurtator.model.UrlMappingDTO;
import com.luizpais.encurtator.repository.UrlRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.net.URI;
import java.security.SecureRandom;

// com.luizpais.encurtator.services.UrlShortenerService.java
@ApplicationScoped
public class UrlShortenerService {


    @Inject
    UrlRepository repository;

    @Inject
    QueueSender queueSender;

    @Transactional
    public URI handleRedirect(String shortCode, String ip, String userAgent) {
        UrlMapping mapping = repository.findByShortCode(shortCode);
        mapping.incrementClickCount();
        repository.update(mapping);

        ClickEventDTO event = new ClickEventDTO(mapping, ip, userAgent);
        queueSender.sendMessage(event);

        return URI.create(mapping.getOriginalUrl());
    }

    @Transactional
    public String shortenUrl(UrlMappingDTO originalUrl) {
        UrlMapping mapping = new UrlMapping(originalUrl);
        ;
        mapping.persist();
        // Generate short code using the entity's ID
        String shortCode = generateUniqueShortCode(10);
        mapping.setShortCode(shortCode);
        mapping.persist();

        return shortCode;
    }

    public String generateUniqueShortCode(int length) {
        String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom random = new SecureRandom();
        String shortCode;

        do {
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int idx = random.nextInt(base62Chars.length());
                sb.append(base62Chars.charAt(idx));
            }
            shortCode = sb.toString();
            // Check if the code already exists in the database
        } while (repository.findByShortCode(shortCode) != null);

        return shortCode;
    }

    private String generateShortCodev1(Long id) {
        String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shortCode = new StringBuilder();

        while (id > 0) {
            int remainder = (int) (id % 62);
            shortCode.append(base62Chars.charAt(remainder));
            id /= 62;
        }

        return shortCode.reverse().toString();
    }

    public String shortenUrlWithCampaign(UrlMappingDTO campaignUrl) {
        UrlMapping mapping = new UrlMapping(campaignUrl);
        mapping.persist();
        // Generate short code using the entity's ID
        String shortCode = generateUniqueShortCode(10);
        mapping.setShortCode(shortCode);
        mapping.setCampaignId(campaignUrl.getCampaignId());
        mapping.persist();

        queueSender.sendMessage(campaignUrl);

        return shortCode;
    }
}