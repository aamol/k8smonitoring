package com.monitoring.dto;

public class Container {
	  private String name;
	  private String image;
	  Resources ResourcesObject;
	  private String terminationMessagePath;
	  private String terminationMessagePolicy;
	  private String imagePullPolicy;


	 // Getter Methods 

	  public String getName() {
	    return name;
	  }

	  public String getImage() {
	    return image;
	  }

	  public Resources getResources() {
	    return ResourcesObject;
	  }

	  public String getTerminationMessagePath() {
	    return terminationMessagePath;
	  }

	  public String getTerminationMessagePolicy() {
	    return terminationMessagePolicy;
	  }

	  public String getImagePullPolicy() {
	    return imagePullPolicy;
	  }

	 // Setter Methods 

	  public void setName( String name ) {
	    this.name = name;
	  }

	  public void setImage( String image ) {
	    this.image = image;
	  }

	  public void setResources( Resources resourcesObject ) {
	    this.ResourcesObject = resourcesObject;
	  }

	  public void setTerminationMessagePath( String terminationMessagePath ) {
	    this.terminationMessagePath = terminationMessagePath;
	  }

	  public void setTerminationMessagePolicy( String terminationMessagePolicy ) {
	    this.terminationMessagePolicy = terminationMessagePolicy;
	  }

	  public void setImagePullPolicy( String imagePullPolicy ) {
	    this.imagePullPolicy = imagePullPolicy;
	  }
	}
