/*
 * UrlRunner.java
 * Created on 2022-04-08
 *
 * Copyright(c) 2022 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package acceptance.url;

import com.intuit.karate.junit5.Karate;

/**
 * Code of class UrlRunner.
 *
 * @author Boas Meier
 * @version JDK 17.0.2
 */
public class UrlRunner {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}
