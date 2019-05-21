package com.monitoring.dto.Deployments;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Labels {

	String app;
	
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

}
