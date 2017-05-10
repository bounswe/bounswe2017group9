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
		if(comment.getOwnerId() != 0){
			query+="owner_id,";
			values += "'"+comment.getOwnerId()+"',";
		}
		if(comment.get_up_vote() != 0){
			query += "up_vote,";
			values += comment.get_up_vote()+",";
		}
		if(comment.get_down_vote() != 0){
			query += "down_vote,";
			values += comment.get_down_vote()+",";
		}
		if(comment.getContent() != null || comment.getContent() != ""){
			query += "content,";
			values += "'"+comment.getContent()+"',";
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
		String query = "SELECT * FROM comment";
		ResultSet rs;
		Comment comment = new Comment();
		Gson gson = new Gson();
		String json = new String();
		json += "[";
		try{
			rs = this.executeQuery(query);
			while(rs.next()){
				comment.setId(rs.getInt("id"));
				comment.setContent(rs.getString("content"));
				comment.setOwnerId(rs.getInt("owner_id"));
				comment.set_up_vote(rs.getInt("up_vote"));
				comment.set_down_vote(rs.getInt("down_vote"));
				json += gson.toJson(comment)+",";
			}
			json = json.substring(0, json.length()-1);
			json += "]";
			return json;
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
		
	}

}
