package hslu.enlab.urlshortener.repositories;

import hslu.enlab.urlshortener.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Code of interface StatisticRepository.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Repository
public interface StatisticRepository extends JpaRepository<Statistic, UUID> {

    Statistic findStatisticByShortUrlId(UUID shortUrlId);

}
