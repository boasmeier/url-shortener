package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.dtos.ShortUrlDto;
import hslu.enlab.urlshortener.dtos.StatisticDto;
import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.entities.Statistic;
import hslu.enlab.urlshortener.mappers.ShortUrlMapper;
import hslu.enlab.urlshortener.mappers.StatisticMapper;
import hslu.enlab.urlshortener.services.ShortUrlService;
import hslu.enlab.urlshortener.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/shorturls")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    private final StatisticService statisticService;

    private final ShortUrlMapper shortUrlMapper;

    private final StatisticMapper statisticMapper;

    @Autowired
    ShortUrlController(ShortUrlService shortUrlService,
                       ShortUrlMapper shortUrlMapper,
                       StatisticService statisticService,
                       StatisticMapper statisticMapper) {
        this.shortUrlService = shortUrlService;
        this.shortUrlMapper = shortUrlMapper;
        this.statisticService = statisticService;
        this.statisticMapper = statisticMapper;
    }

    @PostMapping
    public ResponseEntity<ShortUrlDto> create(@RequestBody ShortUrlDto shortUrlDto) {
        ShortUrl shortUrl = shortUrlService.create(shortUrlDto.url());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(shortUrl.getId())
                .toUri();

        return ResponseEntity.created(uri).body(shortUrlMapper.toDto(shortUrl));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ShortUrlDto> put(@PathVariable UUID id, @RequestBody ShortUrlDto shortUrlDto) {
        ShortUrl shortUrl = shortUrlService.put(shortUrlMapper.fromDto(shortUrlDto), id);

        return ResponseEntity.ok(shortUrlMapper.toDto(shortUrl));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        shortUrlService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/statistics")
    public ResponseEntity<StatisticDto> getStatistic(@PathVariable UUID id) {
        Statistic statistic = statisticService.findByShortUrlId(id);

        return ResponseEntity.ok(statisticMapper.toDto(statistic));
    }

}
