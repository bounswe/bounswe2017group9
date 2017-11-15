package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;

import org.springframework.http.HttpMethod;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	private static STATUS status;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model) {
		Users user = new Users();
		model.addAttribute("user",user);
		return "login-signup";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute Users user,HttpSession session,Model model) {
		user = UserOperations.login(user);
		session.setAttribute("loggedInUser",user);
		if(user == null) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("redirect:/index");
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signup(Model model) {
		return "login-signup";
	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute Users user) {
		UserOperations.signUp(user);
		return new ModelAndView("redirect:/login");
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
