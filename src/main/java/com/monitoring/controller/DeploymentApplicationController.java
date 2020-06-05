package com.monitoring.controller;

import java.awt.Component;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.ComponentUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monitoring.config.DeploymentApplicationComponent;
import com.monitoring.config.EnvironmentComponent;
import com.monitoring.dto.DeployedApplication;
import com.monitoring.dto.Environment;
@Controller
@RequestMapping("/deploymentapplication")
public class DeploymentApplicationController{
	
	private DeploymentApplicationComponent component;



	@Autowired
	@GetMapping("/add")
	public String addDeploymentapplication(Model model) throws Exception {
		
		return "deploymentapplication/add";
	}

	@PostMapping("/add")
	public String addDeploymentApplicationFormSubmit(@ModelAttribute String dep) throws Exception {
		component.getDeploymentApplicationDetails(dep);
		
		return "deploymentapplication/index";
	}
	
	@GetMapping("/updateSelect")
	public String updateDeploymentApplication(Model model) throws Exception {
		List<DeployedApplication> deploymentapplications =  component.getAllDeploymentApplication();
    	model.addAttribute("deploymentapplication", deploymentapplications);
		return "deploymentapplication/update";
	}
	
	@PostMapping("/updateSelected")
	public String updateSelectedDeploymentApplication(@RequestParam Map<String, String> map, Model model) throws Exception {
		String depPort = map.get("port");
		DeployedApplication deploymentapplication = component.getDeploymentApplicationDetails(depPort);
    	model.addAttribute("deploymentapplication", deploymentapplication);
		return "deploymentapplication/updateEnv";
	}
	
	@PostMapping("/update")
	public String updateDeploymentApplicationFormSubmit(@ModelAttribute String dep) throws Exception {
		component.getDeploymentApplicationDetails(dep);
		
		return "deploymentapplication/index";
	}
	
	@GetMapping("/deleteSelect")
	public String deleteDeploymentApplication(Model model) throws Exception {
		List<DeployedApplication> deploymentapplications =  component.getAllDeploymentApplication();
    	model.addAttribute("environment", deploymentapplications);
		return "deploymentapplication/delete";
	}
	
	@PostMapping("/deleteSelected")
	public String deleteSelectedEnvironment(@RequestParam Map<String, String> map, Model model) throws Exception {
		String depName = map.get("name");
		DeployedApplication deploymentapplication = component.getDeploymentApplicationDetails(depName);
		component.deleteDeploymentApplication(deploymentapplication);
		return "deploymentapplication/index";
	}
	
	

}



