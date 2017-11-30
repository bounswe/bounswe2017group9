package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
