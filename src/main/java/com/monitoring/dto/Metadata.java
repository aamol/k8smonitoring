package com.monitoring.dto;

public class Metadata {
	private String selfLink;
	private String resourceVersion;
	
	  private String name;
	  private String namespace;
	  private String uid;
	  private float generation;
	  private String creationTimestamp;



	  // Getter Methods 

	   public String getName() {
	     return name;
	   }

	   public String getNamespace() {
	     return namespace;
	   }


	   public String getUid() {
	     return uid;
	   }


	   public float getGeneration() {
	     return generation;
	   }

	   public String getCreationTimestamp() {
	     return creationTimestamp;
	   }


	  // Setter Methods 

	   public void setName( String name ) {
	     this.name = name;
	   }

	   public void setNamespace( String namespace ) {
	     this.namespace = namespace;
	   }


	   public void setUid( String uid ) {
	     this.uid = uid;
	   }


	   public void setGeneration( float generation ) {
	     this.generation = generation;
	   }

	   public void setCreationTimestamp( String creationTimestamp ) {
	     this.creationTimestamp = creationTimestamp;
	   }



	// Getter Methods

	public String getSelfLink() {
		return selfLink;
	}

	public String getResourceVersion() {
		return resourceVersion;
	}

	// Setter Methods

	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
}
