package com.cbstd.jsfexperiment.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.cbstd.jsfexperiment.entity.User;

@Controller
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	public boolean login(User user) {
		try {
			System.out.println("Login : " + user.getPassword());
			Authentication request = new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			return true;
		} catch (AuthenticationException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), "Sorry!"));
			return false;
		}
	}
}
