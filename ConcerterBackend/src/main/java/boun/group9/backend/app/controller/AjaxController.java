package boun.group9.backend.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Users;
import boun.group9.backend.app.exception.NotLoggedInException;
import boun.group9.backend.app.helper.CommentOperations;

@RestController
public class AjaxController {
	@RequestMapping(value="ajax-new-comment",method=RequestMethod.POST)
	public String createComment(@RequestBody Comments comment,HttpSession session) {
		Users user = (Users)session.getAttribute("loggedInUser");
		if(user == null) {
			throw new NotLoggedInException();
		}
		comment.setCommented_by(user.getId());
		CommentOperations.createComment(comment);
		return "OK.";
	}
}
