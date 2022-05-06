package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.repositories.ShortUrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    private final StatisticService statisticService;

    @Autowired
    ShortUrlService(ShortUrlRepository shortUrlRepository, StatisticService statisticService) {
        this.shortUrlRepository = shortUrlRepository;
        this.statisticService = statisticService;
    }

    public ShortUrl create(String url) {
        url = transformToValidUrl(url);
        ShortUrl existingShortUrl = shortUrlRepository.findUrlEntityByUrl(url);

        if (existingShortUrl != null) {
            return existingShortUrl;
        }

        String shortUrl = generateUniqueShortUrl();
        var urlEntity = generateUrlEntity(shortUrl, url);

        shortUrlRepository.save(urlEntity);

        createDefaultStatistic(urlEntity.getId());

        return urlEntity;
    }

    public ShortUrl put(ShortUrl shortUrl, UUID id) {
        shortUrl.setId(id);

        return shortUrlRepository.save(shortUrl);
    }

    public void delete(UUID id) {
        shortUrlRepository.deleteShortUrlById(id);
    }

    public ShortUrl findUrl(String shortenedUrl) {
        ShortUrl shortUrl = shortUrlRepository.findUrlEntityByShortUrl(shortenedUrl);

        if (shortUrl == null) {
            throw new RuntimeException("Link not found");
        }

        return shortUrl;
    }

    private String transformToValidUrl(String url) {
        if (!url.startsWith("http")) {
            url = String.format("https://%s", url);
        }

        return url;
    }

    private String generateUniqueShortUrl() {
        String shortUrl = RandomStringUtils.random(8, true, true);
        boolean exists = shortUrlRepository.existsUrlEntityByShortUrl(shortUrl);

        if (exists) {
            shortUrl = generateUniqueShortUrl();
        }

        return shortUrl;
    }

    private ShortUrl generateUrlEntity(String shortenedUrl, String url) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(UUID.randomUUID());
        shortUrl.setShortUrl(shortenedUrl);
        shortUrl.setUrl(url);

        return shortUrl;
    }

    private void createDefaultStatistic(UUID id) {
        statisticService.createStatistic(id);
    }

}
