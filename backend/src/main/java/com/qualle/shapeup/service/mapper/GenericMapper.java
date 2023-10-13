package com.qualle.shapeup.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface GenericMapper<T, DTO> {

    DTO toDto(T t);

    T fromDto(DTO dto);

    default List<DTO> toDto(Collection<T> list) {
        List<DTO> dtos = new ArrayList<>();

        list.forEach(t -> dtos.add(toDto(t)));

        return dtos;
    }

    default List<DTO> toDto(Iterable<T> iterable) {
        List<DTO> dtos = new ArrayList<>();

        iterable.forEach(t -> dtos.add(toDto(t)));

        return dtos;
    }
}