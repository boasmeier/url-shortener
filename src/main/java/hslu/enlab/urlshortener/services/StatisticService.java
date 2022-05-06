package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.Statistic;
import hslu.enlab.urlshortener.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;

    @Autowired
    StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public Statistic findByShortUrlId(UUID shortUrlId) {
        return statisticRepository.findStatisticByShortUrlId(shortUrlId);
    }

    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }

    public void createStatistic(UUID shortUrlId) {
        Statistic statistic = new Statistic();
        statistic.setId(UUID.randomUUID());
        statistic.setShortUrlId(shortUrlId);
        statistic.setAverageForwardDurationInMillis(0L);
        statistic.setTotalNumberOfCalls(0L);

        statisticRepository.save(statistic);
    }

    public void updateStatistic(UUID shortUrlId, Instant redirectStart, Instant redirectEnd) {
        Statistic statistic = statisticRepository.findStatisticByShortUrlId(shortUrlId);

        long redirectTimeMillis = redirectEnd.toEpochMilli() - redirectStart.toEpochMilli();
        long totalForwardDurationInMillis = calculateTotalForwardDuration(statistic
                        .getAverageForwardDurationInMillis(), statistic.getTotalNumberOfCalls(), redirectTimeMillis);
        long totalNumberOfCalls = statistic.getTotalNumberOfCalls() + 1;
        long averageForwardDurationInMillis = totalForwardDurationInMillis / totalNumberOfCalls;

        statistic.setTotalNumberOfCalls(totalNumberOfCalls);
        statistic.setAverageForwardDurationInMillis(averageForwardDurationInMillis);
        statistic.setTimeOfLastCall(OffsetDateTime.now());

        statisticRepository.save(statistic);
    }

    private long calculateTotalForwardDuration(long averageForwardDurationInMillis, long totalNumberOfCalls, long redirectTimeMillis) {
        return averageForwardDurationInMillis * totalNumberOfCalls + redirectTimeMillis;
    }

}
