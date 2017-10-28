package boun.group9.webservice.app.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.Comments;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.ArtistChecker;
import boun.group9.webservice.helper.ConcertChecker;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.LocationChecker;

@RestController
public class ConcertController {
	@RequestMapping(value="concerts/{concertID}",method=RequestMethod.GET)
	public String getConcert(@PathVariable(value="concertID") int concertID) {
		String jsonString="";
		String query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Concerts.id="+concertID+";";
		System.out.println(query);
		ResultSet rs;
		Concerts concert;
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			if(rs.next()) {
				concert = new Concerts();
				user = new Users();
				artist = new Artists();
				location = new Locations();
				concert.setId(rs.getInt("Concerts_id"));
				concert.setName(rs.getString("Concerts_name"));
				concert.setMin_price(rs.getInt("min_price"));
				concert.setMax_price(rs.getInt("max_price"));
				concert.setRate(rs.getFloat("Concerts_rate"));
				concert.setVoter_amount(rs.getInt("Concerts_voter_amount"));
				concert.setImage_path(rs.getString("Concerts_image_path"));
				concert.setDate_time(rs.getTimestamp("Concerts_date_time"));
				user.setId(rs.getInt("Users_id"));
				user.setName(rs.getString("Users_name"));
				user.setEmail(rs.getString("Users_email"));
				user.setFollowers(rs.getInt("Users_followers"));
				user.setFollowings(rs.getInt("Users_followings"));
				user.setPhoto_path(rs.getString("Users_photo_path"));
				user.setCreated_at(rs.getTimestamp("Users_created_at"));
				user.setUpdated_at(rs.getTimestamp("Users_updated_at"));
				user.setLast_login(rs.getTimestamp("Users_last_login"));
				concert.setCreated_by(user);
				artist.setId(rs.getInt("Artists_id"));
				artist.setName(rs.getString("Artists_name"));
				concert.setArtist(artist);
				location.setId(rs.getInt("Locations_id"));
				location.setLatitude(rs.getDouble("Locations_latitude"));
				location.setLongitude(rs.getDouble("Locations_longitude"));
				location.setCity(rs.getString("Locations_city"));
				location.setAddress(rs.getString("Locations_address"));
				concert.setLocation(location);
				jsonString = Application.gson.toJson(concert,Concerts.class);
				System.out.println(jsonString);
				System.out.println(jsonString);
				return jsonString;
			}
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		return jsonString;
	}
	@RequestMapping(value="concerts/{concertID}/comments",method=RequestMethod.GET)
	public String getCommentsForConcert(@PathVariable("concertID") int concert_id) {
		ArrayList<Comments> commentList = new ArrayList<Comments>();
		String jsonString="";
		Comments comment;
		Users user;
		String query = "SELECT Comments.id AS Comments_id, Comments.up_votes AS Comments_up_votes, Comments.down_votes AS Comments_down_votes, Comments.comment, Users.id AS user_id, Users.name AS Users_name, Users.username AS Users_username , Users.photo_path AS Users_photo_path FROM Comments INNER JOIN Users ON Comments.commented_by = Users.id WHERE Comments.concert_id="+concert_id+";";
		ResultSet rs;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				comment = new Comments();
				user = new Users();
				comment.setId(rs.getInt("Comments_id"));
				comment.setConcert_id(concert_id);
				comment.setComment(rs.getString("comment"));
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("Users_name"));
				user.setUsername(rs.getString("Users_username"));
				user.setPhoto_path(rs.getString("Users_photo_path"));
				comment.setCommented_user(user);
				commentList.add(comment);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL Exception occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		jsonString = Application.gson.toJson(commentList);
		System.out.println(jsonString);
		return jsonString;
	}
	@RequestMapping(value="concerts",method = RequestMethod.GET)
	public String getConcertsForUser(@RequestParam(value="user_id",required=true) int user_id) {
		String jsonString="";
		String query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Concerts.created_by="+user_id+";";
		System.out.println(query);
		ResultSet rs;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				concert = new Concerts();
				user = new Users();
				artist = new Artists();
				location = new Locations();
				concert.setId(rs.getInt("Concerts_id"));
				concert.setName(rs.getString("Concerts_name"));
				concert.setMin_price(rs.getInt("min_price"));
				concert.setMax_price(rs.getInt("max_price"));
				concert.setRate(rs.getFloat("Concerts_rate"));
				concert.setVoter_amount(rs.getInt("Concerts_voter_amount"));
				concert.setImage_path(rs.getString("Concerts_image_path"));
				concert.setDate_time(rs.getTimestamp("Concerts_date_time"));
				user.setId(rs.getInt("Users_id"));
				user.setName(rs.getString("Users_name"));
				user.setEmail(rs.getString("Users_email"));
				user.setFollowers(rs.getInt("Users_followers"));
				user.setFollowings(rs.getInt("Users_followings"));
				user.setPhoto_path(rs.getString("Users_photo_path"));
				user.setCreated_at(rs.getTimestamp("Users_created_at"));
				user.setUpdated_at(rs.getTimestamp("Users_updated_at"));
				user.setLast_login(rs.getTimestamp("Users_last_login"));
				concert.setCreated_by(user);
				artist.setId(rs.getInt("Artists_id"));
				artist.setName(rs.getString("Artists_name"));
				concert.setArtist(artist);
				location.setId(rs.getInt("Locations_id"));
				location.setLatitude(rs.getDouble("Locations_latitude"));
				location.setLongitude(rs.getDouble("Locations_longitude"));
				location.setCity(rs.getString("Locations_city"));
				location.setAddress(rs.getString("Locations_address"));
				concert.setLocation(location);
				concertList.add(concert);
			}
			jsonString = Application.gson.toJson(concertList);
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		return jsonString;
	}
	@RequestMapping(value="concerts",method=RequestMethod.POST)
	public String createConcert(@RequestBody String body) {
		int artist_id=0;
		int location_id=0;
		ResultSet rs;
		Concerts concert = Application.gson.fromJson(body, Concerts.class);
		String artistQuery = ArtistChecker.insertArtistQuery(concert.getArtist());
		String locationQuery = LocationChecker.insertLocationQuery(concert.getLocation());
		String concertQuery;
		System.out.println(artistQuery);
		System.out.println(locationQuery);
		try {
				
				Database.connect(artistQuery, Application.MODE_UPDATE);
				/*
				rs = Database.connect("SELECT LAST_INSERT_ID();", Application.MODE_GET);
				if(rs.next()) {
					artist_id = rs.getInt("LAST_INSERT_ID()");
				}
				*/
				artist_id = Database.last_generated_id;
				Database.connect(locationQuery, Application.MODE_UPDATE);
				location_id = Database.last_generated_id;
				/*
				rs = Database.connect("SELECT LAST_INSERT_ID();", Application.MODE_GET);
				if(rs.next()) {
					location_id = rs.getInt("LAST_INSERT_ID()");
				}
				*/
				System.out.println(location_id);
				System.out.println(artist_id);
				concert.setArtist_id(artist_id);
				concert.setLocation_id(location_id);
				concertQuery = ConcertChecker.insertConcertQuery(concert);
				System.out.println(concertQuery);
				Database.connect(concertQuery, Application.MODE_UPDATE);
		}catch(SQLException ex) {
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		return "Saved.";
	}
}
