package com.qualle.truegain.service;

import com.qualle.truegain.api.ChartDto;

import java.util.List;

public interface ChartService {

    List<ChartDto> getCharts(long userId, List<Long> ids);
}
