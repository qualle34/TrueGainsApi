package com.qualle.shapeup.service.impl;

import com.qualle.shapeup.api.ChartDto;
import com.qualle.shapeup.model.entity.Exercise;
import com.qualle.shapeup.model.entity.Record;
import com.qualle.shapeup.repository.RecordRepository;
import com.qualle.shapeup.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final RecordRepository recordRepository;

    @Override
    public List<ChartDto> getCharts(long userId, List<Long> ids) {

        List<ChartDto> charts = new ArrayList<>();

        for (Long id : ids) {
            charts.add(getChart(userId, id));
        }

        return charts;
    }

    private ChartDto getChart(long userId, long id) {

        List<Record> records = recordRepository.getRecord(userId, id);
        Record firstRecord = records.get(0);
        Exercise exercise = firstRecord.getExercise();

        Map<String, Float> data = new HashMap<>();

        for (Record record : records) {
            data.put(record.getWorkout().getDate().toString(), record.getWeight());
        }

        return ChartDto.builder()
                .name(exercise.getName())
                .description(exercise.getDescription())
                .measureType("firstRecord.getMeasureType()") // todo delete
                .imageLink("/") // todo
                .data(data)
                .build();
    }
}
