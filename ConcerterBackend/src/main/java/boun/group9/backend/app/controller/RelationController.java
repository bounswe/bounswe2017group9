package boun.group9.backend.app.controller;

import javax.management.relation.Relation;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value="/follow/{followingID}",method=RequestMethod.POST)
	public ModelAndView follow(@PathVariable int followingID,HttpSession session) {
		Application.STATUS status = RelationOperations.follow(followingID, session);
		if(status == Application.STATUS.SUCCESS) {
			return new ModelAndView("redirect:/index");
		}else {
			return new ModelAndView("redirect:/error");
		}
	}
	
	@RequestMapping(value="/unfollow/{followingID}",method=RequestMethod.POST)
	public ModelAndView unfollow(@PathVariable int followingID, HttpSession session) {
		Application.STATUS status = RelationOperations.unfollow(followingID, session);
		if(status == Application.STATUS.SUCCESS) {
			return new ModelAndView("redirect:/index");
		}else {
			return new ModelAndView("redirect:/error");
		}
	}

}
