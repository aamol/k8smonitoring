package com.monitoring.dto.HPA;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Spec {

	ScaleTargetRef scaleTargetRef;
	int minReplicas;
	int maxReplicas;
	long targetCPUUtilizationPercentage;
	public ScaleTargetRef getScaleTargetRef() {
		return scaleTargetRef;
	}
	public void setScaleTargetRef(ScaleTargetRef scaleTargetRef) {
		this.scaleTargetRef = scaleTargetRef;
	}
	public int getMinReplicas() {
		return minReplicas;
	}
	public void setMinReplicas(int minReplicas) {
		this.minReplicas = minReplicas;
	}
	public int getMaxReplicas() {
		return maxReplicas;
	}
	public void setMaxReplicas(int maxReplicas) {
		this.maxReplicas = maxReplicas;
	}
	public long getTargetCPUUtilizationPercentage() {
		return targetCPUUtilizationPercentage;
	}
	public void setTargetCPUUtilizationPercentage(long targetCPUUtilizationPercentage) {
		this.targetCPUUtilizationPercentage = targetCPUUtilizationPercentage;
	}
	
}
