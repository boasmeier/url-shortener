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

        UrlEntity existingUrlEntity = urlRepository.findUrlEntityByUrl(url);

        if (existingUrlEntity != null) {
            return existingUrlEntity;
        }

        String id = RandomStringUtils.random(8, true, true);

        boolean exists = urlRepository.existsUrlEntityById(id);

        if (exists) {
            create(url);
        }

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setId(id);
        urlEntity.setUrl(url);

        return urlRepository.save(urlEntity);
    }

}
