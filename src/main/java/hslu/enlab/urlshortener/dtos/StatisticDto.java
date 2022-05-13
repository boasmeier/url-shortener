package hslu.enlab.urlshortener.dtos;

import java.time.OffsetDateTime;

/**
 * Code of class StatisticDto.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
public record StatisticDto(long totalNumberOfCalls, long averageForwardDurationInMillis,
                           OffsetDateTime timeOfLastCall) {
}
