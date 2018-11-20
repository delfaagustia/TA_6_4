package com.apap.ta46.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.ta46.model.UserRoleModel;



@Controller
public class PageController {
	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	

	@RequestMapping (value = "/register")
	private String register (Model model) {
		UserRoleModel user = new UserRoleModel();
		model.addAttribute("user",user);
		return "register";
	}
}
