package com.qualle.truegain.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface GenericMapper<T, DTO> {

    DTO toDto(T t);

    T fromDto(DTO dto);

    default List<DTO> toDto(Collection<T> list) {
        validate(list);
        List<DTO> dtos = new ArrayList<>();

        list.forEach(t -> dtos.add(toDto(t)));

        return dtos;
    }

    default List<DTO> toDto(Iterable<T> iterable) {
        validate(iterable);
        List<DTO> dtos = new ArrayList<>();

        iterable.forEach(t -> dtos.add(toDto(t)));

        return dtos;
    }

    default void validate(Object o) {
        if (o == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }
    }
}