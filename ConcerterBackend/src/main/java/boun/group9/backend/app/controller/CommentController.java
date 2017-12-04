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
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.helper.CommentOperations;
import boun.group9.backend.app.helper.ConcertOperations;
@Controller
public class CommentController {
	@RequestMapping(value="/new-comment",method=RequestMethod.POST)
	public ModelAndView createComment(@ModelAttribute Comments comment, HttpSession session) {
		Users loggedInUser = (Users)session.getAttribute("loggedInUser");
		comment.setCommented_by(loggedInUser.getId());
		Application.STATUS status = CommentOperations.createCommentwithCategory(comment);
		if(status==Application.STATUS.ERROR) {
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/index");
	}
}
