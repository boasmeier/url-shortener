package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.entities.ShortUrl;
import hslu.enlab.urlshortener.mappers.ShortUrlMapper;
import hslu.enlab.urlshortener.services.ShortUrlService;
import hslu.enlab.urlshortener.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.time.Instant;

/**
 * Code of class RedirectController.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@Controller
public class RedirectController {

    private final ShortUrlService shortUrlService;

    private final StatisticService statisticService;

    private final ShortUrlMapper shortUrlMapper;

    @Autowired
    RedirectController(ShortUrlService shortUrlService, StatisticService statisticService, ShortUrlMapper shortUrlMapper) {
        this.shortUrlService = shortUrlService;
        this.statisticService = statisticService;
        this.shortUrlMapper = shortUrlMapper;
    }

    @GetMapping(value = "/r/{shortUrl}")
    public String redirect(@PathVariable String shortUrl, Model model) {
        Instant start = Instant.now();
        ShortUrl url = shortUrlService.findUrl(shortUrl);

        Instant end = Instant.now();
        statisticService.updateStatistic(url.getId(), start, end);

        model.addAttribute("shortUrl", shortUrlMapper.toDto(url));

        return "advertisement";
    }
}
