package com.monitoring.dto;

public class Resources {
	  Limits LimitsObject;
	  Requests RequestsObject;


	 // Getter Methods 

	  public Limits getLimits() {
	    return LimitsObject;
	  }

	  public Requests getRequests() {
	    return RequestsObject;
	  }

	 // Setter Methods 

	  public void setLimits( Limits limitsObject ) {
	    this.LimitsObject = limitsObject;
	  }

	  public void setRequests( Requests requestsObject ) {
	    this.RequestsObject = requestsObject;
	  }
	}
