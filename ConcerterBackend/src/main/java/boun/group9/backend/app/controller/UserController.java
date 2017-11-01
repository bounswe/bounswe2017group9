package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.UserOperations;

@Controller
public class UserController {
	@RequestMapping("/profile/{userID}")
	public String profilePage(@PathVariable("userID") int userID,Model model) {
		//Users user = UserOperations.getUser(userID);
		//model.addAttribute(user);
		return "profile";
	}
	@RequestMapping("/profile/{userID}/attending")
	public String attendingProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> attendingConcertList = ConcertOperations.getAttendingConcerts(userID);
		model.addAttribute("user",user);
		model.addAttribute("concertList",attendingConcertList);
		return "attendingProfile";
	}
	@RequestMapping("/profile/{userID}/attended")
	public String attendedProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> attendedConcertList = ConcertOperations.getAttendedConcerts(userID);
		model.addAttribute("user",user);
		model.addAttribute("concertList",attendedConcertList);
		return "attendedProfile";
	}
	@RequestMapping("/profile/{userID}/thinking")
	public String thinkingProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> thinkingConcertList = ConcertOperations.getThinkingConcerts(userID);
		model.addAttribute("user",user);
		model.addAttribute("concertList",thinkingConcertList);
		return "thinking";
	}
}
