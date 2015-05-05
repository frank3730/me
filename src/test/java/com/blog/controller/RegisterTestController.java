package com.blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.domain.User;
import com.blog.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterTestController {
	@Resource
	private RegisterService registerService;
	@RequestMapping("/add")
	public void register(String userName,String password,String confirmPassword,String email){
		System.out.println(userName+"*************");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		registerService.register(user);
		
	}

}
