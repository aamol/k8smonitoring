package com.monitoring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monitoring.dto.Deployments.Deployment;
import com.monitoring.repository.DeploymentRepository;

@Component
public class DeploymentConfig {

	@Autowired
	DeploymentRepository deploymentRepository;

	public void addDeployments(Deployment deployment) {
		deploymentRepository.save(deployment);
	}

}
