package boun.group9.webservice.app.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Notifications;
import boun.group9.webservice.helper.NotificationChecker;
@RestController
public class NotificationController {
	@RequestMapping(value="notifications/{userId}",method=RequestMethod.GET)
	public String getNotifications(@PathVariable(value="userId") int userId) {
		ArrayList<Notifications> notificationList = NotificationChecker.getNotifications(userId);
		return Application.gson.toJson(notificationList);
	}
}
