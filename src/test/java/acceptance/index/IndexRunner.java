/*
 * IndexRunner.java
 * Created on 2022-05-06
 *
 * Copyright(c) 2022 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package acceptance.index;

import com.intuit.karate.junit5.Karate;

/**
 * Code of class IndexRunner.
 *
 * @author Boas Meier
 * @version JDK 17.0.2
 */
public class IndexRunner {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}
