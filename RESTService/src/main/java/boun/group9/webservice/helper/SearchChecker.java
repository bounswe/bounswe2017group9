package boun.group9.webservice.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.AdvancedSearch;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.NotSavedException;

public class SearchChecker {
    public static ArrayList<Users> searchUsers(String searchKey) {
    	String query = "SELECT * FROM Users WHERE name LIKE '%"+searchKey.trim()+"%' OR email LIKE '%"+searchKey.trim()+"%' OR username LIKE '%"+searchKey.trim()+"';";
    	System.out.println(query);
    	ArrayList<Users> result = new ArrayList<Users>();
    	Users user;
    	ResultSet rs;
    	try {
    		rs = Database.connect(query, Application.MODE_GET);
    		while(rs.next()) {
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
				result.add(user);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	Database.closeConnection();
    	return result;
    }
    public static ArrayList<Concerts> searchConcerts(String searchKey){
    	ArrayList<Concerts> concertList = new ArrayList<Concerts>();
    	String jsonString;
    	String query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE Concerts.name LIKE '%"+searchKey+"%' OR Locations.city LIKE '%"+searchKey+"%';";
		System.out.println(query);
		ResultSet rs;
		Concerts concert;
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
				jsonString = Application.gson.toJson(concert,Concerts.class);
				System.out.println(jsonString);
				System.out.println(jsonString);
				concertList.add(concert);
			}
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return null;
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return null;
		}
		return concertList;
    }
    public static ArrayList<Artists> searchArtists(String searchKey){
    	String query = "SELECT * FROM Artists WHERE name LIKE '%"+searchKey.trim()+"%';";
    	System.out.println(query);
    	ArrayList<Artists> result = new ArrayList<Artists>();
    	Artists artist;
    	ResultSet rs;
    	try {
    		rs = Database.connect(query, Application.MODE_GET);
    		while(rs.next()) {
    			artist = new Artists();
    			artist.setId(rs.getInt("id"));
    			artist.setName(rs.getString("name"));
    			result.add(artist);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return result;
    }
    public static ArrayList<Concerts> advancedSearch(AdvancedSearch as ){
    	ResultSet rs;
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	Date date;
    	ArrayList<Concerts> concertList = new ArrayList<Concerts>();
    	String query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE ";;
    	if(as.getName() != "" && as.getName() != null) {
    		query+="Concerts.name LIKE '%"+as.getName()+"%' AND ";
    	}
    	if(as.getMin_price() != 0) {
    		query+="Concerts.min_price >= "+as.getMin_price()+" AND ";
    	}
    	if(as.getMax_price() != 0) {
    		query+="Concerts.max_price <= "+as.getMax_price()+" AND ";
    	}
    	if(as.getArtist_name() != "" && as.getArtist_name() != null) {
    		query+="Artists.name LIKE '%"+as.getArtist_name()+"%' AND ";
    	}
    	if(as.getLocation_name() != "" && as.getLocation_name() != null) {
    		query+="Locations.city LIKE '%"+as.getLocation_name()+"%' AND ";
    	}
    	if(as.getStart_date_str() != "" && as.getStart_date_str() != null) {
    		if(as.getStart_time_str() != "" && as.getStart_time_str() != null) {
    			try {
    				date = sdf.parse(as.getStart_date_str() + " "+as.getStart_time_str());
    				query+="DATE_FORMAT(Concerts.date_time,'%d/%m/%Y %H:%i') >= '"+sdf.format(date)+"' AND ";
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    		}else {
    			try {
    				date = sdf.parse(as.getStart_date_str()+" "+"00:00");
    				query+="DATE_FORMAT(Concerts.date_time,'%d/%m/%Y %H:%i') >= '"+sdf.format(date)+"' AND ";
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    		}
    	}
    	if(as.getEnd_date_str() != "" && as.getEnd_date_str() != null) {
    		if(as.getEnd_time_str() != "" && as.getEnd_time_str() != null) {
    			try {
    				date = sdf.parse(as.getEnd_date_str() + " "+as.getEnd_time_str());
    				query+="DATE_FORMAT(Concerts.date_time,'%d/%m/%Y %H:%i') <= '"+sdf.format(date)+"' AND ";
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    		}else {
    			try {
    				date = sdf.parse(as.getEnd_date_str()+" "+"00:00");
    				query+="DATE_FORMAT(Concerts.date_time,'%d/%m/%Y %H:%i') <= '"+sdf.format(date)+"' AND ";
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
    		}
    	}
    	query = query.substring(0,query.length()-4)+";";
    	System.out.println(query);
    	Concerts concert;
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
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return concertList;
    }
}

