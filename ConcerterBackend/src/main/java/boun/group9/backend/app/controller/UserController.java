package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.UserOperations;

import boun.group9.backend.app.helper.UserOperations;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	private static STATUS status;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String LoginUser(Model model) {
		model.addAttribute("user",new Users());
		return "login";
	}
	
	@RequestMapping(value="/profilepage",method= RequestMethod.POST)
	public String loginPage(@ModelAttribute Users user){
		status = UserOperations.login(user);
		System.out.println(status.toString());
		if(status == STATUS.SUCCESS) {
			return "profile";
		}else {
			return "error";
		}
	}
	

	@RequestMapping(value="/signUp",method=RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user",new Users());
		return "signUp";
	}
	
	@RequestMapping(value="/signUp",method= RequestMethod.POST)
	public String submitNewUser(@ModelAttribute Users user){
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		status = UserOperations.signUp(user);
		System.out.println(status.toString());
		if(status == STATUS.SUCCESS) {
			return "profile";
		}else {
			return "error";
		}
	}
	
	/*
	@RequestMapping("/profile/{userID}")
	public String profilePage(@PathVariable("userID") int userID,Model model) {
		//Users user = UserOperations.getUser(userID);
		//model.addAttribute(user);
		return "profile";
	}
	*/
	
	@RequestMapping("/profile/{userID}/attending")
	public String attendingProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> attendingConcertList = ConcertOperations.getAttendingConcerts(userID);
		model.addAttribute("page","1");
		model.addAttribute("user",user);
		model.addAttribute("concertList",attendingConcertList);
		System.out.println("Concert id:"+attendingConcertList.get(0).getId());
		return "profile";
	}
	@RequestMapping("/profile/{userID}/attended")
	public String attendedProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> attendedConcertList = ConcertOperations.getAttendedConcerts(userID);
		model.addAttribute("page","2");
		model.addAttribute("user",user);
		model.addAttribute("concertList",attendedConcertList);
		return "profile";
	}
	@RequestMapping("/profile/{userID}/thinking")
	public String thinkingProfilePage(@PathVariable("userID") int userID, Model model) {
		Users user = UserOperations.getUser(userID);
		ArrayList<Concerts> thinkingConcertList = ConcertOperations.getThinkingConcerts(userID);
		model.addAttribute("page","3");
		model.addAttribute("user",user);
		model.addAttribute("concertList",thinkingConcertList);
		return "profile";
	}
	
}
