package com.cbstd.jsfexperiment.utils.ui;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

public class AccountUIUtils implements Serializable{
	
	private static final long serialVersionUID = -5298586378874795931L;
	
	private int viewLoadCount = 0;
	
	public void greetOnLoad(ComponentSystemEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(viewLoadCount < 1)
		{
			String firstName = (String) event.getComponent().getAttributes().get("firstName");
			String lastName = (String) event.getComponent().getAttributes().get("lastName");
			
			FacesMessage message = new FacesMessage(String.format("Welcome to your account %s %s", firstName, lastName));
			
			context.addMessage("growlMessages", message);
			
			viewLoadCount++;
		}
	}
}
