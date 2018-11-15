package com.monitoring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monitoring.dto.Environment;

public interface EnvironmentRepository extends MongoRepository<Environment, String> {

	public Environment findByName(String name);

}
