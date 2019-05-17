package com.monitoring.dto.Deployments.Templates;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.monitoring.dto.Deployments.Container;

@JsonIgnoreProperties
public class Spec {

	List<Container> containers;

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

}
