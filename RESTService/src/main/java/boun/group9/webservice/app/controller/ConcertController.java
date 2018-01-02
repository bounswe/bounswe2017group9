package boun.group9.webservice.app.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.Comments;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.ArtistChecker;
import boun.group9.webservice.helper.CommentChecker;
import boun.group9.webservice.helper.ConcertChecker;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.FileChecker;
import boun.group9.webservice.helper.LocationChecker;
import boun.group9.webservice.helper.NotificationChecker;

/**
 * Controller to handle operations related with comments
 * @author ffguven
 *
 */
@RestController
public class ConcertController {
	/**
	 * HTTP GET request with request parameter will result in fetching all concerts for specified concert
	 * @param concertID id of a concert of which comments will be fetched from db
	 * @return JSON array of comments
	 */
	@RequestMapping(value="concerts/{concertID}/comments",method=RequestMethod.GET)
	public String getCommentsForConcert(@PathVariable(value="concertID") int concertID) {
		ArrayList<Comments> commentList = CommentChecker.getCommentList(concertID); // fetch all comments for a concert from db
		String jsonString = Application.gson.toJson(commentList); // generate json array string from java arraylist
		return jsonString;
	}
	/**
	 * HTTP GET request with request parameter will result in fetching the specified concert by concert id
	 * @param concertID id of a concert that will be fetched from db
	 * @return JSON string of the concert object
	 */
	@RequestMapping(value="concerts/{concertID}",method=RequestMethod.GET)
	public static String getConcert(@PathVariable(value="concertID") int concertID) {
		String jsonString="";
		String query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Concerts.id="+concertID+";";
		//System.out.println(query);
		ResultSet rs;
		Concerts concert;
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET); // fetch concert specified by concert id from db
			if(rs.next()) { // if db returns any concert, then fill Concerts Java object
				concert = new Concerts();
				user = new Users();
				artist = new Artists();
				location = new Locations();
				concert.setTicket(rs.getString("Concerts_ticket"));
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
				//System.out.println(jsonString);
				rs.close();
			}
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		
		Database.closeConnection();
		return jsonString;
	}
	/**
	 * HTTP POST request with request parameters will result in rating the concert
	 * @param concertId id of a concert that will be rated
	 * @param rate past rate of the concert
	 * @return info about the status of rating the concert
	 */
	@RequestMapping(value="rate-concert",method=RequestMethod.POST)
	public String rateConcert(@RequestParam(value="id",required=true) int concertId, @RequestParam(value="rate",required=true) int rate) {
		ConcertChecker.rateConcert(concertId, rate); // use helper method to rate a concert
		return "OK.";
	}
	/**
	 * HTTP GET request with request parameters will result in fetching attending concerts for the specified user
	 * @param user_id id of a user of which attending concerts will be fetched
	 * @param status attending/attended or maybe
	 * @param created_by
	 * @return
	 */
	@RequestMapping(value="concerts",method = RequestMethod.GET)
	public String getConcertsForUser(@RequestParam(value="user_id",required=false) Integer user_id,@RequestParam(value="status",required=false) String status,@RequestParam(value="created_by",required=false) String created_by) {
		String jsonString="";
		String query;
		System.out.println(user_id);
		if(user_id != null) {
			if(status == null) { //get created concerts 
				query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Concerts.created_by="+user_id+";";
			}
			else if(status.equals("attended")) {
				query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Attendees INNER JOIN Concerts ON Attendees.concert_id = Concerts.id INNER JOIN Users ON Attendees.user_id = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Attendees.user_id="+user_id+" AND Attendees.status =2;";
			}else if(status.equals("attending")) {
				query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Attendees INNER JOIN Concerts ON Attendees.concert_id = Concerts.id INNER JOIN Users ON Attendees.user_id = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Attendees.user_id="+user_id+" AND Attendees.status =1;";
			}else {
				query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Attendees INNER JOIN Concerts ON Attendees.concert_id = Concerts.id INNER JOIN Users ON Attendees.user_id = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Attendees.user_id="+user_id+" AND Attendees.status =3;";
			}
			
		}else { //get all concerts
			query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id;";
		}
		
		ResultSet rs;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET); // fetch concerts to which specified user will attend
			while(rs.next()) { // if database returns any concerts, then fill java object with response data
				concert = new Concerts();
				user = new Users();
				artist = new Artists();
				location = new Locations();

				concert.setTicket(rs.getString("Concerts_ticket"));
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
			rs.close();
			concertList=ConcertChecker.sortByDate(concertList); // sort concerts by data in a way last added concert at the top of list
			
			jsonString = Application.gson.toJson(concertList);
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		Database.closeConnection();
		return jsonString;
	}
	/**
	 * HTTP POST request with JSON String of a Concerts object will result in creation of the concert
	 * @param body JSON string of the Concert that will be created
	 * @return info about the status of creation the concert
	 */
	@RequestMapping(value="concerts",method=RequestMethod.POST)
	public String createConcert(@RequestBody String body) {
		int artist_id=0;
		int location_id=0;
		ResultSet rs;
		Concerts concert = Application.gson.fromJson(body, Concerts.class); // create a java object with form fields json string
		String artistQuery = ArtistChecker.insertArtistQuery(concert.getArtist()); // generate query to insert artist info to db
		String locationQuery = LocationChecker.insertLocationQuery(concert.getLocation()); // generate query to insert location info to db
		String concertQuery;
		try {
				Database.connect(artistQuery, Application.MODE_UPDATE); // insert artist info object to db
				artist_id = Database.last_generated_id; // get database primary key (id) for artist object
				Database.closeConnection();
				Database.connect(locationQuery, Application.MODE_UPDATE); // insert location info object to db
				location_id = Database.last_generated_id; // get database primary key (id) for location object
				Database.closeConnection();
				concert.setArtist_id(artist_id); // fill Concerts java object with db data
				concert.setLocation_id(location_id); // fill Concerts java object with db data
				concertQuery = ConcertChecker.insertConcertQuery(concert); // generate query to insert concert info to db
				Database.connect(concertQuery, Application.MODE_UPDATE); // insert concert info object to db
				Database.closeConnection();
		}catch(SQLException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "Not saved.";
		}
		return "Saved.";
	}
	/**
	 * HTTP POST request with request parameters will result in attending to a concert specified by concertID
	 * @param concertID id of a concert to which user specified by userID will attend
	 * @param userID id of a user that will attend to a concert specified by concertID
	 * @return info about the status of attending to a concert
	 */
	@RequestMapping(value="concert/{concertID}/attendee/{userID}",method=RequestMethod.POST)
	public String attendConcert(@PathVariable int concertID, @PathVariable int userID) {
		String query=ConcertChecker.attend(concertID,userID); // generate query to attend to a concert for specified user
		String result;
		try{
			Database.connect(query, Application.MODE_UPDATE); // attend to a concert for specified user
			Database.closeConnection();
			NotificationChecker.notify(concertID,userID); // generate notifications for users who follows the attending user
			result="OK";
		}catch(SQLException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			result = "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			result = "Not saved.";
		}
		return result;
		
	}
	
	//concerts that the user attended is considered.
	@RequestMapping(value = "concert/{concert_id}/{userID}/{current_rate}" , method = RequestMethod.POST)
	public String postRateForaConcert(@PathVariable(value = "concert_id") int concert_id ,
									  @PathVariable(value = "current_rate") int current_rate,
									  @PathVariable(value = "userID") int userID ){

		String rateNumberQuery = ConcertChecker.updateRateNum(concert_id); // generate query to calculate rate of the concert
		String postRatequery = ConcertChecker.postRate(userID ,concert_id, current_rate); // generate query for inserting new rate of the concert
		try{
			Database.connect(rateNumberQuery, Application.MODE_UPDATE); // update voter_amount at db
			Database.connect(postRatequery, Application.MODE_UPDATE); // update rate value at db
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		return "Saved.";
	}
	
	/**
	 * HTTP GET request will result in fetching next available concerts 
	 * @return JSON array of the concerts that are available to attend
	 */
	@RequestMapping(value="nextconcerts",method = RequestMethod.GET)
	public String getAllNextConcerts() {
		String jsonString="";
		ResultSet rs;
		Users user;
		Artists artist;
		Locations location;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		try {
			String query = ConcertChecker.getNextConcertsQuery(); // generate query to fetch next available concerts
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) { // if response is not empty, then fill java object
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
			rs.close();
			concertList=ConcertChecker.sortByDate(concertList); // sort concert list in a way last added concert is at the top of list
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
	/**
	 * HTTP GET request will result in fetching past concerts
	 * @return JSON array of the concerts which were occured before now
	 */
	@RequestMapping(value="pastconcerts",method = RequestMethod.GET)
	public String getAllPastConcerts() {
		String jsonString="";
		ResultSet rs;
		Users user;
		Artists artist;
		Locations location;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		try {
			String query = ConcertChecker.getPastConcertsQuery(); // generate query to fetch ended concerts from db
			rs = Database.connect(query, Application.MODE_GET); // fetch ended concerts from db
			while(rs.next()) { // if response is not empty, then fill java object
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
			rs.close();
			jsonString = Application.gson.toJson(concertList); // generate json array string that contains concert list
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			Database.closeConnection();
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
			return "Not saved.";
		}


		
		return jsonString;
	}


/**
 * HTTP POST request with request parameters will result in rating the specified concert
 * @param concert_id id of a concert that will be rated 
 * @param current_rate rate of the concert for before voting
 * @return info about the status of rating a concert
 */
	@RequestMapping(value = "concert/{concert_id}/{current_rate}" , method = RequestMethod.POST)
	public String postConcertRate(@PathVariable(value = "concert_id") int concert_id , @PathVariable(value = "current_rate") int current_rate){
		String rateNumberQuery = ConcertChecker.updateRateNumber(concert_id);
		String postRatequery = ConcertChecker.postConcertRate(concert_id, current_rate);

		//System.out.println(rateNumberQuery);
		//System.out.println(postRatequery);

		try{
			Database.connect(rateNumberQuery, Application.MODE_UPDATE);
			Database.closeConnection();
			Database.connect(postRatequery, Application.MODE_UPDATE);
			Database.closeConnection();
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		return "Saved.";
	}
	/**
	 * HTTP GET requst will result in fetching active concerts
	 * @return JSON array of Java object of concerts
	 */
	@RequestMapping(value="active-concerts",method=RequestMethod.GET)
	public static String allActiveConcerts() {
		String jsonString="";
		String query;
		String result;
		query = "SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Concerts.date_time>CURDATE();";
		ResultSet rs;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET); // fetch all active (not ended) concerts from db
			while(rs.next()) {
				concert = new Concerts();
				user = new Users();
				artist = new Artists();
				location = new Locations();

				concert.setTicket(rs.getString("Concerts_ticket"));
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
			rs.close();
			jsonString = Application.gson.toJson(concertList);
			Database.closeConnection();
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			Database.closeConnection();
			jsonString = "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			Database.closeConnection();
				jsonString = "Not saved.";
		}finally {
			Database.closeConnection();
		}
		return jsonString;
	}
}
