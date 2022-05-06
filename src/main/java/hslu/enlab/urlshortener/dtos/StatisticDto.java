package hslu.enlab.urlshortener.dtos;

import java.time.OffsetDateTime;

public record StatisticDto (long totalNumberOfCalls, long averageForwardDurationInMillis, OffsetDateTime timeOfLastCall) {}
