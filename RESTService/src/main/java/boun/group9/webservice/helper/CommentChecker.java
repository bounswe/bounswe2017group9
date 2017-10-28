package boun.group9.webservice.helper;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Comments;

public class CommentChecker {
	public static String insertCommentQuery(String jsonString) {
		Comments comment = Application.gson.fromJson(jsonString, Comments.class);
		String query = "INSERT INTO Comments (comment,commented_by,concert_id) VALUES ( ";
		query+="'"+comment.getComment()+"',";
		query+=comment.getCommented_by()+",";
		query+=comment.getConcert_id()+")";
		System.out.println(query);
		return query;
	}
}
