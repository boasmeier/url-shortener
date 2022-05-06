package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.repositories.ShortUrlRepository;
import hslu.enlab.urlshortener.services.ShortUrlService;
import hslu.enlab.urlshortener.services.StatisticService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;

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
    void shouldCreateUniqueShortUrl() {
        // arrange
        String url = "www.google.ch";

        Mockito.when(shortUrlRepository.existsUrlEntityByShortUrl(Mockito.any(String.class))).thenReturn(true).thenReturn(false);

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

    @Test
    void shouldDeleteShortUrl() {
        // arrange
        UUID id = UUID.randomUUID();

        // act
        testee.delete(id);

        // assert
        Mockito.verify(shortUrlRepository).deleteShortUrlById(id);
    }

    @Test
    void shouldReturnShortUrlIfShortedUrlExist() {
        // arrange
        String shortedUrl = "TEST";

        ShortUrl expectedShortUrl = new ShortUrl();
        expectedShortUrl.setId(UUID.randomUUID());
        expectedShortUrl.setUrl("https://existingurl.com");

        Mockito.when(shortUrlRepository.findUrlEntityByShortUrl(shortedUrl)).thenReturn(expectedShortUrl);

        // act
        testee.findUrl(shortedUrl);

        // assert
        Mockito.verify(shortUrlRepository).findUrlEntityByShortUrl(shortedUrl);
    }

    @Test
    void shouldThrowIfShortedUrlDontExist() {
        // arrange
        String shortedUrl = "TEST";

        // act
        RuntimeException actual = Assertions.assertThrows(RuntimeException.class, () -> testee.findUrl(shortedUrl));

        // assert
        assertThat(actual.getMessage()).isEqualTo("Link not found");
    }

    @Test
    void shouldUpdateUUID() {
        // arrange
        UUID newId = UUID.randomUUID();
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(UUID.randomUUID());
        shortUrl.setUrl("https://existingurl.com");

        // act
        testee.put(shortUrl, newId);

        // assert
        Mockito.verify(shortUrlRepository).save(argThat(s -> {
            AssertionsForClassTypes.assertThat(s.getId()).isEqualTo(newId);
            return true;
        }));
    }

}
