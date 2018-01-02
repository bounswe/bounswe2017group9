package boun.group9.webservice.app.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Notifications;
import boun.group9.webservice.helper.NotificationChecker;
/**
 * Controller to handle operations related with Notifications
 * @author ffguven
 *
 */
@RestController
public class NotificationController {
	/**
	 * HTTP GET request will result in fetching notifications for the specified user
	 * @param userId id of the user of whom notifications will be fetched
	 * @return JSON array of Notifications objects
	 */
	@RequestMapping(value="notifications/{userId}",method=RequestMethod.GET)
	public String getNotifications(@PathVariable(value="userId") int userId) {
		ArrayList<Notifications> notificationList = NotificationChecker.getNotifications(userId); // fetch list of notifications for specified user
		return Application.gson.toJson(notificationList);
	}
}
