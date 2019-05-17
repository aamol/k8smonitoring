package com.monitoring.scheduler;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.monitoring.config.DeploymentConfig;
import com.monitoring.config.EnvironmentComponent;
import com.monitoring.config.HPAConfig;
import com.monitoring.dto.Environment;
import com.monitoring.dto.Deployments.Deployment;
import com.monitoring.dto.HPA.HorizontalPodAutoscaler;
import com.monitoring.dto.NameSpaces.Item;
import com.monitoring.dto.NameSpaces.NameSpaces;
import com.monitoring.utility.ProjectUtility;

@Component
public class DeploymentAndHPAScheduler {
	
	@Autowired
	EnvironmentComponent environmentConifg;
	@Autowired
	DeploymentConfig deploymentConfig;
	@Autowired
	HPAConfig hpaConfig;
	@Value("#{'${skippedNameSpaces}'.split(',')}")
	private List<String> skippedNameSpaces;
	
	ObjectMapper objectMapper;
	
	private static final Logger log = LoggerFactory.getLogger(DeploymentAndHPAScheduler.class);
	
	@Scheduled(fixedDelay = 3600000, initialDelay = 5000)
	public void populateDeploymentsData() {
		
		log.info("Scheduler execution started");
		List<Environment> environments = environmentConifg.getAllEnvironments();
		try {
			HttpComponentsClientHttpRequestFactory requestFactory = ProjectUtility.getRequestFactory();
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			for (Environment env : environments) {
				String URLforNameSpaces = "https://"+env.getMasterIP()+"/api/v1/namespaces";
				if (objectMapper == null ) {
					objectMapper = new ObjectMapper();
				}
				ResponseEntity<Object> nameSpacesResponse = restTemplate.exchange(URLforNameSpaces, HttpMethod.GET, new HttpEntity<String>(ProjectUtility.createHeaders(env.getUser(), env.getPassword())), Object.class);
				Gson gson = new GsonBuilder().create();
				String jsonNameSpacesResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nameSpacesResponse.getBody());
//				NameSpaces nameSpaces = gson.fromJson(JSON.serialize(nameSpacesResponse.getBody()), NameSpaces.class);
				NameSpaces nameSpaces = gson.fromJson(jsonNameSpacesResult, NameSpaces.class);
				if (nameSpaces != null && nameSpaces.getItems() != null) {
					for (Item item : nameSpaces.getItems()) {
						if (skippedNameSpaces.contains(item.getMetadata().getName())) {
							continue;
						}
						
						String URL = "https://"+env.getMasterIP()+"/apis/apps/v1/namespaces/"+ item.getMetadata().getName() +"/deployments";
						log.info("Populating Deployment information for environment = {} namespace = {}", env.getName(), item.getMetadata().getName());
						ResponseEntity<Object> deployments = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<String>(ProjectUtility.createHeaders(env.getUser(), env.getPassword())), Object.class);
						String jsonDeploymentResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deployments.getBody());
						Deployment deployment = gson.fromJson(jsonDeploymentResult, Deployment.class);
//						Deployment deployment = gson.fromJson(JSON.serialize(deployments.getBody()), Deployment.class);
						deploymentConfig.addDeployments(deployment);
						log.info("Deployment data populated scuccessfully");
						
						String hpaURL = "https://"+env.getMasterIP()+"/apis/autoscaling/v1/namespaces/"+ item.getMetadata().getName() +"/horizontalpodautoscalers";
						log.info("Populating HPA information for environment = {} namespace = {}", env.getName(), item.getMetadata().getName());
						ResponseEntity<Object> hpa = restTemplate.exchange(hpaURL, HttpMethod.GET, new HttpEntity<String>(ProjectUtility.createHeaders(env.getUser(), env.getPassword())), Object.class);
						String jsonHPAResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(hpa.getBody());
//						HorizontalPodAutoscaler horizontalPodAutoscaler = gson.fromJson(JSON.serialize(hpa.getBody()), HorizontalPodAutoscaler.class);
						HorizontalPodAutoscaler horizontalPodAutoscaler = gson.fromJson(jsonHPAResult, HorizontalPodAutoscaler.class);
						hpaConfig.addHPA(horizontalPodAutoscaler);
						log.info("HPA data populated scuccessfully");
					}
				}
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
			log.error("Error occurred:: " + e.getMessage());
		}
		
	}
	
}
