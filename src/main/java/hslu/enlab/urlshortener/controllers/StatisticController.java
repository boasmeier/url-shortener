package hslu.enlab.urlshortener.controllers;

import hslu.enlab.urlshortener.dtos.StatisticDto;
import hslu.enlab.urlshortener.entities.Statistic;
import hslu.enlab.urlshortener.mappers.StatisticMapper;
import hslu.enlab.urlshortener.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Code of class StatisticController.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
@RestController
@RequestMapping(value = "/v1/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    private final StatisticMapper statisticMapper;

    @Autowired
    StatisticController(StatisticService statisticService, StatisticMapper statisticMapper) {
        this.statisticService = statisticService;
        this.statisticMapper = statisticMapper;
    }

    @GetMapping
    public ResponseEntity<StatisticDto> getOverall() {
        Statistic statistic = statisticService.getOverallStatistic();

        return ResponseEntity.ok(statisticMapper.toDto(statistic));
    }

}
