package hslu.enlab.urlshortener.repositories;

import hslu.enlab.urlshortener.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {

    boolean existsUrlEntityById(String id);

    UrlEntity findUrlEntityByUrl(String url);

}
