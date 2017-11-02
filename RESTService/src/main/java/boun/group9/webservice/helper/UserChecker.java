package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.Users;

public class UserChecker {
	public static String insertUserQuery(Users user) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		String query = "INSERT INTO Users ";
		String fieldQuery = "(";
		String valuesQuery = "VALUES(";
		if(user.getId() != 0) {
			fieldQuery+="id,";
			valuesQuery+=user.getId()+",";
		}
		if(user.getFacebook_id() != null) {
			fieldQuery+="facebook_id,";
			valuesQuery+="'"+user.getFacebook_id()+"',";
		}
		if(user.getGoogle_id() != null) {
			fieldQuery+="google_id,";
			valuesQuery+="'"+user.getGoogle_id()+"',";
		}
		if(user.getName() != null) {
			fieldQuery+="name,";
			valuesQuery+="'"+user.getName()+"',";
		}
		if(user.getEmail() != null) {
			fieldQuery += "email,";
			valuesQuery+="'"+user.getEmail()+"',";
		}
		if(user.getPassword() != null) {
			fieldQuery += "password,";
			valuesQuery+="'"+user.getPassword()+"',";
		}
			fieldQuery += "followers,";
			valuesQuery += user.getFollowers()+",";
			fieldQuery += "followings,";
			valuesQuery += user.getFollowings()+",";
		if(user.getPhoto_path() != null) {
			fieldQuery += "photo_path,";
			valuesQuery += "'"+user.getPhoto_path()+"',";
		}
		fieldQuery += "created_at,";
		valuesQuery += "'"+currentTime+"',";
		fieldQuery += "updated_at,";
		valuesQuery += "'"+currentTime+"',";
		fieldQuery += "last_login";
		valuesQuery += "'"+currentTime+"'";
		if(fieldQuery.endsWith(",")) {
			fieldQuery = fieldQuery.substring(0, fieldQuery.length()-1);
		}
		if(valuesQuery.endsWith(",")) {
			valuesQuery = valuesQuery.substring(0, valuesQuery.length()-1);
		}
		query+=fieldQuery+") "+valuesQuery+");";
		System.out.println(query);
		return query;
	}
}
