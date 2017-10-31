package boun.group9.backend.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.helper.ConcertOperations;

@Controller
public class ConcertController {
	@RequestMapping("/concert/{concertID}")
	public String concertPage(@PathVariable("concertID") int concertID,Model model) {
		Concerts concert = ConcertOperations.getConcert(concertID);
		return "concert";
	}
	@RequestMapping("/new-concert")
	public String newConcert(Model model) {
		
		return "new-concert";
	}
}
