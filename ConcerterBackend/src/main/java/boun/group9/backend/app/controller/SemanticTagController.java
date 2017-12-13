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
import boun.group9.backend.app.data.AdvancedSearch;
import boun.group9.backend.app.data.Artists;
import boun.group9.backend.app.data.Attend;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Search;
import boun.group9.backend.app.data.SemanticTag;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.SearchOperations;
import boun.group9.backend.app.helper.SemanticTagOperations;
@Controller
public class SemanticTagController {
	@RequestMapping(value="/semantic-tag-wiki",method=RequestMethod.GET)
//	public String wikiData(@RequestParam(name="search") String searchKey ,Model model,HttpSession session ) {
		public String wikiData(Model model,HttpSession session,@RequestParam(name="search") String searchKey ) {
		System.out.println("semant "+model.toString());	
		searchKey="metal";
		System.out.println("semantic tag list");
		ArrayList<SemanticTag> tagList = SemanticTagOperations.getSemanticTags(searchKey);
		System.out.println("semantic tag list");
		for(int i=0;i<tagList.size();i++){
			System.out.println(tagList.get(i).getLabel());
		}
		model.addAttribute("tagList",tagList);
		model.addAttribute("search",new Search());
		session.setAttribute("tagSearch",searchKey);
		return "redirect:/index";
	}
	
	
	
	@RequestMapping(value="tags",method=RequestMethod.GET)
	public String semantictag(Model model, HttpSession session,@RequestParam(name="search") String searchKey ){
		if(searchKey!=null){
			session.setAttribute("tagSearch",searchKey);
		}else{
			session.setAttribute("tagSearch","");
		}
		
		ArrayList<SemanticTag> tagList = SemanticTagOperations.getSemanticTags(searchKey);
		/*for(int i=0;i<tagList.size();i++){
			System.out.println(tagList.get(i).getLabel());
			System.out.println(tagList.get(i).getConcert_id());
			System.out.println(tagList.get(i).getDescription());
			System.out.println(tagList.get(i).getId());
			System.out.println(tagList.get(i).getSearch());
			System.out.println(tagList.get(i).getSemanticTagId());
			
		}*/
		model.addAttribute("tagList",tagList);
		model.addAttribute("search",new Search());
		
		return "redirect:/index";
	}
	

}
