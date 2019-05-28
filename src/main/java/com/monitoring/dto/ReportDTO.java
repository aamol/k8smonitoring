package com.monitoring.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.monitoring.dto.HPA.Status;

public class ReportDTO implements Serializable,Comparable<Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Date timeNow;
	
	Status status;

	public String getTimeNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z");
		return dateFormat.format(timeNow);
	}

	public void setTimeNow(Date timeNow) {
		this.timeNow = timeNow;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	@Override
	public int compareTo(Object o) {
		
		return this.timeNow.compareTo(((ReportDTO)o).timeNow);
	} 
}
