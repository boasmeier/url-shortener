package hslu.enlab.urlshortener.mappers;

import hslu.enlab.urlshortener.dtos.ShortUrlDto;
import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.mappers.ShortUrlMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class ShortUrlMapperTest {

    @Test
    void shouldMapToDto() {
        // arrange
        ShortUrlMapper shortUrlMapper = new ShortUrlMapper();

        UUID id = UUID.randomUUID();
        String shortenedUrl = "abcd99asd";
        String url = "https://foo.ch";

        var shortUrl = new ShortUrl();
        shortUrl.setId(id);
        shortUrl.setShortUrl(shortenedUrl);
        shortUrl.setUrl(url);

        // act
        ShortUrlDto result = shortUrlMapper.toDto(shortUrl);

        // assert
        assertEquals(id, result.id());
        assertEquals(shortenedUrl, result.shortUrl());
        assertEquals(url, result.url());
    }

    @Test
    void shouldMapFromDto() {
        // arrange
        ShortUrlMapper shortUrlMapper = new ShortUrlMapper();

        UUID id = UUID.randomUUID();
        String shortenedUrl = "abcd99asd";
        String url = "https://foo.ch";

        var dto = new ShortUrlDto(id, shortenedUrl, url);

        // act
        ShortUrl result = shortUrlMapper.fromDto(dto);

        // assert
        assertEquals(id, result.getId());
        assertEquals(shortenedUrl, result.getShortUrl());
        assertEquals(url, result.getUrl());
    }

}
