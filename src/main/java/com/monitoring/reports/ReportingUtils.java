package com.monitoring.reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.monitoring.dto.Environment;
import com.monitoring.dto.ReportDTO;
import com.monitoring.dto.HPA.HorizontalPodAutoscaler;

@Component
public class ReportingUtils {

	@Autowired
	MongoTemplate mongoTemplate;

	private List<Object> executeQuery(Query query, Class clazz, String collectionName) {
		query.fields().exclude("_id");
		return mongoTemplate.find(query, clazz, collectionName);
	}

	public List<Object> getAllEnvName() {
		Query query = new Query();
		query.with(new Sort(Sort.DEFAULT_DIRECTION.ASC, "name"));
		query.fields().include("name");
		return executeQuery(query, Environment.class, "environment");
	}

	public List<String> getNamespaces(String envName) {
		List<String> namespaceList = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(new Criteria().where("environment").is(envName));
		for (String namespace : mongoTemplate.getCollection("Deployment").distinct("items.metadata.namespace",
				query.getQueryObject(), String.class)) {
			namespaceList.add(namespace);
		}
		return namespaceList;
	}

	public List<String> getDeployments(String envName, String namespace) {
		List<String> deploymentList = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(
				new Criteria().where("environment").is(envName).and("items.metadata.namespace").is(namespace));
		for (String deployment : mongoTemplate.getCollection("Deployment").distinct("items.metadata.name",
				query.getQueryObject(), String.class)) {
			deploymentList.add(deployment);
		}
		return deploymentList;
	}
	public List<ReportDTO> getReportData(String envName, String namespace, String deployment, String startDate,
			String endDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start = dateFormat.parse(startDate);
		Date end = dateFormat.parse(endDate);
		end.setTime(end.getTime()+24*60*60*1000);
		MatchOperation matchStage = Aggregation
				.match(new Criteria("environment").is(envName).and("items.metadata.namespace").is(namespace)
						.and("items.metadata.name").is(deployment).and("timeNow").gte(start).lte(end));

		
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.unwind("items"),matchStage, 
				Aggregation.project("timeNow", "items.status.currentReplicas","items.status.currentCPUUtilizationPercentage"),Aggregation.sort(new Sort(Sort.DEFAULT_DIRECTION.ASC, "timeNow")));

		AggregationResults<ReportDTO> output = mongoTemplate.aggregate(aggregation,
				"HorizontalPodAutoScaler", ReportDTO.class);
		return output.getMappedResults();
	}
	
	public List<ReportDTO> getData(String envName, String namespace, String deployment, String startDate,
			String endDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date start = dateFormat.parse(startDate);
		Date end = dateFormat.parse(endDate);
		MatchOperation matchStage = Aggregation
				.match(new Criteria("environment").is(envName).and("items.metadata.namespace").is(namespace)
						.and("items.metadata.name").is(deployment).and("timeNow").gte(start).lte(end));

		
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.unwind("items"),matchStage, 
				Aggregation.project("timeNow", "items.status.currentReplicas","items.status.currentCPUUtilizationPercentage"));

		AggregationResults<ReportDTO> output = mongoTemplate.aggregate(aggregation,
				"HorizontalPodAutoScaler", ReportDTO.class);
		return output.getMappedResults();
	}
	

}
