package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import boun.group9.backend.app.data.Notifications;
import boun.group9.backend.app.data.Search;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.NotificationOperations;

@Controller
public class NotificationController {
	@RequestMapping(value="notifications/{userId}",method=RequestMethod.GET)
	public String getNotifications(@PathVariable("userId") int userId, Model model, HttpSession session) {
		ArrayList<Notifications> notificationList = NotificationOperations.getNotificationList(userId);
		model.addAttribute("notificationList",notificationList);
		model.addAttribute("search",new Search());
		model.addAttribute("loggedInUser",(Users)session.getAttribute("loggedInUser"));
		return "notifications";
	}
}
