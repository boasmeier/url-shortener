package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.dtos.UrlDto;
import hslu.enlab.urlshortener.entities.UrlEntity;
import hslu.enlab.urlshortener.mappers.UrlMapper;
import hslu.enlab.urlshortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    private final UrlMapper urlMapper;

    @Autowired
    UrlController(UrlService urlService, UrlMapper urlMapper) {
        this.urlService = urlService;
        this.urlMapper = urlMapper;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from URL-Shortener! Deploy job works! :D";
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UrlDto> create(@RequestBody UrlDto urlDto) {
        UrlEntity urlEntity = urlService.create(urlDto.url());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(urlEntity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(urlMapper.toDto(urlEntity));
    }

    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String url = urlService.findUrl(shortUrl);

        URI uri = URI.create(url);

        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }

}
