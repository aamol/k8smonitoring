package com.monitoring.config;

import java.util.Comparator;
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
		Comparator<Environment> comparator = new Comparator<Environment>() {
			@Override
			public int compare(final Environment env1, final Environment env2) {
				// let your comparator look up your car's color in the custom order
				return Integer.valueOf(environments.indexOf(env1.getName()))
						.compareTo(Integer.valueOf(environments.indexOf(env2.getName())));
			}
		};
		environments.sort(comparator);
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
