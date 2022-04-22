package hslu.enlab.urlshortener.mappers;

import hslu.enlab.urlshortener.dtos.UrlDto;
import hslu.enlab.urlshortener.entities.UrlEntity;
import org.springframework.stereotype.Component;


@Component
public class UrlMapper {

    public UrlDto toDto(UrlEntity urlEntity) {
        return new UrlDto(
                urlEntity.getUrl(),
                urlEntity.getId()
        );

    }

    public UrlEntity fromDto(UrlDto urlDto) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setId(urlDto.shortenedUrl());
        urlEntity.setUrl(urlDto.url());

        return urlEntity;
    }
}
