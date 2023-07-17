package com.camelloncase.securitylogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@GetMapping(value = "/")
	public String indexScreen() {
		return "home";
	}

}
