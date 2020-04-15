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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
import com.monitoring.utility.APIUtilitiy;

@Component
public class DeploymentAndHPAScheduler {
	
	@Autowired
	EnvironmentComponent environmentConifg;
	@Autowired
	DeploymentConfig deploymentConfig;
	@Autowired
	HPAConfig hpaConfig;
	@Autowired
	APIUtilitiy apiUtility;
	@Value("#{'${skippedNameSpaces}'.split(',')}")
	private List<String> skippedNameSpaces;
	
	ObjectMapper objectMapper;
	
	private static final Logger log = LoggerFactory.getLogger(DeploymentAndHPAScheduler.class);
	
	@Scheduled(fixedDelayString = "${schedulerFrequency}", initialDelay = 5000)
	public void populateDeploymentsData() {
		
		log.info("Scheduler execution started");
		List<Environment> environments = environmentConifg.getAllEnvironments();
		try {
			for (Environment env : environments) {
				if (objectMapper == null ) {
					objectMapper = new ObjectMapper();
				}
				String namespacesURL = "https://"+env.getMasterIP()+"/api/v1/namespaces";
				ResponseEntity<Object> nameSpacesResponse = apiUtility.makeAPICall(namespacesURL, HttpMethod.GET, env);
				Gson gson = new GsonBuilder().create();
				String jsonNameSpacesResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nameSpacesResponse.getBody());
				NameSpaces nameSpaces = gson.fromJson(jsonNameSpacesResult, NameSpaces.class);
				if (nameSpaces != null && nameSpaces.getItems() != null) {
					for (Item item : nameSpaces.getItems()) {
						if (skippedNameSpaces.contains(item.getMetadata().getName())) {
							continue;
						}
						
						log.info("Populating Deployment information for environment = {} namespace = {}", env.getName(), item.getMetadata().getName());
						String deploymentsURL = "https://"+ env.getMasterIP() +"/apis/apps/v1/namespaces/" + item.getMetadata().getName() + "/deployments";
						ResponseEntity<Object> deploymentResponse = apiUtility.makeAPICall(deploymentsURL, HttpMethod.GET, env);
						String jsonDeploymentResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(deploymentResponse.getBody());
						Deployment deployment = gson.fromJson(jsonDeploymentResult, Deployment.class);
						deployment.setEnvironment(env.getName());
						deploymentConfig.addDeployments(deployment);
						
						log.info("Populating HPA information for environment = {} namespace = {}", env.getName(), item.getMetadata().getName());
						String hpaURL = "https://"+env.getMasterIP()+"/apis/autoscaling/v1/namespaces/"+ item.getMetadata().getName() +"/horizontalpodautoscalers";
						ResponseEntity<Object> hpaResponse = apiUtility.makeAPICall(hpaURL, HttpMethod.GET, env);
						String jsonHPAResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(hpaResponse.getBody());
						HorizontalPodAutoscaler horizontalPodAutoscaler = gson.fromJson(jsonHPAResult, HorizontalPodAutoscaler.class);
						horizontalPodAutoscaler.setEnvironment(env.getName());
						hpaConfig.addHPA(horizontalPodAutoscaler);
					}
				}
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
			log.error("Error occurred:: " + e.getMessage());
		}
		
	}
	
}
