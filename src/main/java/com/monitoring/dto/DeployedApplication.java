package com.monitoring.dto;

import javax.validation.constraints.NotNull;

public class DeployedApplication {
	@NotNull
	String host;
	@NotNull
	String port;

	@NotNull
	String name;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeployedApplication(@NotNull String host, @NotNull String port, @NotNull String name) {
		super();
		this.host = host;
		this.name = name;
		this.port = port;
	}

	@Override
	public String toString() {
		return "DeploymentApplication [host=" + host + ", name=" + name + ", port=" + port + "]";

	}

	
	}


