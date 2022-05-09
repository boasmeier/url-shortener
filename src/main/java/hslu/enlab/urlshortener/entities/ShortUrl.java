package hslu.enlab.urlshortener.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Code of class ShortUrl.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Entity
public class ShortUrl {
    @Id
    private UUID id;

    private String shortUrl;

    private String url;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
