package boun.group9.webservice.helper;

import java.sql.ResultSet;
import java.util.ArrayList;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;

public class RecommendationChecker {
	public static ArrayList<Concerts> recommend(int userID){
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		ArrayList<Integer> followingList = new ArrayList<Integer>();
		ArrayList<Integer> concertIdList = new ArrayList<Integer>();
		String query = "SELECT following_id from Relations WHERE follower_id="+userID+";";
		Concerts concert;
		Users user;
		Artists artist;
		Locations location;
		ResultSet rs;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				followingList.add(rs.getInt("following_id"));
			}
			query = "SELECT concert_id from Attendees WHERE ";
			for(int i=0;i<followingList.size();i++) {
				if(i != followingList.size()-1) {
					query+="user_id="+followingList.get(i)+" OR ";
				}else {
					query+="user_id="+followingList.get(i)+";";
				}
			}
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				concertIdList.add(rs.getInt("concert_id"));
			}
			query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE ";
			for(int i=0;i<concertIdList.size();i++) {
				if(i!=followingList.size()-1) {
					query+="Concerts.id="+concertIdList.get(i)+" OR ";
				}else {
					query+="Concerts.id="+concertIdList.get(i)+";";
				}
			}
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
