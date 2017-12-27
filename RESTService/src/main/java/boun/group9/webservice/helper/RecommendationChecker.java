package boun.group9.webservice.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.controller.ConcertController;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.Users;
import ch.qos.logback.core.net.SyslogOutputStream;

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
				query+="Concerts.id="+concertIdList.get(i)+" OR ";
			}
			query = query.substring(0,query.length()-3)+";";
			System.out.println(query);

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
	public static ArrayList<Concerts> recommend2(int userID){
		int score;
		int status,concert_id;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		ArrayList<Integer> followingList = new ArrayList<Integer>();
		Map<Integer,Integer> concertL = new HashMap<Integer, Integer>();
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
			query = "SELECT concert_id, status from Attendees WHERE ";
			for(int i=0;i<followingList.size();i++) {
				if(i != followingList.size()-1) {
					query+="user_id="+followingList.get(i)+" OR ";
				}else {
					query+="user_id="+followingList.get(i)+";";
				}
			}
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				score=0;
				concert_id=rs.getInt("concert_id");
				status=rs.getInt("status");
				if(status==1)score=2;
				else score=1;
				if(concertL.get(concert_id)!=null)
					score+=concertL.get(concert_id);
				concertL.put(concert_id, score);
			}
			Map<Integer, Integer> newMap =
					concertL.entrySet().stream()
							.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
							.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
									(e1, e2) ->e1, LinkedHashMap::new));
			query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE ";
			for(int key : newMap.keySet()) {
				query+="Concerts.id="+ key +" OR ";
			}
			query = query.substring(0,query.length()-3)+";";
			System.out.println(query);

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
	public static ArrayList<Concerts> recommend3(int userID){
		String query,tag_id;
		int concert_id,status,score;
		score=0;
		Map<Integer, Integer> recommendations=new HashMap<Integer, Integer>();
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Map<String, Integer> tags=new HashMap<String, Integer>();
		ArrayList<Integer> userconcerts=new ArrayList<Integer>();
		ResultSet rs,rs2,rs3;
		Concerts concert;
		Users user;
		Artists artist;
		Locations location;
		query="SELECT concert_id From Attendees WHERE user_id="+userID;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				concert_id=rs.getInt("concert_id");
				userconcerts.add(concert_id);
				query="SELECT SemanticTagId FROM SemanticTags WHERE concert_id="+concert_id;
				rs2 = Database.connect(query, Application.MODE_GET);
				while(rs2.next()) {
					score=1;
					tag_id=rs2.getString("SemanticTagId");
					if(tags.get(tag_id)!=null) {
						score=tags.get(tag_id)+1;
					}
					tags.put(tag_id, score);
				}
			}
			System.out.println(query);
			System.out.println(tags.size());
			for(String key:tags.keySet()){
				System.out.println(key + ", " + tags.get(key));
			}
			for(String tag:tags.keySet()) {
				query="SELECT SemanticTags.concert_id As concert_id FROM SemanticTags INNER JOIN Concerts ON Concerts.id=SemanticTags.concert_id WHERE SemanticTags.SemanticTagId=\""+tag+"\" AND Concerts.date_time > now();";
				rs3 = Database.connect(query, Application.MODE_GET);
				while(rs3.next()) {
					concert_id=rs3.getInt("concert_id");
					//System.out.println("concert : " + concert_id);
					if(userconcerts.contains(concert_id)) continue;
					score=tags.get(tag);
					if(recommendations.get(concert_id)!=null) {
						score+=recommendations.get(concert_id);
					}
					recommendations.put(concert_id,score);
				}
			}
			Map<Integer, Integer> newMap =
					recommendations.entrySet().stream()
							.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
							.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
									(e1, e2) ->e1, LinkedHashMap::new));
			query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE ";
			for(int key : newMap.keySet()) {
				query+="Concerts.id="+ key +" OR ";
			}
			System.out.println("recommendations : " + recommendations.size());
			query = query.substring(0,query.length()-3)+";";
			System.out.println(query);

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
	public static ArrayList<Concerts> recommend4(int userID){
		String query,tag_id, user_id;
		int concert_id,status,score;
		score=0;
		Map<Integer, Integer> recommendations=new HashMap<Integer, Integer>();
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Map<String, Integer> tags=new HashMap<String, Integer>();
		Map<String, Integer> users=new HashMap<String, Integer>();

		ArrayList<Integer> userconcerts=new ArrayList<Integer>();
		ResultSet rs,rs2,rs3;
		Concerts concert;
		Users user;
		Artists artist;
		Locations location;
		query="SELECT concert_id From Attendees WHERE user_id="+userID;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				concert_id=rs.getInt("concert_id");
				userconcerts.add(concert_id);
				query="SELECT user_id FROM Attendees WHERE concert_id="+concert_id;
				rs2 = Database.connect(query, Application.MODE_GET);
				while(rs2.next()) {
					score=1;
					user_id=rs2.getString("user_id");
					if(users.get(user_id)!=null) {
						score=users.get(user_id)+1;
					}
					users.put(user_id, score);
				}
			}
			for(String userid:users.keySet()) {
				query="SELECT concert_id FROM Attendees WHERE user_id = " + userid;
				rs3 = Database.connect(query, Application.MODE_GET);
				while(rs3.next()) {
					concert_id=rs3.getInt("concert_id");
					//System.out.println("concert : " + concert_id);
					if(userconcerts.contains(concert_id)) continue;
					score=users.get(userid);
					if(recommendations.get(concert_id)!=null) {
						score+=recommendations.get(concert_id);
					}
					recommendations.put(concert_id,score);
				}
			}
			Map<Integer, Integer> newMap =
					recommendations.entrySet().stream()
							.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
							.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
									(e1, e2) ->e1, LinkedHashMap::new));
			query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id WHERE ";
			for(int key : newMap.keySet()) {
				query+="Concerts.id="+ key +" OR ";
			}
			System.out.println("recommendations : " + recommendations.size());
			query = query.substring(0,query.length()-3)+";";
			System.out.println(query);

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