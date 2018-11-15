package com.monitoring.dto;


public class Items {
  Metadata MetadataObject;
  Spec SpecObject;
  Status StatusObject;


 // Getter Methods 

  public Metadata getMetadata() {
    return MetadataObject;
  }

  public Spec getSpec() {
    return SpecObject;
  }

  public Status getStatus() {
    return StatusObject;
  }

 // Setter Methods 

  public void setMetadata( Metadata metadataObject ) {
    this.MetadataObject = metadataObject;
  }

  public void setSpec( Spec specObject ) {
    this.SpecObject = specObject;
  }

  public void setStatus( Status statusObject ) {
    this.StatusObject = statusObject;
  }
}
