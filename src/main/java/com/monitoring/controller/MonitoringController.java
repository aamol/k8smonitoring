package com.monitoring.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user instanceof User) {
			Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) ((User) user).getAuthorities();
			model.addAttribute("isAdmin",
					authorities.stream().anyMatch(authority -> authority.getAuthority().equals("admin")));
		}
    	List<Environment> environments =  component.getAllEnvironments();
    	model.addAttribute("environment", environments);
		return "index";
    }

}
