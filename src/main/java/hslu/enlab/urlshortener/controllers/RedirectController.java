package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.services.ShortUrlService;
import hslu.enlab.urlshortener.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.Instant;


@RestController
public class RedirectController {

    private final ShortUrlService shortUrlService;

    private final StatisticService statisticService;

    @Autowired
    RedirectController(ShortUrlService shortUrlService, StatisticService statisticService) {
        this.shortUrlService = shortUrlService;
        this.statisticService = statisticService;
    }

    @GetMapping(value = "/r/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        Instant start = Instant.now();
        ShortUrl url = shortUrlService.findUrl(shortUrl);

        URI uri = URI.create(url.getUrl());

        Instant end = Instant.now();
        statisticService.updateStatistic(url.getId(), start, end);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }
}
