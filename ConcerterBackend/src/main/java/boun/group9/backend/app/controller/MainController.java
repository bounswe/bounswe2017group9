package boun.group9.backend.app.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.helper.ConcertOperations;

@Controller
public class MainController {

	@RequestMapping("/test")
	public String test(@RequestParam(value="name",required=false,defaultValue="World.")String name,Model model) {
		model.addAttribute("name",name);
		return "test";
	}
	@RequestMapping("/index")
	public String index(Model model) {
		ArrayList<Concerts> concertList = ConcertOperations.getAllActiveConcerts();
		return "index";
	}
}
