package hslu.enlab.urlshortener.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Code of class IndexController.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
