package com.monitoring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monitoring.dto.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

}
