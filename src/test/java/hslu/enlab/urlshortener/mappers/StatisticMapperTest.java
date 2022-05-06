package hslu.enlab.urlshortener.mappers;

import hslu.enlab.urlshortener.dtos.ShortUrlDto;
import hslu.enlab.urlshortener.dtos.StatisticDto;
import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.entities.Statistic;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
 * StatisticMapperTest.java
 * Created on 2022-05-06
 *
 * Copyright(c) 2022 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */class StatisticMapperTest {

    @Test
    void shouldMapToDto() {
        // arrange
        StatisticMapper statisticMapper = new StatisticMapper();

        UUID id = UUID.randomUUID();
        long totalNumberOfCalls = 6;
        long averageForwardDurationInMillis = 10;
        OffsetDateTime timeOfLastCall = OffsetDateTime.now();
        UUID shortUrlId = UUID.randomUUID();

        var statistic = new Statistic();
        statistic.setId(id);
        statistic.setTotalNumberOfCalls(totalNumberOfCalls);
        statistic.setAverageForwardDurationInMillis(averageForwardDurationInMillis);
        statistic.setTimeOfLastCall(timeOfLastCall);
        statistic.setShortUrlId(shortUrlId);

        // act
        StatisticDto result = statisticMapper.toDto(statistic);

        // assert
        assertEquals(averageForwardDurationInMillis, result.averageForwardDurationInMillis());
        assertEquals(totalNumberOfCalls, result.totalNumberOfCalls());
        assertEquals(timeOfLastCall, result.timeOfLastCall());
    }

    @Test
    void shouldReturnEmptyListOfStatisticDto() {
        // arrange
        StatisticMapper statisticMapper = new StatisticMapper();

        // act
        List<StatisticDto> actual = statisticMapper.toDtos(Collections.emptyList());

        // assert
        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnListOfStatisticDto() {
        // arrange
        StatisticMapper statisticMapper = new StatisticMapper();

        var statistic1 = new Statistic();
        statistic1.setTotalNumberOfCalls(0l);
        statistic1.setAverageForwardDurationInMillis(1l);

        var statistic2 = new Statistic();
        statistic2.setTotalNumberOfCalls(0l);
        statistic2.setAverageForwardDurationInMillis(1l);

        var statistic3 = new Statistic();
        statistic3.setTotalNumberOfCalls(0l);
        statistic3.setAverageForwardDurationInMillis(1l);

        List<Statistic> statistics = List.of(statistic1, statistic2, statistic3);

        // act
        List<StatisticDto> actual = statisticMapper.toDtos(statistics);

        // assert
        assertThat(actual.size()).isEqualTo(3);
    }
}