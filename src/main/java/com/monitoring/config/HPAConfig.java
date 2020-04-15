package com.monitoring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monitoring.dto.HPA.HorizontalPodAutoscaler;
import com.monitoring.repository.HorizontalPodAutoscalerRepository;

@Component
public class HPAConfig {

	@Autowired
	HorizontalPodAutoscalerRepository horizontalPodAutoscalerRepository;

	public void addHPA(HorizontalPodAutoscaler horizontalPodAutoscaler) {
		horizontalPodAutoscalerRepository.save(horizontalPodAutoscaler);
	}

}
