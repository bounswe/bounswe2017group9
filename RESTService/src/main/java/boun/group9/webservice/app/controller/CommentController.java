package boun.group9.webservice.app.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Comments;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.CommentChecker;
import boun.group9.webservice.helper.Database;

/**
 * Controller to handle operations related with comments
 * @author ffguven
 *
 */
@RestController
public class CommentController {
	
	/**
	 * HTTP POST request with body, results in creation of the comment 
	 * @param body JSON string that contains Comments object fields
	 * @return info about the status of creation of a comment
	 */
	@RequestMapping(value="newcomment",method=RequestMethod.POST)
	public String createCommentwithCategory(@RequestBody String body) {
		String query = CommentChecker.insertCommentQuerywithCategory(body); // use helper method to create new comment on db
		try {
			Database.connect(query, Application.MODE_UPDATE); // add comment to db via query
			Database.closeConnection(); // close db connection
			return "Saved.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "Not saved.";
		}
	}


	/**
	 * HTTP DELETE request with request parameter commentID will result in deletion of the specified comment from db
	 * @param commentID id of a comment that will be deleted
	 * @return info about the status of deletion of a comment
	 */
	@RequestMapping(value="comments",method=RequestMethod.DELETE)
	public String deleteComment(@RequestParam(value="id") int commentID){
		String query = CommentChecker.deleteComment(commentID); // use helper method to delete specified comment from db
		try {
			Database.connect(query, Application.MODE_UPDATE); // delete comment from db via query
			Database.closeConnection();
			return "Deleted.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "Not deleted.";
		}
	}
	/**
	 * HTTP GET request with request parameter commentID will result in upvoting the specified comment on db
	 * @param commentID id of a comment that will be upvoted
	 * @return info about the status of upvoting a comment
	 */
	@RequestMapping(value="comments/{commentID}/upVote")
	public String likeComment(@PathVariable("commentID") int commentID){
		String query = CommentChecker.likeComment(commentID); // use helper method to generate query for liking a comment specified by comment id

		try {
			Database.connect(query, Application.MODE_UPDATE); // upvote a comment on db
			Database.closeConnection();
			return "Updated.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "Not updateted.";
		}
	}

	/**
	 * HTTP GET request with request parameter commentID will result in downvoting the specified comment on db
	 * @param commentID id of a comment that will be upvoted
	 * @return info about the status of downvoting a comment
	 */
	@RequestMapping(value="comments/{commentID}/downVote")
	public String unlikeComment(@PathVariable("commentID") int commentID){
		String query = CommentChecker.unlikeComment(commentID); // use helper method to generate query for unliking a comment specified by comment id

		try {
			Database.connect(query, Application.MODE_UPDATE); // downvote a comment on db
			Database.closeConnection();
			return "Updated.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "Not updateted.";
		}
	}
	/**
	 * HTTP GET request with request parameters will result in fetching the comments which are in specific category
	 * @param category id of a comment category
	 * @param concertID id of a concert of which comments will be fetched
	 * @return JSON array of comments
	 */
	@RequestMapping(value = "concerts/{concertID}/comments/{commentCategory}", method = RequestMethod.GET)
	public String getCommentsByCategory(@PathVariable("commentCategory") int category, @PathVariable("concertID") int concertID){

		ArrayList<Comments> commentList = CommentChecker.getCommentsByCategory(concertID , category); // use helper method to get  comment list by category
		String jsonString = Application.gson.toJson(commentList); // convert comment list to json array string

		return jsonString;
	}



}
