package boun.group9.webservice.app.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonSyntaxException;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.LoginUser;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.UserChecker;

@RestController
public class UserController {
	
	@RequestMapping("test")
	public String test() {
		return "Works.";
	}
	@RequestMapping(value="new-user",method=RequestMethod.POST)
	public String newUser(@RequestBody String body) {
		Users user;
		String query;
		ResultSet rs;
		try {
			user = Application.gson.fromJson(body, Users.class);
			query = UserChecker.insertUserQuery(user);
			System.out.println(query);
			rs = Database.connect(query,Application.MODE_UPDATE);
			return "OK.";
		}catch(JsonSyntaxException ex) {
			return "Invalid JSON.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Row cannot be saved.";
		}
	}
	@RequestMapping(value = "user",method = RequestMethod.POST)
	public String user(@RequestBody String body) {
		Users user;
		try {
			LoginUser loginUser = Application.gson.fromJson(body, LoginUser.class);
			String query = "SELECT * FROM Users WHERE email='"+loginUser.getEmail()+"' AND password = '"+loginUser.getPassword()+"';";
			System.out.println(query);
			ResultSet rs = Database.connect(query,Application.MODE_GET);
			if(rs.next()) { // if a user is returned i.e. username and password is correct
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setFacebook_id(rs.getString("facebook_id"));
				user.setGoogle_id(rs.getString("google_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setFollowers(rs.getInt("followers"));
				user.setFollowings(rs.getInt("followings"));
				user.setPhoto_path(rs.getString("photo_path"));
				return Application.gson.toJson(user);
			}else {
				return "Nothing returned.";
			}
		}catch(JsonSyntaxException ex) {
			return "Invalid JSON Syntax.";
		}catch(SQLException ex) {
			return "An SQL error occured.";
		}catch(NotSavedException ex) {
			return "Row cannot be saved.";
		}
				finally {
			Database.closeConnection();
		}
		
	}
}
