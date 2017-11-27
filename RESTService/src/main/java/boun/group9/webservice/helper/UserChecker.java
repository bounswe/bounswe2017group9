package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.Users;

public class UserChecker {
	public static String getUser(int userID){
		String query = "select Users.spotify_id as Users_spotify_id  ,  Users.id as Users_id , Users.name as Users_name, Users.email as Users_email, Users.followers as Users_followers , Users.followings as Users_followings, Users.photo_path as Users_photo_path, Users.username as Users_username from users where id = "+ userID+";" ;
		return query;
	}
	// bu userID  birini follow ederse
	public static String follow(int userID){
		String query = "Update users SET followings = followings + 1 WHERE users.id = "+ userID +  ";";
		return query;
	}

	// bu userID  birini unfollow ederse
	public static String unFollow(int userID){
		String query = "Update users SET followings = followings - 1 WHERE users.id = "+ userID +  ";";
		return query;
	}

	// bu userID'yi  biri follow ederse
	public static String followed(int userID){
		String query = "Update users SET followers = followers + 1 WHERE users.id = "+ userID +  ";";
		return query;
	}

	// bu userID'yi  biri unfollow ederse
	public static String unFollowed(int userID){
		String query = "Update users SET followers = followers - 1 WHERE users.id = "+ userID +  ";";
		return query;
	}


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
		if(user.getName() != null) {
			fieldQuery+="name,";
			valuesQuery+="'"+user.getName()+"',";
		}
		if(user.getUsername() != null) {
			fieldQuery+="username,";
			valuesQuery+="'"+user.getUsername()+"',";
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
