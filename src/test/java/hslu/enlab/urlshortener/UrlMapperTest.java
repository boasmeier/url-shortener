package hslu.enlab.urlshortener;

import hslu.enlab.urlshortener.dtos.UrlDto;
import hslu.enlab.urlshortener.entities.UrlEntity;
import hslu.enlab.urlshortener.mappers.UrlMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UrlMapperTest {

    @Test
    void shouldMapToDto() {
        // arrange
        UrlMapper urlMapper = new UrlMapper();

        String id = "abcd99asd";
        String url = "https://foo.ch";

        var urlEntity = new UrlEntity();
        urlEntity.setId(id);
        urlEntity.setUrl(url);

        // act
        UrlDto result = urlMapper.toDto(urlEntity);

        // assert
        assertEquals(id, result.shortenedUrl());
        assertEquals(url, result.url());
    }

    @Test
    void shouldMapFromDto() {
        // arrange
        UrlMapper urlMapper = new UrlMapper();

        String id = "abcd99asd";
        String url = "https://foo.ch";

        var dto = new UrlDto(url, id);

        // act
        UrlEntity result = urlMapper.fromDto(dto);

        // assert
        assertEquals(id, result.getId());
        assertEquals(url, result.getUrl());
    }

}
