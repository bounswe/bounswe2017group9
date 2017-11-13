package boun.group9.backend.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.Artists;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Locations;
import boun.group9.backend.app.helper.ConcertOperations;
import boun.group9.backend.app.helper.CommentOperations;
@Controller
public class ConcertController {
	private static STATUS status;
	@RequestMapping(value="/new-comment",method=RequestMethod.POST)
	public ModelAndView newComment(@ModelAttribute Comments newComment) {
		newComment.setCommented_by(7);
		System.out.println(newComment.getComment());
		System.out.println(newComment.getConcert_id());
		System.out.println(newComment.getCommented_by());
		String jsonString = Application.gson.toJson(newComment);
		status = CommentOperations.createComment(newComment);
		return new ModelAndView("redirect:/index");
	}
	@RequestMapping("/concert/{concertID}")
	public String concertPage(@PathVariable("concertID") int concertID,Model model) {
		Concerts concert = ConcertOperations.getConcert(concertID);
		model.addAttribute("concert",concert);
		return "concert";
	}
	@RequestMapping(value="/new-concert",method=RequestMethod.GET)
	public String newConcert(Model model) {
		model.addAttribute("concert",new Concerts());
		return "new-concert";
	}
	@RequestMapping(value="/new-concert",method=RequestMethod.POST)
	public String submitNewConcert(@ModelAttribute Concerts concert) {
		System.out.println(concert.getName());
		System.out.println(concert.getArtist_name());
		System.out.println(concert.getLocation_name());
		System.out.println(concert.getDate_str());
		System.out.println(concert.getTime_str());
		System.out.println(concert.getMin_price());
		System.out.println(concert.getMax_price());
		status = ConcertOperations.createConcert(concert);
		if(status == STATUS.SUCCESS) {
			return "index";
		}else {
			return "error";
		}
		
	}
}
