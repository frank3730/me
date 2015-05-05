package com.blog.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.dao.RegisterDao;
import com.blog.domain.User;

@Service
public class RegisterTestService {
	@Resource
	private RegisterDao registerDao;

	public void register(User user) {
		registerDao.register(user);
	}
}
