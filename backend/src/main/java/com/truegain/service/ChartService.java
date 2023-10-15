package com.truegain.service;

import com.truegain.api.ChartDto;

import java.util.List;

public interface ChartService {

    List<ChartDto> getCharts(long userId, List<Long> ids);
}
