package com.cbstd.jsfexperiment.controller;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cbstd.jsfexperiment.entity.User;
import com.cbstd.jsfexperiment.service.UserService;

@Controller("mainController")
public class MainController {

	@Autowired
	UserService userService;

	public void init(User user) {
		
		return;
	}

	public boolean signUp(User user) {
		
		boolean result = true;
	
		try {
			User dbUser = userService.createUser(user);
			if(null == dbUser)
			{
				getFacesContext().addMessage(null, constructErrorMessage(String.format(getMessageBundle().getString("usernamenotavailable"), user.getUsername()), null));
				result = false;
			}
			
		} catch (Exception e) {
			getFacesContext().addMessage(null, constructErrorMessage(e.getMessage(), null));
			result = false;
		}
		
		return result;
	}
	
	public boolean checkAvailableUsername(AjaxBehaviorEvent event) {
		InputText inputText = (InputText) event.getSource();
		String username = inputText.getValue().toString();
		
		boolean available = userService.checkAvailableUsername(username);
		
		if(!available)
		{
			getFacesContext().addMessage(event.getComponent().getClientId(), constructErrorMessage(null, String.format(getMessageBundle().getString("usernamenotavailable"), username)));
		}
		else {
			getFacesContext().addMessage(event.getComponent().getClientId(), constructInfoMessage(null, String.format(getMessageBundle().getString("usernameavailable"), username)));
		}
		
		return available;
		
	}
	
	private ResourceBundle getMessageBundle() {
		return ResourceBundle.getBundle("message-labels");
	}
	
	private FacesMessage constructErrorMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail);
	}
	
	private FacesMessage constructInfoMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail);
	}
	
	private FacesContext getFacesContext()
	{
		return FacesContext.getCurrentInstance();
	}
}
