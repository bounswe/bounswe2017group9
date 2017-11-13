package boun.group9.webservice.app.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Comments;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.CommentChecker;
import boun.group9.webservice.helper.Database;

@RestController
public class CommentController {
	@RequestMapping(value="comments",method=RequestMethod.POST)
	public String createComment(@RequestBody String body) {
		String query = CommentChecker.insertCommentQuery(body);
		try {
			Database.connect(query, Application.MODE_UPDATE);
			return "Saved.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
	}
	
}
