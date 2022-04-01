package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.dtos.UrlDto;
import hslu.enlab.urlshortener.entities.UrlEntity;
import hslu.enlab.urlshortener.mappers.UrlMapper;
import hslu.enlab.urlshortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/create")
    public ResponseEntity<UrlDto> create(@RequestBody UrlDto urlDto) {
        UrlEntity urlEntity = urlService.create(urlDto.url);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(urlEntity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(urlMapper.toDto(urlEntity));
    }

}