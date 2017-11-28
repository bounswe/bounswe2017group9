package boun.group9.backend.app.controller;

import javax.management.relation.Relation;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.RelationOperations;

@Controller
public class RelationController {
    private static Application.STATUS status;
	@RequestMapping(value="/follow",method=RequestMethod.PUT)
	public ModelAndView follow(@ModelAttribute Users followinguser,HttpSession session) {
		Users user = (Users)session.getAttribute("loggedInUser"); //bu zaten follower yapacak user
		if(user==null){
			return new ModelAndView("/login");
		}
		//System.out.println(followinguser.getName());
		status = RelationOperations.follow(user.getId(), followinguser);		
		//status = RelationOperations.follow(11, followinguser);
		if(status == STATUS.SUCCESS) {
			return new ModelAndView("redirect:/profile");
		}else {
			return new ModelAndView("redirect:/profile");
		}
	}
	
	@RequestMapping(value="/unfollow",method=RequestMethod.PUT)
	public ModelAndView unfollow(@ModelAttribute Users followinguser,HttpSession session) {
		Users user = (Users)session.getAttribute("loggedInUser"); //bu zaten follower yapacak user
		if(user == null){
			return new ModelAndView("/login");
		}
		//System.out.println(followinguser.getName());
		status = RelationOperations.follow(user.getId(), followinguser);		
		//status = RelationOperations.unfollow(11, followinguser);
		if(status == STATUS.SUCCESS) {
			return new ModelAndView("redirect:/profile");
		}else {
			return new ModelAndView("redirect:/profile");
		}
	}

}
