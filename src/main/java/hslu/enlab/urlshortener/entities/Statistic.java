package hslu.enlab.urlshortener.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Code of class Statistic.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Entity
public class Statistic {

    @Id
    private UUID id;

    private Long totalNumberOfCalls;

    private Long averageForwardDurationInMillis;

    private OffsetDateTime timeOfLastCall;

    @Column(name = "short_url_id")
    private UUID shortUrlId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getTotalNumberOfCalls() {
        return totalNumberOfCalls;
    }

    public void setTotalNumberOfCalls(Long totalNumberOfCalls) {
        this.totalNumberOfCalls = totalNumberOfCalls;
    }

    public Long getAverageForwardDurationInMillis() {
        return averageForwardDurationInMillis;
    }

    public void setAverageForwardDurationInMillis(Long averageForwardDurationInMillis) {
        this.averageForwardDurationInMillis = averageForwardDurationInMillis;
    }

    public OffsetDateTime getTimeOfLastCall() {
        return timeOfLastCall;
    }

    public void setTimeOfLastCall(OffsetDateTime timeOfLastCall) {
        this.timeOfLastCall = timeOfLastCall;
    }

    public UUID getShortUrlId() {
        return shortUrlId;
    }

    public void setShortUrlId(UUID shortUrlId) {
        this.shortUrlId = shortUrlId;
    }
}
