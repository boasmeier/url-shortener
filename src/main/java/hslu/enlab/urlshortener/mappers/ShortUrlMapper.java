package hslu.enlab.urlshortener.mappers;

import hslu.enlab.urlshortener.dtos.ShortUrlDto;
import hslu.enlab.urlshortener.entities.ShortUrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Code of class ShortUrlMapper.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Component
public class ShortUrlMapper {

    @Value("${redirection.timeout}")
    private Integer redirectionTimeout;

    public ShortUrlDto toDto(ShortUrl shortUrl) {
        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getShortUrl(),
                shortUrl.getUrl(),
                redirectionTimeout
        );

    }

    public List<ShortUrlDto> toDtos(List<ShortUrl> shortUrls) {
        return shortUrls.stream().map(this::toDto).toList();
    }

    public ShortUrl fromDto(ShortUrlDto shortUrlDto) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(shortUrlDto.id());
        shortUrl.setShortUrl(shortUrlDto.shortUrl());
        shortUrl.setUrl(shortUrlDto.url());

        return shortUrl;
    }
}
