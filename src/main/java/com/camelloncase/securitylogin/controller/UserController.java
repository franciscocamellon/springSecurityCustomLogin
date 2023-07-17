package com.camelloncase.securitylogin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.camelloncase.securitylogin.dto.UserDto;
import com.camelloncase.securitylogin.exception.UserAlreadyExistException;
import com.camelloncase.securitylogin.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userDetails);
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "login";
	}
	
	@GetMapping("/user/registration")
	public String showRegisterForm(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "/user/registration";
	}
	
	@PostMapping("/user/registration")
	public String registerUserAccount(Model model, @ModelAttribute("user") UserDto userDto) {
		
		try {
			userService.save(userDto);
		} catch (UserAlreadyExistException e) {
			model.addAttribute("useralreadyexist", e);
			return "redirect:/registration?failed";
		}
		
//		return "redirect:/user/registration?success";
		return "login";
	}

}
