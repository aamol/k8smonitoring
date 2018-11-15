package com.monitoring.dto;

public class Strategy {
	  private String type;
	  RollingUpdate RollingUpdateObject;


	 // Getter Methods 

	  public String getType() {
	    return type;
	  }

	  public RollingUpdate getRollingUpdate() {
	    return RollingUpdateObject;
	  }

	 // Setter Methods 

	  public void setType( String type ) {
	    this.type = type;
	  }

	  public void setRollingUpdate( RollingUpdate rollingUpdateObject ) {
	    this.RollingUpdateObject = rollingUpdateObject;
	  }
}
