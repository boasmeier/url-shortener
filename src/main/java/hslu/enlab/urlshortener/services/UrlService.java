package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.UrlEntity;
import hslu.enlab.urlshortener.repositories.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlEntity create(String url) {
        url = transformToValidUrl(url);
        UrlEntity existingUrlEntity = urlRepository.findUrlEntityByUrl(url);

        if (existingUrlEntity != null) {
            return existingUrlEntity;
        }

        String id = generateUniqueId();
        var urlEntity = generateUrlEntity(id, url);

        return urlRepository.save(urlEntity);
    }

    public String findUrl(String shortenedUrl) {
        UrlEntity urlEntity = urlRepository.findUrlEntityById(shortenedUrl);

        if (urlEntity == null) {
            throw new RuntimeException("Link not found");
        }

        return urlEntity.getUrl();
    }

    private String transformToValidUrl(String url) {
        if (url.startsWith("www")) {
            url = url.replace("www.", "https://");
        }

        return url;
    }

    private String generateUniqueId() {
        String id = RandomStringUtils.random(8, true, true);
        boolean exists = urlRepository.existsUrlEntityById(id);

        if (exists) {
            id = generateUniqueId();
        }

        return id;
    }

    private UrlEntity generateUrlEntity(String id, String url) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setId(id);
        urlEntity.setUrl(url);

        return urlEntity;
    }

}
