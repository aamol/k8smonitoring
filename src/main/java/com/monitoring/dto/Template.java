package com.monitoring.dto;

public class Template {
	  Metadata MetadataObject;
	  Spec SpecObject;


	 // Getter Methods 

	  public Metadata getMetadata() {
	    return MetadataObject;
	  }

	  public Spec getSpec() {
	    return SpecObject;
	  }

	 // Setter Methods 

	  public void setMetadata( Metadata metadataObject ) {
	    this.MetadataObject = metadataObject;
	  }

	  public void setSpec( Spec specObject ) {
	    this.SpecObject = specObject;
	  }
}
