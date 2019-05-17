package com.monitoring.dto.Deployments;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Deployment")
public class Deployment {

	String kind;
	String apiVersion;
	List<Item> items;
	MetaData metadata;
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public MetaData getMetaData() {
		return metadata;
	}
	public void setMetaData(MetaData metadata) {
		this.metadata = metadata;
	}


}
