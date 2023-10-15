package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {

    @Query("SELECT r FROM Record r JOIN FETCH r.workout w JOIN FETCH r.exercise e WHERE w.user.id = :userId AND e.id  = :exerciseId")
    List<Record> getRecord(long userId, long exerciseId);
}
