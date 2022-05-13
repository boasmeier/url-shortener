package hslu.enlab.urlshortener.services;

import hslu.enlab.urlshortener.entities.Statistic;
import hslu.enlab.urlshortener.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * Code of class StatisticService.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
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

    public Statistic getOverallStatistic() {
        return aggregateStatistics(statisticRepository.findAll());
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

    private Statistic aggregateStatistics(List<Statistic> statistics) {
        Statistic aggregated = new Statistic();
        aggregated.setTotalNumberOfCalls(getTotalNumberOfCalls(statistics));
        aggregated.setAverageForwardDurationInMillis(getAverageForwardDuration(statistics));

        Optional<OffsetDateTime> timeOfLastCall = getOffsetDateTime(statistics);
        aggregated.setTimeOfLastCall(timeOfLastCall.orElse(null));

        return aggregated;
    }

    private Optional<OffsetDateTime> getOffsetDateTime(List<Statistic> statistics) {
        return statistics.stream()
                .map(Statistic::getTimeOfLastCall)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder());
    }

    private long getAverageForwardDuration(List<Statistic> statistics) {
        long totalForwardDuration = statistics.stream()
                .map(Statistic::getAverageForwardDurationInMillis)
                .reduce(0L, Long::sum);

        return statistics.isEmpty() ? totalForwardDuration : totalForwardDuration / statistics.size();
    }

    private long getTotalNumberOfCalls(List<Statistic> statistics) {
        return statistics.stream()
                .map(Statistic::getTotalNumberOfCalls)
                .reduce(0L, Long::sum);
    }
}
