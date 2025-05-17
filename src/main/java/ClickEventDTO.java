import java.time.Instant;

// ClickEventDTO.java
public class ClickEventDTO {
    public String shortCode;
    public String campaignId;
    public String timestamp;
    public String ip;
    public String userAgent;

    public ClickEventDTO(UrlMapping mapping, String ip, String userAgent) {
        this.shortCode = mapping.shortCode;
        this.campaignId = mapping.campaignId;
        this.timestamp = Instant.now().toString();
        this.ip = ip;
        this.userAgent = userAgent;
    }
}