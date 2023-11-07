package com.qualle.truegain.web;

import com.qualle.truegain.api.ChartDto;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/private/chart/{id}")
    public List<ChartDto> getChart(@AuthenticationPrincipal TokenSecurityDetails user, @PathVariable long id) {
        return chartService.getCharts(user.getId(), List.of(id));
    }

}
