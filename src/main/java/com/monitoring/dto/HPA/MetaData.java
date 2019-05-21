package com.monitoring.dto.HPA;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class MetaData {

	String name;
	String namespace;
	String selfLink;
	String resourceVersion;
	Labels labels;
	Timestamp creationTimestamp;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getSelfLink() {
		return selfLink;
	}
	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}
	public String getResourceVersion() {
		return resourceVersion;
	}
	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
	public Labels getLabels() {
		return labels;
	}
	public void setLabels(Labels labels) {
		this.labels = labels;
	}
	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(Timestamp creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
}
