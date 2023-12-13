package com.qualle.truegain.service.impl;

import com.qualle.truegain.model.entity.Session;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.repository.SessionRepository;
import com.qualle.truegain.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public Session getById(String id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session with id " + id + " not found"));
    }

    @Override
    public Session getByIdWithUser(String id) {

        return sessionRepository.findByIdWithUser(id);
    }

    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session update(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void deleteById(String id) {
        sessionRepository.deleteById(id);
    }
}
