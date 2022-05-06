package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.entities.Statistic;
import hslu.enlab.urlshortener.repositories.ShortUrlRepository;
import hslu.enlab.urlshortener.repositories.StatisticRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;

/*
 * StatisticServiceTest.java
 * Created on 2022-05-06
 *
 * Copyright(c) 2022 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @InjectMocks
    StatisticService testee;

    @Mock
    StatisticRepository statisticRepository;

    @Test
    void shouldReturnByShortUrlId() {
        // arrange
        UUID id = UUID.randomUUID();

        // act
        testee.findByShortUrlId(id);

        // assert
        Mockito.verify(statisticRepository).findStatisticByShortUrlId(id);
    }

    @Test
    void shouldReturnAll() {
        // act
        testee.findAll();

        // assert
        Mockito.verify(statisticRepository).findAll();
    }

    @Test
    void shouldCreateStatistic() {
        // arrange
        UUID shortUrlId = UUID.randomUUID();

        // act
        testee.createStatistic(shortUrlId);

        // assert
        Mockito.verify(statisticRepository).save(argThat(s -> {
            assertThat(s.getShortUrlId()).isEqualTo(shortUrlId);
            assertThat(s.getTotalNumberOfCalls()).isEqualTo(0);
            assertThat(s.getAverageForwardDurationInMillis()).isEqualTo(0);
            assertThat(s.getTimeOfLastCall()).isNull();
            return true;
        }));
    }

    @Test
    void updateStatistic() {
        // arrange
        UUID id = UUID.randomUUID();
        UUID shortUrlId = UUID.randomUUID();
        long totalNumberOfCalls = 0;
        long averageForwardDurationInMillis = 0;

        var statistic = new Statistic();
        statistic.setId(id);
        statistic.setTotalNumberOfCalls(totalNumberOfCalls);
        statistic.setAverageForwardDurationInMillis(averageForwardDurationInMillis);
        statistic.setShortUrlId(shortUrlId);

        Mockito.when(statisticRepository.findStatisticByShortUrlId(shortUrlId)).thenReturn(statistic);

        // act
        testee.updateStatistic(shortUrlId, Instant.ofEpochMilli(100), Instant.ofEpochMilli(120));

        // assert
        Mockito.verify(statisticRepository).save(argThat(s -> {
            assertThat(s.getId()).isEqualTo(id);
            assertThat(s.getShortUrlId()).isEqualTo(shortUrlId);
            assertThat(s.getTotalNumberOfCalls()).isEqualTo(1);
            assertThat(s.getAverageForwardDurationInMillis()).isEqualTo(20);
            assertThat(s.getTimeOfLastCall()).isNotNull();
            return true;
        }));
    }
}