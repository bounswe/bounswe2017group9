package boun.group9.webservice.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Concerts;

public class ConcertChecker {
	public static String insertConcertQuery(String body) {
		return null;
	}
	public static String insertConcertQuery(Concerts concert) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String query = "INSERT INTO Concerts (name,created_by,artist,location,date_time,min_price,max_price,image_path) VALUES ('"+concert.getName()+"',"+concert.getCreated_by_id()+","+concert.getArtist_id()+","+concert.getLocation_id()+",'"+sdf.format(concert.getDate_time())+"',"+concert.getMin_price()+","+concert.getMax_price()+",'"+concert.getImage_path()+"');";
		return query;
	}
	public static String attend(int concertID,int userID) {
		String query = "INSERT INTO Attendees (concert_id,status,user_id) VALUES("+concertID+",1,"+userID+");";
		try {
			Database.connect(query, Application.MODE_UPDATE);
			return "OK.";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	public static String getNextConcertsQuery(){
		return "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, " +
				"Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, " +
				"Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, " +
				"Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, " +
				"Users.followers AS Users_followers, Users.followings AS Users_followings, " +
				"Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, " +
				"Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, " +
				"Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city " +
				"AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON " +
				"Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN " +
				"Locations ON Concerts.location = Locations.id WHERE concerts.date_time > now();";
	}

	public static String getPastConcertsQuery(){
		return "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, " +
				"Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, " +
				"Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, " +
				"Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, " +
				"Users.followers AS Users_followers, Users.followings AS Users_followings, " +
				"Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, " +
				"Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, " +
				"Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city " +
				"AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON " +
				"Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN " +
				"Locations ON Concerts.location = Locations.id WHERE concerts.date_time < now();";
	}
}
