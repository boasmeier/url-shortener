package hslu.enlab.urlshortener.repositories;

import hslu.enlab.urlshortener.entities.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {

    boolean existsUrlEntityByShortUrl(String shortUrl);

    ShortUrl findUrlEntityByUrl(String url);

    ShortUrl findUrlEntityByShortUrl(String shortUrl);

}
