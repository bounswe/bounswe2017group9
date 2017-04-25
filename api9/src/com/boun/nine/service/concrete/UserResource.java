package com.boun.nine.service.concrete;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.boun.nine.data.Concert;
import com.boun.nine.data.User;
import com.boun.nine.service.interfaces.ConnectedService;
import com.boun.nine.service.interfaces.IUserResource;
import com.google.gson.Gson;


public class UserResource extends ConnectedService implements IUserResource{

	@Override
	public String login(String email, String password) {
		String query = "SELECT * FROM user WHERE email="+email+",password="+password+";";
		ResultSet rs;
		Gson gson = new Gson();
		User user=new User();
		try{
			rs = this.executeQuery(query);
			while(rs.next()){
				user.setId(rs.getInt("id"));
				user.setFacebook_id(rs.getInt("facebook_id"));
				user.setGoogle_id(rs.getInt("google_id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhoto_path(rs.getString("photo_path"));
				
			}
			return gson.toJson(user);
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}

	
	
	// it should be a check whether this email has already been used 
	@Override
	public String signUp(String body) {
		User user=new User();
		Gson g = new Gson();
		String query = "INSERT INTO user (";
		String values = " VALUES (";
		user=g.fromJson(body,User.class);
		/*
		if(user.getId() != 0){
			query+="id,";
			values += user.getId()+",";
		}*/
		if(user.getFacebook_id() != 0){
			query+="facebook_id,";
			values += user.getFacebook_id()+",";
		}
		if(user.getGoogle_id() != 0){
			query+="google_id,";
			values += user.getGoogle_id()+",";
		}
		if(user.getName() != null || user.getName() != ""){
			query+="name,";
			values += "'"+user.getName()+"',";
		}
		if(user.getUsername() != null || user.getUsername() != ""){
			query+="username,";
			values += "'"+user.getUsername()+"',";
		}
		if(user.getEmail() != null || user.getEmail() != ""){
			query+="email,";
			values += "'"+user.getEmail()+"',";
		}
		if(user.getPassword() != null || user.getPassword() != ""){
			query+="password,";
			values += "'"+user.getPassword()+"',";
		}
		/*
		if(user.getPhoto_path() != null || user.getPhoto_path() != ""){
			query+="photo_path,";
			values += "'"+user.getPhoto_path()+"',";
		}*/
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

	
}
