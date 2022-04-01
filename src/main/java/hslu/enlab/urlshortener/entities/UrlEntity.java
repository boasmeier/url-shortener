package hslu.enlab.urlshortener.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlEntity {
    @Id
    private String id;

    private String url;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
