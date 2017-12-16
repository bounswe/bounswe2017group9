package boun.group9.backend.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.SemanticTag;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.SemanticTagOperations;
@Controller
public class SemanticTagController {
	@RequestMapping(value="/semantic-tag-wiki",method=RequestMethod.GET)
	public String wikiData(Model model,HttpSession session ) {
		String searchKey="rock";
		System.out.println("semantic tag list");
		ArrayList<SemanticTag> tagList = SemanticTagOperations.getSemanticTags(searchKey);
		System.out.println("semantic tag list");
		for(int i=0;i<tagList.size();i++){
			System.out.println(tagList.get(i).getLabel());
		}
		model.addAttribute("tagList",tagList);
		return "index";
	}
	
	@RequestMapping(value = "/semantictagwiki", method=RequestMethod.GET)
    public ModelAndView semantic( @RequestParam(name="search") String search , HttpSession session){
		
		ArrayList<SemanticTag> tagList = SemanticTagOperations.getSemanticTags(search);
		for(int i=0;i<tagList.size();i++){
			System.out.println(tagList.get(i).getLabel());
		}
        	return new ModelAndView("redirect:/index");
        

    }
	

}
