package com.monitoring.dto;

import java.util.ArrayList;

public class Spec {
	  private float replicas;
	  Template TemplateObject;
	  Strategy StrategyObject;
	  private float revisionHistoryLimit;
	  private float progressDeadlineSeconds;
	  
	  ArrayList<Container> containers = new ArrayList<Container>();
	  private String restartPolicy;
	  private float terminationGracePeriodSeconds;
	  private String dnsPolicy;
	  ArrayList<Object> imagePullSecrets = new ArrayList<Object>();
	  private String schedulerName;


	  

	  // Getter Methods 

	   public String getRestartPolicy() {
	     return restartPolicy;
	   }

	   public float getTerminationGracePeriodSeconds() {
	     return terminationGracePeriodSeconds;
	   }

	   public String getDnsPolicy() {
	     return dnsPolicy;
	   }

	   public String getSchedulerName() {
	     return schedulerName;
	   }

	  // Setter Methods 

	   public void setRestartPolicy( String restartPolicy ) {
	     this.restartPolicy = restartPolicy;
	   }

	   public void setTerminationGracePeriodSeconds( float terminationGracePeriodSeconds ) {
	     this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
	   }

	   public void setDnsPolicy( String dnsPolicy ) {
	     this.dnsPolicy = dnsPolicy;
	   }


	   public void setSchedulerName( String schedulerName ) {
	     this.schedulerName = schedulerName;
	   }



	 // Getter Methods 

	  public float getReplicas() {
	    return replicas;
	  }


	  public Template getTemplate() {
	    return TemplateObject;
	  }

	  public Strategy getStrategy() {
	    return StrategyObject;
	  }

	  public float getRevisionHistoryLimit() {
	    return revisionHistoryLimit;
	  }

	  public float getProgressDeadlineSeconds() {
	    return progressDeadlineSeconds;
	  }

	 // Setter Methods 

	  public void setReplicas( float replicas ) {
	    this.replicas = replicas;
	  }


	  public void setTemplate( Template templateObject ) {
	    this.TemplateObject = templateObject;
	  }

	  public void setStrategy( Strategy strategyObject ) {
	    this.StrategyObject = strategyObject;
	  }

	  public void setRevisionHistoryLimit( float revisionHistoryLimit ) {
	    this.revisionHistoryLimit = revisionHistoryLimit;
	  }

	  public void setProgressDeadlineSeconds( float progressDeadlineSeconds ) {
	    this.progressDeadlineSeconds = progressDeadlineSeconds;
	  }

	public ArrayList<Container> getContainers() {
		return containers;
	}

	public void setContainers(ArrayList<Container> containers) {
		this.containers = containers;
	}
}
