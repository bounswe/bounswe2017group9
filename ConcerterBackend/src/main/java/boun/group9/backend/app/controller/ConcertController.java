package boun.group9.backend.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConcertController {
	@RequestMapping("/concert/{concertID}")
	public String concertPage(@PathVariable("concertID") int concertID,Model model) {
		
		return "concert";
	}
	@RequestMapping("/new-concert")
	public String newConcert(Model model) {
		
		return "new-concert";
	}
}
