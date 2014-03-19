package br.com.its.web.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.its.classes.User;
import br.com.its.services.EmailNotificationService;


@Controller
@Path("/email")
public class EmailNotificationController {
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response send(User email){
		Response response = Response.ok().build();
		try {
			emailNotificationService.sendEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response ;
	}

	public EmailNotificationService getEmailNotificationService() {
		return emailNotificationService;
	}

	public void setEmailNotificationService(
			EmailNotificationService emailNotificationService) {
		this.emailNotificationService = emailNotificationService;
	}
	
}
