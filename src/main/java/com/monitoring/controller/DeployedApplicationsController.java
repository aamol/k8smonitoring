package com.monitoring.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monitoring.config.DeployedApplicationsComponent;
import com.monitoring.dto.DeployedApplication;
@Controller
@RequestMapping("/deployedApplications")
public class DeployedApplicationsController{
	
	@Autowired
	private DeployedApplicationsComponent component;
	
	@GetMapping("/home")
	public String getDeployedApplication(Model model) throws Exception {
		
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user instanceof User) {
			Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) ((User) user).getAuthorities();
			model.addAttribute("isAdmin",
					authorities.stream().anyMatch(authority -> authority.getAuthority().equals("admin")));
		}
		List<DeployedApplication> deployedApplications =  component.getAllDeployedApplications();
		model.addAttribute("deployedapplications", deployedApplications);
		return "deployedApplication/index";
	}
	
	@GetMapping("/add")
	public String addDeploymentapplication(Model model) throws Exception {
		return "deployedApplication/add";
	}

	@PostMapping("/add")
	public String addDeploymentApplicationFormSubmit(@ModelAttribute DeployedApplication deployedApplication) throws Exception {
		component.addUpdateDeployedApplicationDetails(deployedApplication);
		return "redirect:home";
	}
	
	@GetMapping("/updateSelect")
	public String updateDeploymentApplication(Model model) throws Exception {
		List<DeployedApplication> deployedapplications =  component.getAllDeployedApplications();
    	model.addAttribute("deployedapplications", deployedapplications);
		return "deployedApplication/update";
	}
	
	@PostMapping("/updateSelected")
	public String updateSelectedDeploymentApplication(@RequestParam Map<String, String> map, Model model) throws Exception {
		String applicationName = map.get("name");
		DeployedApplication deployedapplication = component.getDeployedApplicationDetails(applicationName);
    	model.addAttribute("deployedapplication", deployedapplication);
		return "deployedApplication/updateApp";
	}
	
	@PostMapping("/update")
	public String updateDeploymentApplicationFormSubmit(@ModelAttribute DeployedApplication deployedApplication) throws Exception {
		component.addUpdateDeployedApplicationDetails(deployedApplication);
		return "redirect:home";
	}
	
	@GetMapping("/deleteSelect")
	public String deleteDeploymentApplication(Model model) throws Exception {
		List<DeployedApplication> deployedapplication =  component.getAllDeployedApplications();
		model.addAttribute("deployedapplication", deployedapplication);
		return "deployedApplication/delete";
	}
	
	@PostMapping("/deleteSelected")
	public String deleteSelectedEnvironment(@RequestParam Map<String, String> map, Model model) throws Exception {
		String depName = map.get("name");
		component.deleteDeploymentApplication(component.getDeployedApplicationDetails(depName));
		return "redirect:home";
	}

}



