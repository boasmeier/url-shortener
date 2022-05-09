package hslu.enlab.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Code of class UrlShortenerApplication.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@SpringBootApplication
public class UrlShortenerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UrlShortenerApplication.class);
	}

}
