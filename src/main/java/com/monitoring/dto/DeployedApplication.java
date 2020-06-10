package com.monitoring.dto;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deployedapplication")
public class DeployedApplication {
	
	@Id
	String id;
	
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
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DeployedApplication(@NotNull String host, @NotNull String port, @NotNull String name) {
		super();
		this.host = host;
		this.name = name;
		this.port = port;
	}

	@Override
	public String toString() {
		return "DeployedApplication [id=" + id + ", host=" + host + ", port=" + port + ", name=" + name + "]";
	}
	
}
