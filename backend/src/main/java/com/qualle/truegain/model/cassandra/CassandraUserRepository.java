package com.qualle.truegain.model.cassandra;

import org.springframework.data.repository.CrudRepository;

public interface CassandraUserRepository extends CrudRepository<User, Long> {
}
