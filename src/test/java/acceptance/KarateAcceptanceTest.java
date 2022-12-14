/*
 * KarateTests.java
 * Created on 2022-04-08
 *
 * Copyright(c) 2022 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package acceptance;

import com.intuit.karate.junit5.Karate;
import hslu.enlab.urlshortener.UrlShortenerApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Code of class KarateTests.
 *
 * @author Boas Meier
 * @version JDK 17.0.2
 */
@SpringBootTest(classes = UrlShortenerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class KarateAcceptanceTest {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}
