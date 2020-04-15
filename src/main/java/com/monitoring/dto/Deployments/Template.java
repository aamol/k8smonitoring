package com.monitoring.dto.Deployments;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.monitoring.dto.Deployments.Templates.Spec;
@JsonIgnoreProperties
public class Template {

	MetaData metadata;
	Spec spec;
	
	public MetaData getMetadata() {
		return metadata;
	}
	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}
	public Spec getSpec() {
		return spec;
	}
	public void setSpec(Spec spec) {
		this.spec = spec;
	}

}
