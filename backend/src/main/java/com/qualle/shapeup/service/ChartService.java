package com.qualle.shapeup.service;

import com.qualle.shapeup.api.ChartDto;

import java.util.List;

public interface ChartService {

    List<ChartDto> getCharts(long userId, List<Long> ids);
}
