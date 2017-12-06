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
}
