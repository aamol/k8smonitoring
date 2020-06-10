package com.monitoring.config;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monitoring.dto.DeployedApplication;
import com.monitoring.repository.DeployedApplicationsRepository;

@Component
public class DeployedApplicationsComponent {

	@Autowired
	DeployedApplicationsRepository repository;

	public DeployedApplication getDeployedApplicationDetails(String deployedApplicationName) {

		DeployedApplication deployedApplication = repository.findByName(deployedApplicationName);

		return deployedApplication;
	}

	public List<DeployedApplication> getAllDeployedApplications() {

		List<DeployedApplication> deployedApplications = repository.findAll();
		Comparator<DeployedApplication> comparator = new Comparator<DeployedApplication>() {
			@Override
			public int compare(final DeployedApplication deployedApplication1,
					final DeployedApplication deployedApplication2) {
				return Integer.valueOf(deployedApplications.indexOf(deployedApplication1.getName()))
						.compareTo(Integer.valueOf(deployedApplications.indexOf(deployedApplication2.getName())));
			}
		};
		deployedApplications.sort(comparator);
		return deployedApplications;
	}

	public void addUpdateDeployedApplicationDetails(DeployedApplication deployedApplicationName) {

		DeployedApplication deployedApplication = repository.findByName(deployedApplicationName.getName());
		if (null == deployedApplication) {
			deployedApplication = new DeployedApplication(deployedApplicationName.getHost(), deployedApplicationName.getPort(),
					deployedApplicationName.getName());
		} else {

			deployedApplication.setHost(deployedApplicationName.getHost());
			deployedApplication.setName(deployedApplicationName.getName());
			deployedApplication.setPort(deployedApplicationName.getPort());

		}
		repository.save(deployedApplication);

	}

	public void deleteDeploymentApplication(DeployedApplication deploymentapplication) {
		repository.delete(deploymentapplication);
	}

}
