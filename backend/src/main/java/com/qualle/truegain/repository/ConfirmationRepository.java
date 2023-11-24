package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Confirmation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends CrudRepository<Confirmation, Long> {

    @Modifying
    @Query("DELETE FROM Confirmation c WHERE c.userId = :userId")
    void deleteConfirmationForUser(long userId);
    @Modifying
    @Query("UPDATE Confirmation c SET c.fails = :fails WHERE c.userId = :userId")
    void updateConfirmationFailsForUser(long userId, int fails);

}
