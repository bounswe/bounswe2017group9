package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.AdvancedSearch;
import boun.group9.backend.app.data.Artists;
import boun.group9.backend.app.data.Attend;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Search;
import boun.group9.backend.app.data.SemanticTag;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.SearchOperations;
import boun.group9.backend.app.helper.SemanticTagOperations;
@Controller
public class SemanticTagController {
	private static Application.STATUS status;
	
	@RequestMapping(value="tags",method=RequestMethod.GET)
	public String semantictag(Model model, HttpSession session,@RequestParam(name="search") String searchKey ){
		//System.out.println("searchKey "+searchKey);
		if(!searchKey.equalsIgnoreCase("")){
			session.setAttribute("tagSearch",searchKey);
		}else{
			session.removeAttribute("tagSearch");
		}
		
		//ArrayList<SemanticTag> tagList = SemanticTagOperations.getSemanticTags(searchKey);
		/*for(int i=0;i<tagList.size();i++){
			System.out.println(tagList.get(i).getLabel());
			System.out.println(tagList.get(i).getConcert_id());
			System.out.println(tagList.get(i).getDescription());
			System.out.println(tagList.get(i).getId());
			System.out.println(tagList.get(i).getSearch());
			System.out.println(tagList.get(i).getSemanticTagId());
			
		}*/
		model.addAttribute("search",new Search());
		
		return "redirect:/index";
	}
	
	
	
	@RequestMapping(value="/new-tag",method=RequestMethod.POST)
	public ModelAndView createTag(@ModelAttribute SemanticTag semanticTag, HttpSession session) {
		ModelAndView model=new ModelAndView("redirect:/index");
		Users loggedInUser = (Users)session.getAttribute("loggedInUser");
	    status = SemanticTagOperations.addSemanticTag(semanticTag);
	    if(status==Application.STATUS.ERROR) {
			return new ModelAndView("redirect:/error");
		}
		
		return model;
	}
	
	
	@RequestMapping("/concert/{concertID}/tags")
	public String getAllTags(@PathVariable("concertID") int concertID,Model model) {
		ArrayList<SemanticTag> tagList = SemanticTagOperations.getTagsByConcertID(concertID);
		model.addAttribute("semanticTagList",tagList);
		return "concert";
	}
	

}
