package com.boun.nine.service.concrete;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.boun.nine.data.Comment;
import com.boun.nine.data.Concert;
import com.boun.nine.service.interfaces.ConnectedService;
import com.boun.nine.service.interfaces.ICommentResource;
import com.boun.nine.service.interfaces.IConcertResource;
import com.google.gson.Gson;

public class CommentResource extends ConnectedService implements ICommentResource{

	@Override
	public String writeComment(String body) {
		Comment comment = new Comment();
		Gson g = new Gson();
		String query = "INSERT INTO comment (";
		String values = " VALUES (";
		comment = g.fromJson(body,Comment.class);
		if(comment.getId() != 0){
			query+="id,";
			values += comment.getId()+",";
		}
		if(comment.getOwnerId() != 0){
			query+="owner_id,";
			values += comment.getOwnerId()+",";
		}
		if(comment.getConcertId() != 0){
			query+="owner_id,";
			values += comment.getOwnerId()+",";
		}
		if(comment.getUpVote() != 0){
			query += "rate,";
			values += comment.getUpVote();
		}
		if(query.endsWith(",")){
			query = query.substring(0, query.length()-1);
		}
		query += ")";
		if(values.endsWith(",")){
			values = values.substring(0, values.length()-1);
		}
		query += values+");";
		try{
			this.executeUpdate(query);
		}catch(SQLException ex){
			ex.printStackTrace();
			return "404";
		}
		
		return query;
	}

	@Override
	public String getAllComments() {
		// TODO Auto-generated method stub
		String test = "getAllComments works!";
		return test;
	}

}
