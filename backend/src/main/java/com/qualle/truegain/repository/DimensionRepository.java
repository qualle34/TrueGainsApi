package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Dimension;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DimensionRepository extends CrudRepository<Dimension, Long> {

    List<Dimension> findAll();

    @Query("FROM Dimension d LEFT JOIN FETCH d.userDimensions ud WHERE d.id = :id AND ud.user.id = :userId")
    Dimension findByIdAndUser(long id, long userId);
}
