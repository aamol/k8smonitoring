package com.monitoring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monitoring.dto.DeployedApplication;
import com.monitoring.dto.Environment;

public interface DeployedApplicationsRepository extends MongoRepository <DeployedApplication, String>{
	
	public DeployedApplication findByName(String name);

}
