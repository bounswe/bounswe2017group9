package com.boun.nine.service.concrete;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.boun.nine.data.Comment;
import com.boun.nine.service.interfaces.ConnectedService;
import com.boun.nine.service.interfaces.ICommentResource;
import com.google.gson.Gson;
public class CommentResource extends ConnectedService implements ICommentResource{

	@Override
	public String getCommentsForConcert(int id) {
		String query = "SELECT * FROM comment WHERE concert_id="+id+";";
		ResultSet rs;
		Comment comment = new Comment();
		Gson gson = new Gson();
		try{
			rs = this.executeQuery(query);
			while(rs.next()){
				comment.setId(rs.getInt("id"));
				comment.setConcertID(rs.getInt("concert_id"));
				comment.setDateTime(rs.getDate("date_time"));
				comment.setOwner(rs.getInt("owner_id"));
				comment.setUpVote(rs.getInt("up_vote"));
				comment.setDownVote(rs.getInt("down_vote"));

			}				
			return gson.toJson(comment);
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}

}
