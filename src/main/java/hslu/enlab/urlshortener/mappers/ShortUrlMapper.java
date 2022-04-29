package hslu.enlab.urlshortener.mappers;

import hslu.enlab.urlshortener.dtos.ShortUrlDto;
import hslu.enlab.urlshortener.entities.ShortUrl;
import org.springframework.stereotype.Component;


@Component
public class ShortUrlMapper {

    public ShortUrlDto toDto(ShortUrl shortUrl) {
        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getShortUrl(),
                shortUrl.getUrl()
        );

    }

    public ShortUrl fromDto(ShortUrlDto shortUrlDto) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(shortUrlDto.id());
        shortUrl.setShortUrl(shortUrlDto.shortUrl());
        shortUrl.setUrl(shortUrlDto.url());

        return shortUrl;
    }
}
