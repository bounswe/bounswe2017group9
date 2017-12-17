package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.AdvancedSearch;
import boun.group9.backend.app.data.Artists;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Search;
import boun.group9.backend.app.data.SearchResult;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.SearchOperations;

@Controller
public class SearchController {
	@RequestMapping(value="basic-search",method=RequestMethod.POST)
	public String basicSearch(@ModelAttribute Search search,Model model,HttpSession session) {
		//System.out.println("basic search");
		SearchResult searchResult = SearchOperations.basicSearchConcert(search.getSearchKey());
		model.addAttribute("concertList",searchResult.concerts);
		model.addAttribute("userList",searchResult.users);
		model.addAttribute("artistList",searchResult.artists);
		ArrayList<Concerts> concertList = ConcertOperations.getAllActiveConcerts();
		model.addAttribute("newComment",new Comments());
		Users user = (Users)session.getAttribute("loggedInUser");
		model.addAttribute("loggedInUser",user);
		model.addAttribute("search",new Search());
		return "search-result";
	}
	@RequestMapping(value="advanced-search",method=RequestMethod.GET)
	public String advancedSearch(HttpSession session,Model model) {
		model.addAttribute("advancedSearch",new AdvancedSearch());
		model.addAttribute("search",new Search());
		model.addAttribute("loggedInUser",(Users)session.getAttribute("loggedInUser"));
		return "advanced-search";
	}
	@RequestMapping(value="advanced-search",method=RequestMethod.POST)
	public String doAdvancedSearch(@ModelAttribute AdvancedSearch search,HttpSession session,Model model) {
		String json = Application.gson.toJson(search,AdvancedSearch.class);
		ArrayList<Concerts> concertList = SearchOperations.advancedSearch(search);
		model.addAttribute("concertList",concertList);
		model.addAttribute("userList",new ArrayList<Users>());
		model.addAttribute("artistList",new ArrayList<Artists>());
		model.addAttribute("newComment",new Comments());
		model.addAttribute("search",new Search());
		model.addAttribute("loggedInUser",(Users)session.getAttribute("loggedInUser"));
		System.out.println(json);
		
		return "search-result";
	}
}
