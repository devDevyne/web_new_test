package com.devyne.newspring.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/auth/accessDenied")
	public String accessDeniedPage() throws Exception {
		logger.info("====================== access Denied ======================");
		return "/auth/accessDenied";
	}
	
	@RequestMapping(value = "/auth/login") 
	public String loginPage(HttpServletRequest request) throws Exception {
		logger.info("====================== 로그인 페이지 ======================");
		
		return "/auth/login";
	}
	
}
