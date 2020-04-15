package com.monitoring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monitoring.dto.HPA.HorizontalPodAutoscaler;

public interface HorizontalPodAutoscalerRepository extends MongoRepository<HorizontalPodAutoscaler, String> {


}
