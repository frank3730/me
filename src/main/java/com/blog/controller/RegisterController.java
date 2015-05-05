package com.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.domain.User;
import com.blog.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Resource
	private RegisterService registerService;

	@RequestMapping("/confirm")
	public String register(User user, String userName, String password,
			String confirmPassword, String email,HttpServletRequest request) {
		System.out.println(userName + "*************");
		if(!password.equals(confirmPassword)){
			System.out.println("两次密码输入不一样");
			request.setAttribute("pwd_err", "两次密码输入不一样");
			return "/register";
		}else{
			System.out.println("两次密码输入一样");
			user.setUserName(userName);
			user.setPassword(password);
			registerService.register(user);
			return "/login";
		}

	}

}
