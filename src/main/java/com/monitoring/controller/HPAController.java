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

import com.monitoring.config.EnvironmentComponent;
import com.monitoring.config.HPAConfig;
import com.monitoring.dto.Environment;
import com.monitoring.utility.APIUtilitiy;

@Controller
@RequestMapping("/hpa")
public class HPAController {
	
	@Autowired
	EnvironmentComponent environmentConifg;
	@Autowired
	HPAConfig hpaConfig;
	@Autowired
	APIUtilitiy apiUtilitiy;
	
	@GetMapping("/getAll")
    public String getDeployment(@RequestParam(name="env", required=true, defaultValue="DEV01") String environmentName,@RequestParam(name="namespace", required=false) String namespace, Model model) throws Exception {
		Environment environment = environmentConifg.getEnvironmentDetails(environmentName);
		if(StringUtils.isEmpty(namespace)) {
			namespace=environment.getDefaultNameSpace();
		}
		
		String hpaURL = "https://"+ environment.getMasterIP() +"/apis/autoscaling/v1/namespaces/" + namespace + "/horizontalpodautoscalers";
		ResponseEntity<Object> response = apiUtilitiy.makeAPICall(hpaURL, HttpMethod.GET, environment);
		model.addAttribute("env", environmentName);
		model.addAttribute("namespace", namespace);
		model.addAttribute("hpa",response.getBody());
        return "hpa";
    }
	
}
