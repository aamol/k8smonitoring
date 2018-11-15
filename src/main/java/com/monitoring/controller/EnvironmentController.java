package com.monitoring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monitoring.config.EnvironmentComponent;
import com.monitoring.dto.Environment;

@Controller
@RequestMapping("/env")
public class EnvironmentController {
	
	@Autowired
	EnvironmentComponent component; 

	@GetMapping("/home")
	public String getEnvironment(Model model) throws Exception {
		return "environment/index";
	}
	
	@GetMapping("/add")
	public String addEnvironment(Model model) throws Exception {
		
		return "environment/add";
	}

	@PostMapping("/add")
	public String addEnvironmentFormSubmit(@ModelAttribute Environment env) throws Exception {
		component.addUpdateEnvironmentDetails(env);
		
		return "environment/index";
	}
	
	@GetMapping("/updateSelect")
	public String updateEnvironment(Model model) throws Exception {
		List<Environment> environments =  component.getAllEnvironments();
    	model.addAttribute("environment", environments);
		return "environment/update";
	}
	
	@PostMapping("/updateSelected")
	public String updateSelectedEnvironment(@RequestParam Map<String, String> map, Model model) throws Exception {
		String envName = map.get("name");
		Environment environment = component.getEnvironmentDetails(envName);
    	model.addAttribute("environment", environment);
		return "environment/updateEnv";
	}
	
	@PostMapping("/update")
	public String updateEnvironmentFormSubmit(@ModelAttribute Environment env) throws Exception {
		component.addUpdateEnvironmentDetails(env);
		
		return "environment/index";
	}
	
	@GetMapping("/deleteSelect")
	public String deleteEnvironment(Model model) throws Exception {
		List<Environment> environments =  component.getAllEnvironments();
    	model.addAttribute("environment", environments);
		return "environment/delete";
	}
	
	@PostMapping("/deleteSelected")
	public String deleteSelectedEnvironment(@RequestParam Map<String, String> map, Model model) throws Exception {
		String envName = map.get("name");
		Environment environment = component.getEnvironmentDetails(envName);
		component.deleteEnvironment(environment);
		return "environment/index";
	}
	
	

}
