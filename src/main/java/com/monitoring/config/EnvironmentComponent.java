package com.monitoring.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monitoring.dto.Environment;
import com.monitoring.repository.EnvironmentRepository;

@Component
public class EnvironmentComponent {

	@Autowired
	EnvironmentRepository repository;

	public Environment getEnvironmentDetails(String environmentName) {

		Environment environment = repository.findByName(environmentName);

		return environment;
	}

	public List<Environment> getAllEnvironments() {

		List<Environment> environments = repository.findAll();

		return environments;
	}

	public void addUpdateEnvironmentDetails(Environment environment) {

		Environment object = repository.findByName(environment.getName());
		if (null == object) {
			object = new Environment(environment.getName(), environment.getMasterIP(), environment.getUser(),
					environment.getPassword());
		} else {
			object.setMasterIP(environment.getMasterIP());
			object.setPassword(environment.getPassword());
			object.setUser(environment.getUser());
		}
		repository.save(object);

	}

	public void deleteEnvironment(Environment environment) {
		repository.delete(environment);
	}

}
