package com.monitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monitoring.config.DeploymentConfig;
import com.monitoring.config.EnvironmentComponent;
import com.monitoring.dto.Environment;
import com.monitoring.utility.APIUtilitiy;

@Controller
@RequestMapping("/deployment")
public class DeploymentController {
	
	@Autowired
	EnvironmentComponent environmentConifg;
	
	@Autowired
	DeploymentConfig deploymentConfig;
	
	@Autowired
	APIUtilitiy apiUtilitiy; 
	
	@GetMapping("/getDeployment")
    public String getDeployment(@RequestParam(name="env", required=true, defaultValue="DEV01") String environmentName,@RequestParam(name="namespace", required=false) String namespace, Model model) throws Exception {
		Environment environment = environmentConifg.getEnvironmentDetails(environmentName);
		if(StringUtils.isEmpty(namespace)) {
			namespace=environment.getDefaultNameSpace();
		}
		
//		String URL = "https://"+ env.getMasterIP() + "/apis/apps/v1/namespaces/" + namespace + "/deployments";
//		ResponseEntity<Object> response = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<String>(apiUtilitiy.createHeaders(env.getUser(), env.getPassword())), Object.class);
		String deploymentsURL = "https://"+ environment.getMasterIP() +"/apis/apps/v1/namespaces/" + namespace + "/deployments";
		ResponseEntity<Object> response = apiUtilitiy.makeAPICall(deploymentsURL, HttpMethod.GET, environment);
		model.addAttribute("env", environmentName);
		model.addAttribute("namespace", namespace);
		model.addAttribute("deployment",response.getBody());
        return "deployment";
    }
	
}
