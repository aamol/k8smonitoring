package com.monitoring.dto;

import java.util.ArrayList;

public class Status {
	private float observedGeneration;
	private float replicas;
	private float updatedReplicas;
	private float readyReplicas;
	private float availableReplicas;
	ArrayList<Object> conditions = new ArrayList<Object>();

	// Getter Methods

	public float getObservedGeneration() {
		return observedGeneration;
	}

	public float getReplicas() {
		return replicas;
	}

	public float getUpdatedReplicas() {
		return updatedReplicas;
	}

	public float getReadyReplicas() {
		return readyReplicas;
	}

	public float getAvailableReplicas() {
		return availableReplicas;
	}

	// Setter Methods

	public void setObservedGeneration(float observedGeneration) {
		this.observedGeneration = observedGeneration;
	}

	public void setReplicas(float replicas) {
		this.replicas = replicas;
	}

	public void setUpdatedReplicas(float updatedReplicas) {
		this.updatedReplicas = updatedReplicas;
	}

	public void setReadyReplicas(float readyReplicas) {
		this.readyReplicas = readyReplicas;
	}

	public void setAvailableReplicas(float availableReplicas) {
		this.availableReplicas = availableReplicas;
	}
}
