package boun.group9.webservice.app.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.google.gson.JsonSyntaxException;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.LoginUser;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.BadRequestException;
import boun.group9.webservice.exception.IJsonSyntaxException;
import boun.group9.webservice.exception.ISQLException;
import boun.group9.webservice.exception.InternalServerException;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.exception.UserNotFoundException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.UserChecker;

@RestController
public class UserController {
	
	@RequestMapping("test")
	public String test() {
		return "Works.";
	}
	@SuppressWarnings("TransferManager")
	@RequestMapping(value="user/photo",method=RequestMethod.POST)
	public String uploadPhoto(@RequestParam("file") MultipartFile file) {
		if(file.isEmpty()) {
			return "File is empty.";
		}
		TransferManager tm = new TransferManager(new ProfileCredentialsProvider());
		return null;
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
			ex.printStackTrace();
			throw new IJsonSyntaxException();
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new ISQLException();
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			throw new InternalServerException();
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
				user.setSpotify_id(rs.getString("spotify_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setFollowers(rs.getInt("followers"));
				user.setFollowings(rs.getInt("followings"));
				user.setPhoto_path(rs.getString("photo_path"));
				user.setCreated_at(rs.getTimestamp("created_at"));
				user.setLast_login(rs.getTimestamp("last_login"));
				user.setUpdated_at(rs.getTimestamp("updated_at"));
				return Application.gson.toJson(user);
			}else {
				throw new UserNotFoundException();
			}
		}catch(JsonSyntaxException ex) {
			throw new IJsonSyntaxException();
		}catch(SQLException ex) {
			throw new ISQLException();
		}catch(NotSavedException ex) {
			throw new InternalServerException();
		}
				finally {
			Database.closeConnection();
		}
		
	}
	// bu userID birini follow etti
	@RequestMapping(value="follow/{userID}")
	public static String follow(@PathVariable(value = "userID") int userID){
		String query = UserChecker.follow(userID);
		try {
			System.out.println(query);
			Database.connect(query, Application.MODE_UPDATE);
			return "Following count is increased.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not follow.";
		}
	}
	//bu userID birini unfollow etti
	@RequestMapping(value="unfollow/{userID}")
	public static String unFollow(@PathVariable(value = "userID") int userID){
		String query = UserChecker.unFollow(userID);
		try {
			System.out.println(query);
			Database.connect(query, Application.MODE_UPDATE);
			return "Following count is decreased.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not follow.";
		}
	}
	//bu userID'yi biri follow etti
	@RequestMapping(value="followed/{userID}",method= RequestMethod.PUT)
	public static String followed(@PathVariable(value = "userID") int userID){
		String query = UserChecker.followed(userID);
		try {
			System.out.println(query);
			Database.connect(query, Application.MODE_UPDATE);
			return "Followers count is increased.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not followed.";
		}
	}
	// bu userID'yi biri unfollow etti
	@RequestMapping(value="unfollowed/{userID}",method= RequestMethod.PUT)
	public static String unFollowed(@PathVariable(value = "userID") int userID){
		String query = UserChecker.unFollowed(userID);
		try {
			System.out.println(query);
			Database.connect(query, Application.MODE_UPDATE);
			return "Followers count is decreased.";
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not followed.";
		}
	}

	@RequestMapping(value="getUser/{userID}",method= RequestMethod.GET)
	public static String getUser(@PathVariable(value = "userID") int userID){
		Users user;
		String query = UserChecker.getUser(userID);
		try {
			System.out.println(query);
			ResultSet rs = Database.connect(query,Application.MODE_GET);
			if(rs.next()) { // if a user is returned i.e. username and password is correct
				user = new Users();
				user.setId(rs.getInt("Users_id"));
				user.setSpotify_id(rs.getString("Users_spotify_id"));
				user.setName(rs.getString("Users_name"));
				user.setUsername(rs.getString("Users_username"));
				user.setEmail(rs.getString("Users_email"));
				user.setFollowers(rs.getInt("Users_followers"));
				user.setFollowings(rs.getInt("Users_followings"));
				user.setPhoto_path(rs.getString("Users_photo_path"));
				return Application.gson.toJson(user);
			}else {
				throw new UserNotFoundException();
			}
		}catch(JsonSyntaxException ex) {
			throw new IJsonSyntaxException();
		}catch(SQLException ex) {
			throw new ISQLException();
		}catch(NotSavedException ex) {
			throw new InternalServerException();
		}
		finally {
			Database.closeConnection();
		}

	}

}
