package boun.group9.webservice.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import boun.group9.webservice.helper.WikiDataUtility;
import com.sun.xml.internal.bind.v2.util.QNameMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  
import org.json.simple.JSONValue;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonSyntaxException;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Artists;
import boun.group9.webservice.app.data.ConcertTags;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.Locations;
import boun.group9.webservice.app.data.SemanticTags;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.IJsonSyntaxException;
import boun.group9.webservice.exception.ISQLException;
import boun.group9.webservice.exception.InternalServerException;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.SemanticTagsChecker;


@RestController
public class SemanticTagsController {
	@RequestMapping(value="new-semantictag",method=RequestMethod.POST)
	public String newsemanticTag(@RequestBody String body) {
		SemanticTags tag;
		String query;
		ResultSet rs;
		try {
			tag = Application.gson.fromJson(body, SemanticTags.class);
			query = SemanticTagsChecker.insertSemanticTagsQuery(tag);
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

	@RequestMapping(value="semantic-tags/{concertID}",method=RequestMethod.GET)
	public String getsemanticTags(@PathVariable(value="concertID") int concertID) {
		String jsonString="";
		String query="SELECT * FROM SemanticTags WHERE concert_id="+concertID+";";
		System.out.println(query);
		ResultSet rs;
		SemanticTags tag;
		ArrayList<SemanticTags> tagList = new ArrayList<SemanticTags>();
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				tag = new SemanticTags();
				tag.setId(rs.getInt("id"));
				tag.setSemanticTagId(rs.getString("semanticTagId"));
				tag.setConcert_id(rs.getInt("concert_id"));
				tag.setLabel(rs.getString("label"));
				tag.setDescription(rs.getString("description"));
				tagList.add(tag);
			}
			jsonString = Application.gson.toJson(tagList);
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
	@RequestMapping(value="semantictags/{tagID}",method=RequestMethod.GET)
	public String getsemanticTag(@PathVariable(value="tagID") String tagID) {
		String jsonString="";
		String query="SELECT * FROM SemanticTags WHERE id=\""+tagID+"\";";
		System.out.println(query);
		ResultSet rs;
		SemanticTags tag;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			if(rs.next()) {
				tag = new SemanticTags();
				tag.setId(rs.getInt("id"));
				tag.setSemanticTagId(rs.getString("semanticTagId"));
				tag.setConcert_id(rs.getInt("concert_id"));
				tag.setLabel(rs.getString("label"));
				tag.setDescription(rs.getString("description"));
				jsonString = Application.gson.toJson(tag);
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

	@SuppressWarnings("TryWithIdenticalCatches")
	@RequestMapping(value="searchWikidata/{search}",method=RequestMethod.GET)
	public String getSementicTagsFromWikidata(@PathVariable(value="search") String search) {
		String jsonString="";
		ArrayList<SemanticTags> tagList = new ArrayList<SemanticTags>();
		SemanticTags tag;
		try {
			JSONArray result= WikiDataUtility.searchData(search);
		    Iterator i = result.iterator();
		    while (i.hasNext()) {
		    		JSONObject innerObj = (JSONObject) i.next();
		    		tag=new SemanticTags();
		    		tag.setSemanticTagId(innerObj.get("id").toString());
		    		tag.setLabel(innerObj.get("label").toString());
		    		tag.setSearch(search);
		    		tag.setDescription(innerObj.get("description").toString());
		    		tagList.add(tag);

			}

		    jsonString = Application.gson.toJson(tagList);

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return jsonString;
	}


	@RequestMapping(value="search-tags/{tagID}",method=RequestMethod.GET)
	public String searchSemanticTag(@PathVariable(value="tagID") String tagID) {
		String jsonString="";
		String query;
		query = "SELECT Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, Artists.id AS Artists_id, Artists.name AS Artists_name, Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id INNER JOIN Locations ON Concerts.location = Locations.id INNER JOIN ConcertTags ON Concerts.id=ConcertTags.concert_id WHERE ConcertTags.tag_id='"+tagID+"';";
		//System.out.println(query);
		ResultSet rs;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {//System.out.println(rs.getInt("Concerts_id"));
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

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

		List<Map.Entry<K, V>> list =
				new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;

	}

	/*public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey()
					+ " Value : " + entry.getValue());
		}
	}*/


	@RequestMapping(value="same-tags/{tagID}/{concertID}",method=RequestMethod.GET)
	public String findConcertsWithSameTags(@PathVariable(value="tagID") String semanticTagId,@PathVariable(value="concertID") int concertId) {
		String jsonString="";
		String query;
		query=SemanticTagsChecker.findConcertsWithSameTags(semanticTagId,concertId);
		//System.out.println(query);
		ResultSet rs;
		Concerts concert;
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		Users user;
		Artists artist;
		Locations location;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {//System.out.println(rs.getInt("Concerts_id"));
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


	@RequestMapping(value="semantic-search/{concertID}",method=RequestMethod.GET)
	public String semanticSearch(@PathVariable(value="concertID") int concertID) {
		String jsonString = "";
		String resultJson=ConcertController.allActiveConcerts();
		ArrayList<Concerts> activeConcerts = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		for(int i=0;i<activeConcerts.size();i++){
			map.put(activeConcerts.get(i).getId(),0);
		}

		resultJson=getsemanticTags(concertID);
		ArrayList<SemanticTags> baseTags=new ArrayList<SemanticTags>(Arrays.asList(Application.gson.fromJson(resultJson, SemanticTags[].class)));

		for(int i=0;i<baseTags.size();i++){
			String semanticTagId=baseTags.get(i).getSemanticTagId();
			resultJson=findConcertsWithSameTags(semanticTagId,concertID);
			ArrayList<Concerts> compareConcerts=new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
			for (int j=0;j<compareConcerts.size();j++){
				if(map.containsKey(compareConcerts.get(j).getId())){
					int count=map.get(compareConcerts.get(j).getId());
					count--;
					map.put(compareConcerts.get(j).getId(),count);
				}
			}

		}


		Map<Integer, Integer> sortedMap = sortByValue(map);
		ArrayList<Concerts> resultList=new ArrayList<Concerts>();

		for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
			if(entry.getValue()!=0) {
				String concert_ = ConcertController.getConcert(entry.getKey());
				Concerts concert=Application.gson.fromJson(concert_, Concerts.class);
				resultList.add(concert);
			}
		}

		jsonString = Application.gson.toJson(resultList);
		return jsonString;
	}



}
