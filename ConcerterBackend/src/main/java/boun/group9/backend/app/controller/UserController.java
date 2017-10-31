package boun.group9.backend.app.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@RequestMapping("/profile/{userID}")
	public String profilePage(@PathVariable("userID") int userID,Model model) {
		
		return "profile";
	}
	
}
