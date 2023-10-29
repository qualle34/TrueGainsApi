package com.qualle.truegain.service.mapper;

import com.qualle.truegain.model.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface GenericMapper<T, DTO> {

    DTO toDto(T t, List<String> params);

    default DTO toDto(T t) {
        return toDto(t, Collections.emptyList());
    }

    default List<DTO> toDto(Collection<T> list, List<String> params) {
        validate(list);
        List<DTO> dtos = new ArrayList<>();

        list.forEach(t -> dtos.add(toDto(t, params)));

        return dtos;
    }

    default List<DTO> toDto(Collection<T> list) {
        return toDto(list, Collections.emptyList());
    }

    default List<DTO> toDto(Iterable<T> iterable, List<String> params) {
        validate(iterable);
        List<DTO> dtos = new ArrayList<>();

        iterable.forEach(t -> dtos.add(toDto(t, params)));

        return dtos;
    }

    default List<DTO> toDto(Iterable<T> iterable) {
        return toDto(iterable, Collections.emptyList());
    }

    default T fromDto(DTO dto) {
        return fromDto(dto, new ArrayList<>());
    }

    T fromDto(DTO dto, List<String> params);


    default void validate(Object o) {
        if (o == null) {
            throw new EntityNotFoundException("Unable to parse entity. Entity is null");
        }
    }
}