package com.qualle.shapeup.service.basic;

import java.util.List;

public interface GenericService <DTO, ID> {

    List<DTO> getAll();

    DTO getById(ID id);

    void save(DTO t);

    void delete(ID id);
}
