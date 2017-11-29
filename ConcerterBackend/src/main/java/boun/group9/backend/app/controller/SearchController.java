package boun.group9.backend.app.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.SearchOperations;

@Controller
public class SearchController {
	@RequestMapping("/basicsearchconcert/{name}")
	public String basicSearchConcert(@PathVariable("name") String name,Model model) {
		ArrayList<Concerts> concert = SearchOperations.basicSearchConcert(name);
		for (int i = 0; i < concert.size(); i++) {
			System.out.println(concert.get(i).getName());
		}
		model.addAttribute("concertList",concert);
		return "redirect/:basicSearch";
	}
	@RequestMapping("/basicsearchuser/{name}")
	public String basicSearchUser(@PathVariable("name") String name,Model model) {
		ArrayList<Users> users = SearchOperations.basicSearchUser(name);
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getName());
		}
		model.addAttribute("userList",users);
		return "redirect/:basicSearch";
	}

	 @RequestMapping("/advancedSearchPrice/{min}/{max}")
	    public String advancedSearchPrice(@PathVariable("min") int min ,@PathVariable("max") int max, Model model) {
	        ArrayList<Concerts> concert = SearchOperations.advancedSearchPrice(min, max);
	        for (int i = 0; i < concert.size(); i++) {
	            System.out.println(concert.get(i).getName());
	        }
	        model.addAttribute("concertList",concert);
	        return "redirect/:advancedSearch";
	    }
	    
	    @RequestMapping("/advancedSearchLocation/{location}")
	    public String advancedSearchLocation(@PathVariable("location") String location , Model model) {
	        ArrayList<Concerts> concert = SearchOperations.advancedSearchLocation(location);
	        for (int i = 0; i < concert.size(); i++) {
	            System.out.println(concert.get(i).getName());
	        }
	        model.addAttribute("concertList",concert);
	        return "redirect/:advancedSearch";
	    }
	    
	    @RequestMapping("/advancedSearchDate/{startDate}/{endDate}")
	    public String advancedSearchDate(@PathVariable("startDate") String startDate ,@PathVariable("endDate") String endDate , Model model) {
	        ArrayList<Concerts> concert = SearchOperations.advancedSearchDate(startDate, endDate);
	        for (int i = 0; i < concert.size(); i++) {
	            System.out.println(concert.get(i).getName());
	        }
	        model.addAttribute("concertList",concert);
	        return "redirect/:advancedSearch";
	    }

	    @RequestMapping("advancedSearch/{startDate}/{endDate}/{min}/{max}/{location}")
		public String advancedSearchGeneral(@PathVariable(value = "min") int minPrice ,@PathVariable(value = "max") int maxPrice ,
            @PathVariable(value = "location") String location ,
            @PathVariable(value = "startDate") String start,
            @PathVariable(value = "endDate") String end , Model model) {
		
		/*
		if(end == null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			java.util.Date dateobj = new java.util.Date();
			end = df.format(dateobj);
			System.out.println(end);
		}
		if(start == null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			java.util.Date dateobj = new java.util.Date(1900-00-00);
			end = df.format(dateobj);
			System.out.println(end);
		}
		if(location == null) {
			location = "";
		}
		*/
		
		
		ArrayList<Concerts> concert = SearchOperations.advancedSearchGeneral(start, end , minPrice , maxPrice, location );
		for (int i = 0; i < concert.size(); i++) {
			System.out.println(concert.get(i).getName());
		}
		model.addAttribute("concertList",concert);
		return "redirect/:advancedSearch";
	}
	
}
