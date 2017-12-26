package boun.group9.webservice.app.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Comments;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.CommentChecker;
import boun.group9.webservice.helper.Database;

@RestController
public class CommentController {

	@RequestMapping(value="newcomment",method=RequestMethod.POST)
	public String createCommentwithCategory(@RequestBody String body) {
		String query = CommentChecker.insertCommentQuerywithCategory(body);
		System.out.println(query);
		try {
			Database.connect(query, Application.MODE_UPDATE);
			Database.closeConnection();
			return "Saved.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
	}



	@RequestMapping(value="{userID}/comments/{commentID}/deleteComment",method=RequestMethod.DELETE)
	public String deleteComment(@PathVariable(value="commentID") int commentID , @PathVariable(value = "userID") int userID){
		String query = CommentChecker.deleteComment(commentID, userID);
		try {
			System.out.println(query);
			Database.connect(query, Application.MODE_UPDATE);
			Database.closeConnection();
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
			Database.closeConnection();
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
			Database.closeConnection();
			return "Updated.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not updateted.";
		}
	}

	@RequestMapping(value = "concerts/{concertID}/comments/{commentCategory}", method = RequestMethod.GET)
	public String getCommentsByCategory(@PathVariable("commentCategory") int category, @PathVariable("concertID") int concertID){

		ArrayList<Comments> commentList = CommentChecker.getCommentsByCategory(concertID , category);
		String jsonString = Application.gson.toJson(commentList);

		return jsonString;
	}



}
