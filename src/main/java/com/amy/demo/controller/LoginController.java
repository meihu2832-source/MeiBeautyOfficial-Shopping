package com.amy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

	
	@GetMapping("/index")
	public String index(HttpServletRequest request) {
		return "login";
	}
}