package com.monitoring.dto;

public class RollingUpdate {
	  private String maxUnavailable;
	  private String maxSurge;


	 // Getter Methods 

	  public String getMaxUnavailable() {
	    return maxUnavailable;
	  }

	  public String getMaxSurge() {
	    return maxSurge;
	  }

	 // Setter Methods 

	  public void setMaxUnavailable( String maxUnavailable ) {
	    this.maxUnavailable = maxUnavailable;
	  }

	  public void setMaxSurge( String maxSurge ) {
	    this.maxSurge = maxSurge;
	  }
}
