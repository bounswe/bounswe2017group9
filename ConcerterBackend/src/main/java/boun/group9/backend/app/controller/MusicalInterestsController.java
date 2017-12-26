package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.MusicalInterests;
import boun.group9.backend.app.data.Search;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;
import boun.group9.backend.app.helper.MusicalInterestOperations;
import boun.group9.backend.app.helper.UserOperations;

@Controller	
public class MusicalInterestsController {
	private static Application.STATUS status;
	
	
	@RequestMapping(value="/search-interests",method=RequestMethod.GET)
	public String searchInterest(Model model, HttpSession session,@RequestParam(name="search") String searchKey ){
	
		Users loggedInUser = (Users)session.getAttribute("loggedInUser");	
		ArrayList<MusicalInterests> interestList=MusicalInterestOperations.searchMusicalInterests(searchKey);
		System.out.println("search interest");
		for(int i=0;i<interestList.size();i++){
			System.out.println(interestList.get(i).getLabel());
		}
		
		if(!searchKey.equalsIgnoreCase("")){
			model.addAttribute("interestSearchList",interestList);
		}
		
		System.out.println(model.containsAttribute("interestSearchList"));
		return "redirect:/profile/"+loggedInUser.getId();
	}
	
	
	
	
	@RequestMapping(value="/new-musical-interests",method=RequestMethod.POST)
	public ModelAndView addMusicalInterests(@ModelAttribute MusicalInterests musical_interest, HttpSession session) {
		System.out.println("new Musical interests");
		Users loggedInUser = (Users)session.getAttribute("loggedInUser");
		ModelAndView model=new ModelAndView("redirect:/profile/"+loggedInUser.getId());
		status=MusicalInterestOperations.addMusicalInterests(musical_interest);
	    if(status==Application.STATUS.ERROR) {
			return new ModelAndView("redirect:/error");
		}
		
		return model;
	}
	
	@RequestMapping(value = "/deleteInterest", method=RequestMethod.GET)
    public ModelAndView deleteInterest( @RequestParam(name="interestID") String interestID , HttpSession session){
    	Users user = (Users)session.getAttribute("loggedInUser");
    	int userID = user.getId();
    	int interestid=Integer.parseInt(interestID);
        status = MusicalInterestOperations.deleteMusicalInterest(interestid, userID);
        System.out.println(status);
        if(status == Application.STATUS.SUCCESS){
        	System.out.println(interestID);
        	return new ModelAndView("redirect:/profile/"+userID);
        }else{
            return new ModelAndView("redirect:/error");
        }

    }

	
}
