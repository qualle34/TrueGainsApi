package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Confirmation;
import com.qualle.truegain.model.entity.Dimension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DimensionRepository extends CrudRepository<Dimension, Long> {

    List<Dimension> findAll();


}
