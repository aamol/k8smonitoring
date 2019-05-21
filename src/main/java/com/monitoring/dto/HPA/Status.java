package com.monitoring.dto.HPA;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties
public class Status {

	int currentReplicas;
	int desiredReplicas;
	long currentCPUUtilizationPercentage;
	Timestamp lastScaleTime;
	
	public int getCurrentReplicas() {
		return currentReplicas;
	}
	public void setCurrentReplicas(int currentReplicas) {
		this.currentReplicas = currentReplicas;
	}
	public int getDesiredReplicas() {
		return desiredReplicas;
	}
	public void setDesiredReplicas(int desiredReplicas) {
		this.desiredReplicas = desiredReplicas;
	}
	public long getCurrentCPUUtilizationPercentage() {
		return currentCPUUtilizationPercentage;
	}
	public void setCurrentCPUUtilizationPercentage(long currentCPUUtilizationPercentage) {
		this.currentCPUUtilizationPercentage = currentCPUUtilizationPercentage;
	}
	public Timestamp getLastScaleTime() {
		return lastScaleTime;
	}
	public void setLastScaleTime(Timestamp lastScaleTime) {
		this.lastScaleTime = lastScaleTime;
	}
	
}
