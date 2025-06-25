package com.luizpais.encurtator.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.Instant;

// com.luizpais.encurtator.model.UrlMapping.java (PanacheEntity)
@Entity
public class UrlMapping extends PanacheEntity {

    public String originalUrl;
    public String shortCode;

    @Column(name = "campaign_id")
    public String campaignId;
    public int clickCount;
    public Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UrlMapping(UrlMappingDTO dto) {
        this.originalUrl = dto.getOriginalUrl();
        this.shortCode = dto.getShortCode();
        this.campaignId = dto.getCampaignId();
        this.createdAt = Instant.now();
    }

    public UrlMapping() {

    }

    public void incrementClickCount() {
        this.clickCount++;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}