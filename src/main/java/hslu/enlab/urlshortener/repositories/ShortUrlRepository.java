package hslu.enlab.urlshortener.repositories;

import hslu.enlab.urlshortener.entities.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Code of interface ShortUrlRepository.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, UUID> {

    void deleteShortUrlById(UUID id);

    boolean existsUrlEntityByShortUrl(String shortUrl);

    ShortUrl findUrlEntityByUrl(String url);

    ShortUrl findUrlEntityByShortUrl(String shortUrl);

}
