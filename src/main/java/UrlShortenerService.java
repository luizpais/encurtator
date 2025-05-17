import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.net.URI;

// UrlShortenerService.java
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
        String shortCode = generateShortCode(mapping.getId());
        mapping.setShortCode(shortCode);
        mapping.persist();

        return shortCode;
    }

    private String generateShortCode(Long id) {
        String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shortCode = new StringBuilder();

        while (id > 0) {
            int remainder = (int) (id % 62);
            shortCode.append(base62Chars.charAt(remainder));
            id /= 62;
        }

        return shortCode.reverse().toString();
    }
}