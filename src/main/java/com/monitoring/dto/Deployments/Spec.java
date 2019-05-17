package com.monitoring.dto.Deployments;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Spec {

	int replicas;
	Template template;
	
	public int getReplicas() {
		return replicas;
	}
	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}

	
}
