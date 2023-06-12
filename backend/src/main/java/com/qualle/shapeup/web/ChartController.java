package com.qualle.shapeup.web;

import com.qualle.shapeup.api.ChartDto;
import com.qualle.shapeup.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/chart/{id}")
    public List<ChartDto>  getChart(@PathVariable long id){
        return chartService.getCharts(1, List.of(id));
    }

}
