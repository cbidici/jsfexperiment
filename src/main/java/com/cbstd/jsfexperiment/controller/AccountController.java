package com.cbstd.jsfexperiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cbstd.jsfexperiment.entity.User;
import com.cbstd.jsfexperiment.service.UserService;

@Controller
public class AccountController {

	@Autowired
	UserService userService;
	
	public User init(String username)
	{
		return userService.findByUsername(username);
	}
	
}
