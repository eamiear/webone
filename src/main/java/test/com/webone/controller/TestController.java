package com.webone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/dealtask2.html")
	public ModelAndView dealtask(){
		return new ModelAndView("backend/form/backup/dealtask2");
	}
	
	@RequestMapping("/approval.html")
	public ModelAndView approve(){
		return new ModelAndView("backend/form/backup/approvaltask");
	}
	
	@RequestMapping("/userlist.html")
	public ModelAndView userlist(){
		return new ModelAndView("backend/form/backup/userlist");
	}
}
