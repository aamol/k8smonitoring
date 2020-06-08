package com.monitoring.config;

import java.util.Comparator;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.monitoring.dto.DeployedApplication;
import com.monitoring.dto.Environment;
import com.monitoring.repository.DeploymentApplicationRepository;

@Component
public class DeploymentApplicationComponent {
	
	@Autowired
	DeploymentApplicationRepository repository;
	private DeployedApplication deployment;
	
	
	

	public DeployedApplication getDeploymentApplicationDetails(String deploymentapplicationName) {

		DeployedApplication deploymentapplication = repository.findByName(deploymentapplicationName);

		return deploymentapplication;
	}

	public List<DeployedApplication> getAllDeploymentApplication() {

		List<DeployedApplication> deploymentApplications = repository.findAll();
		Comparator<DeployedApplication> comparator = new Comparator<DeployedApplication>() {
			@Override
			public int compare(final DeployedApplication deployedApplication1, final DeployedApplication deployedApplication2) {
				// let your comparator look up your car's color in the custom order
				return Integer.valueOf(deploymentApplications.indexOf(deployedApplication1.getName()))
						.compareTo(Integer.valueOf(deploymentApplications.indexOf(deployedApplication2.getName())));
			}
		};
		deploymentApplications.sort(comparator);
		return deploymentApplications;
	}
		
	public void addUpdateDeploymentApplicationDetails( DeployedApplication deploymentapplication) {
	

		
		DeployedApplication object = repository.findByName(deployment.getName());
		if (null == object) {
			
			
			object = new DeployedApplication(deployment.getHost(), deployment.getPort(), deployment.getName());
					
		} else {
		
			object.setHost(deployment.getHost());
			object.setName(deployment.getName());
			object.setPort(deployment.getPort());
			
		}
		repository.save(object);

	}

	
	

	public void deleteDeploymentApplication(DeployedApplication deploymentapplication) {
		repository.delete(deploymentapplication);
	}

	
	}

	





