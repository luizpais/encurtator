package com.luizpais.encurtator.services;

import com.luizpais.encurtator.infrastructure.CampainerClient;
import com.luizpais.encurtator.infrastructure.KafkaSender;
import com.luizpais.encurtator.infrastructure.QueueSender;
import com.luizpais.encurtator.model.ClickEventDTO;
import com.luizpais.encurtator.model.UrlMapping;
import com.luizpais.encurtator.model.UrlMappingDTO;
import com.luizpais.encurtator.repository.UrlRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;
import java.security.SecureRandom;
import org.jboss.logging.Logger;

// com.luizpais.encurtator.services.UrlShortenerService.java
@ApplicationScoped
public class UrlShortenerService {

    private static final Logger LOG = Logger.getLogger(UrlShortenerService.class);

    @Inject
    UrlRepository repository;

    @Inject
    KafkaSender queueSender;

    @RestClient
    CampainerClient campainerClient;

    @Transactional
    public URI handleRedirect(String shortCode, String ip, String userAgent) {
        UrlMapping mapping = repository.findByShortCode(shortCode);
        mapping.incrementClickCount();
        repository.update(mapping);

        ClickEventDTO event = new ClickEventDTO(mapping, ip, userAgent);
        queueSender.sendMessage(event);
        LOG.infof("Redirecting to original URL: %s", mapping.getOriginalUrl());
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

    @Transactional
    public String shortenUrlWithCampaign(UrlMappingDTO campaignUrl) {

        // Check if the campaign exists
        if (campaignUrl.getCampaignId() == null || campainerClient.getById(campaignUrl.getCampaignId()) == null) {
            throw new IllegalArgumentException("Invalid campaign ID: " + campaignUrl.getCampaignId());
        }

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