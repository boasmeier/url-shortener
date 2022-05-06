package hslu.enlab.urlshortener;

import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.repositories.ShortUrlRepository;
import hslu.enlab.urlshortener.services.ShortUrlService;
import hslu.enlab.urlshortener.services.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ShortUrlServiceTest {

    @InjectMocks
    ShortUrlService testee;

    @Mock
    ShortUrlRepository shortUrlRepository;

    @Mock
    StatisticService statisticService;

    @Test
    void shouldCreateShortUrl() {
        // arrange
        String url = "https://google.ch";

        // act
        testee.create(url);

        // assert
        Mockito.verify(shortUrlRepository).save(Mockito.any(ShortUrl.class));
    }

    @Test
    void shouldReturnExistingUrlIfExists() {
        // arrange
        String url = "https://existingurl.com";

        ShortUrl expectedShortUrl = new ShortUrl();
        expectedShortUrl.setId(UUID.randomUUID());
        expectedShortUrl.setUrl(url);

        Mockito.when(shortUrlRepository.findUrlEntityByUrl(url)).thenReturn(expectedShortUrl);

        // act
        ShortUrl result = testee.create(url);

        // assert
        Assertions.assertEquals(expectedShortUrl, result);
    }

    @Test
    void shouldPrefixWithHttps() {
        // arrange
        String url = "www.google.ch";

        // act
        testee.create(url);

        // assert
        Mockito.verify(shortUrlRepository).findUrlEntityByUrl("https://www.google.ch");
    }

}
