package com.qualle.truegain.web;

import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.service.MeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasureController {

    private final MeasureService service;

    @GetMapping("/measure")
    public List<MeasureDto> getMeasures() {
        return service.getMeasures();
    }


}
