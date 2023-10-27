package com.qualle.truegain.repository.impl;

import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.repository.WorkoutCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutCustomRepositoryImpl implements WorkoutCustomRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Workout> findRecentByUserIdWithLimit(long userId, int count) {

        return manager.createQuery("FROM Workout w INNER JOIN FETCH User u WHERE u.id = :userId ORDER BY w.date DESC")
                .setParameter("userId", userId)
                .setMaxResults(count)
                .getResultList();

    }
}
