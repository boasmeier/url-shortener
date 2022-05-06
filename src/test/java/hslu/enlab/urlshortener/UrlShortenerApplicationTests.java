package hslu.enlab.urlshortener;

import hslu.enlab.urlshortener.controllers.IndexController;
import hslu.enlab.urlshortener.controllers.RedirectController;
import hslu.enlab.urlshortener.controllers.ShortUrlController;
import hslu.enlab.urlshortener.controllers.StatisticController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UrlShortenerApplicationTests {

	@Autowired
	private IndexController indexController;

	@Test
	void contextLoads() {
		assertThat(indexController).isNotNull();
	}

}
