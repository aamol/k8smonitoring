package com.monitoring.dto;

import java.util.ArrayList;

public class Deployment {
	 private String kind;
	 private String apiVersion;
	 Metadata MetadataObject;
	 ArrayList < Items > items = new ArrayList < Items > ();


	 // Getter Methods 

	 public String getKind() {
	  return kind;
	 }

	 public String getApiVersion() {
	  return apiVersion;
	 }

	 public Metadata getMetadata() {
	  return MetadataObject;
	 }

	 // Setter Methods 

	 public void setKind(String kind) {
	  this.kind = kind;
	 }

	 public void setApiVersion(String apiVersion) {
	  this.apiVersion = apiVersion;
	 }

	 public void setMetadata(Metadata metadataObject) {
	  this.MetadataObject = metadataObject;
	 }

	public Metadata getMetadataObject() {
		return MetadataObject;
	}

	public void setMetadataObject(Metadata metadataObject) {
		MetadataObject = metadataObject;
	}

	public ArrayList<Items> getItems() {
		return items;
	}

	public void setItems(ArrayList<Items> items) {
		this.items = items;
	}
	}
	