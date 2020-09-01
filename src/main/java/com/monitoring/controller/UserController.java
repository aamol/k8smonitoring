package com.monitoring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.monitoring.dto.User;
import com.monitoring.service.RolesService;
import com.monitoring.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RolesService rolesService;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("roles", rolesService.getAllRoles());
		return "user/registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute User userForm) {

		userService.save(userForm);

		return "redirect:registration";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username or password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "user/login";
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}

}
