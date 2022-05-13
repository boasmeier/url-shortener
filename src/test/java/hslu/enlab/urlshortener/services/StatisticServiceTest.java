package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.Statistic;
import hslu.enlab.urlshortener.repositories.StatisticRepository;
import org.assertj.core.data.TemporalUnitWithinOffset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void shouldReturnCorrectAggregatedOverallStatistic() {
        // arrange
        long totalNumberOfCalls = 5;
        long averageForwardDurationInMillis = 2;
        var offsetDateTime = OffsetDateTime.now();
        var offsetDateTime2 = OffsetDateTime.now();
        offsetDateTime2.minus(10, ChronoUnit.DAYS);

        var statistic = new Statistic();
        statistic.setId(UUID.randomUUID());
        statistic.setTotalNumberOfCalls(totalNumberOfCalls);
        statistic.setAverageForwardDurationInMillis(averageForwardDurationInMillis);
        statistic.setTimeOfLastCall(offsetDateTime);
        statistic.setShortUrlId(UUID.randomUUID());

        var statistic2 = new Statistic();
        statistic2.setId(UUID.randomUUID());
        statistic2.setTotalNumberOfCalls(totalNumberOfCalls);
        statistic2.setAverageForwardDurationInMillis(averageForwardDurationInMillis);
        statistic2.setTimeOfLastCall(offsetDateTime2);
        statistic2.setShortUrlId(UUID.randomUUID());

        Mockito.when(statisticRepository.findAll()).thenReturn(List.of(statistic, statistic2));

        // act
        var actual = testee.getOverallStatistic();

        // assert
        assertThat(actual.getTotalNumberOfCalls()).isEqualTo(10);
        assertThat(actual.getAverageForwardDurationInMillis()).isEqualTo(averageForwardDurationInMillis);
        assertThat(actual.getTimeOfLastCall()).isCloseToUtcNow(new TemporalUnitWithinOffset(100L, ChronoUnit.MILLIS));
    }

    @Test
    void shouldReturnStatisticWith0Calls() {
        // arrange
        Mockito.when(statisticRepository.findAll()).thenReturn(Collections.emptyList());

        // act
        Statistic actual = testee.getOverallStatistic();

        // assert
        assertThat(actual.getTotalNumberOfCalls()).isZero();
        assertThat(actual.getAverageForwardDurationInMillis()).isZero();
        assertThat(actual.getTimeOfLastCall()).isNull();
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
            assertThat(s.getTotalNumberOfCalls()).isZero();
            assertThat(s.getAverageForwardDurationInMillis()).isZero();
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

        Statistic statistic = new Statistic();
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