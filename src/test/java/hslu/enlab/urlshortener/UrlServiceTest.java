package hslu.enlab.urlshortener;

import hslu.enlab.urlshortener.entities.UrlEntity;
import hslu.enlab.urlshortener.repositories.UrlRepository;
import hslu.enlab.urlshortener.services.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @InjectMocks
    UrlService testee;

    @Mock
    UrlRepository urlRepository;

    @Test
    void shouldCreateShortUrl() {
        // arrange
        String url = "https://google.ch";

        // act
        testee.create(url);

        // assert
        Mockito.verify(urlRepository).save(Mockito.any(UrlEntity.class));
    }

    @Test
    void shouldReturnExistingUrlIfExists() {
        // arrange
        String url = "https://existingurl.com";

        UrlEntity expectedUrlEntity = new UrlEntity();
        expectedUrlEntity.setId("123");
        expectedUrlEntity.setUrl(url);

        Mockito.when(urlRepository.findUrlEntityByUrl(url)).thenReturn(expectedUrlEntity);

        // act
        UrlEntity result = testee.create(url);

        // assert
        Assertions.assertEquals(expectedUrlEntity, result);
    }

    @Test
    void shouldReplaceWwwWithHttps() {
        // arrange
        String url = "www.google.ch";

        // act
        testee.create(url);

        // assert
        Mockito.verify(urlRepository).findUrlEntityByUrl("https://google.ch");
    }

}
