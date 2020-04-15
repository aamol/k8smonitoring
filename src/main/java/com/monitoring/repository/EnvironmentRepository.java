package com.monitoring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.monitoring.dto.Environment;

public interface EnvironmentRepository extends MongoRepository<Environment, String> {

	public Environment findByName(String name);
	

}
