package hslu.enlab.urlshortener.mappers;

import hslu.enlab.urlshortener.dtos.StatisticDto;
import hslu.enlab.urlshortener.entities.Statistic;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Code of class StatisticMapper.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Component
public class StatisticMapper {

    public StatisticDto toDto(Statistic statistic) {
        return new StatisticDto(
                statistic.getTotalNumberOfCalls(),
                statistic.getAverageForwardDurationInMillis(),
                statistic.getTimeOfLastCall()
        );
    }

    public List<StatisticDto> toDtos(List<Statistic> statistics) {
        return statistics
                .stream()
                .map(this::toDto)
                .toList();
    }

}
