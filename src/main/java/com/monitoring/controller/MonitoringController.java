package com.monitoring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monitoring.config.EnvironmentComponent;
import com.monitoring.dto.Environment;

@Controller
@RequestMapping("/")
public class MonitoringController {
	
	@Autowired
	EnvironmentComponent component;
	
	@RequestMapping("/")
    public String getDefaultPage(Model model) throws Exception {
    	
    	List<Environment> environments =  component.getAllEnvironments();
    	model.addAttribute("environment", environments);
		return "index";
    }

}
