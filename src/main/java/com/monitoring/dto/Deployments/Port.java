package com.monitoring.dto.Deployments;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Port {

	int containerPort;
	String name;
	String protocol;
	
	public int getContainerPort() {
		return containerPort;
	}
	public void setContainerPort(int containerPort) {
		this.containerPort = containerPort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
}
