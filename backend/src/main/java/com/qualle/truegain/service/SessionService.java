package com.qualle.truegain.service;

import com.qualle.truegain.model.entity.Session;

public interface SessionService {

    Session getById(String id);

    Session getByIdWithUser(String id);

    Session save(Session session);

    Session update(Session session);

    void deleteById(String id);
}
