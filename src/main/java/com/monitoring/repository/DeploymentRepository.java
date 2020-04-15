package com.monitoring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monitoring.dto.Deployments.Deployment;

public interface DeploymentRepository extends MongoRepository<Deployment, String> {


}
