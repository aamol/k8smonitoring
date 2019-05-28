package com.monitoring.dto;

import java.io.Serializable;

public class Report implements Serializable {
	
	String envName;
	String namespace;
	String deployment;
	String startDate;
	String endDate;

	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getDeployment() {
		return deployment;
	}
	public void setDeployment(String deployment) {
		this.deployment = deployment;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Report(String envName, String namespace, String deployment, String startDate, String endDate) {
		super();
		this.envName = envName;
		this.namespace = namespace;
		this.deployment = deployment;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
