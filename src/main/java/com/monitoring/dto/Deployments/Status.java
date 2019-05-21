package com.monitoring.dto.Deployments;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties
public class Status {

	int replicas;
	int readyReplicas;
	int updatedReplicas;
	int availableReplicas;
	List<Condition> conditions;
	
	public int getReplicas() {
		return replicas;
	}
	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}
	public int getReadyReplicas() {
		return readyReplicas;
	}
	public void setReadyReplicas(int readyReplicas) {
		this.readyReplicas = readyReplicas;
	}
	public int getUpdatedReplicas() {
		return updatedReplicas;
	}
	public void setUpdatedReplicas(int updatedReplicas) {
		this.updatedReplicas = updatedReplicas;
	}
	public int getAvailableReplicas() {
		return availableReplicas;
	}
	public void setAvailableReplicas(int availableReplicas) {
		this.availableReplicas = availableReplicas;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	} 
	

}
