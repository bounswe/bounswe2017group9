package boun.group9.webservice.app.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;

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


	@RequestMapping(value="comments/{commentID}/deleteComment")
	public String deleteComment(@PathVariable(value="commentID") int commentID){
		String query = CommentChecker.deleteComment(commentID);
		try {
			System.out.println(query);
			Database.connect(query, Application.MODE_UPDATE);
			return "Deleted.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not deleted.";
		}
	}


	@RequestMapping(value="comments/{commentID}/upVote")
	public String likeComment(@PathVariable("commentID") int commentID){
		String query = CommentChecker.likeComment(commentID);

		try {
			Database.connect(query, Application.MODE_UPDATE);
			return "Updated.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not updateted.";
		}
	}


	@RequestMapping(value="comments/{commentID}/downVote")
	public String unlikeComment(@PathVariable("commentID") int commentID){
		String query = CommentChecker.unlikeComment(commentID);

		try {
			Database.connect(query, Application.MODE_UPDATE);
			return "Updated.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not updateted.";
		}
	}



}
