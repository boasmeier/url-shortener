package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.services.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {

    private final ShortUrlService shortUrlService;

    @Autowired
    RedirectController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping(value = "/r/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String url = shortUrlService.findUrl(shortUrl);

        URI uri = URI.create(url);

        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }
}
