package com.monitoring.dto.HPA;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document(collection="HorizontalPodAutoScaler")
public class HorizontalPodAutoscaler {

	String kind;
	String apiVersion;
	List<Item> items;
	MetaData metadata;
	Date timeNow = new Date();
	String environment;
	
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
	public MetaData getMetadata() {
		return metadata;
	}
	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Date getTimeNow() {
		return timeNow;
	}
	public void setTimeNow(Date timeNow) {
		this.timeNow = timeNow;
	}
	public String getEnvironment() {
		return environment;
	}

}
