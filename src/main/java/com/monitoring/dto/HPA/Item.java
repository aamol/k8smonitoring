package com.monitoring.dto.HPA;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Item {

	MetaData metadata;
	Spec spec;
	Status status;
	
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
