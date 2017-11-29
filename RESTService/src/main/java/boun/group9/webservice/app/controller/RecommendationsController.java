package boun.group9.webservice.app.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;

@RestController
public class RecommendationsController {
	@RequestMapping(value="recommendations/{userID}",method=RequestMethod.GET)
	public String getRecommendations(@PathVariable(value="userID") int userID) throws JsonParseException, JsonMappingException, IOException  {
		String query;
		ResultSet rs;
		int followings,concert_id,status,score;
		score=0;
		Map<Integer, Integer> recommendations=new HashMap<Integer, Integer>();
		//query="SELECT following_id FROM Relations WHERE follower_id="+userID;
		query="SELECT Attendees.concert_id As concert_id, Attendees.status As status FROM Relations INNER JOIN Attendees ON Attendees.id=Relations.following_id INNER JOIN Concerts ON Concerts.id=Attendees.concert_id  WHERE Relations.follower_id="+userID+" AND Concerts.date_time > now() AND Attendees.status != 2";
		try {
			/*rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				followings=rs.getInt("following_id");
				query="SELECT concert_id,status FROM Attendees WHERE id="+followings;
				rs2=Database.connect(query, Application.MODE_GET);
				while(rs2.next()) {
					score=0;
					concert_id=rs2.getInt("concert_id");
					query="SELECT date_time FROM concerts WHERE id="+concert_id;
					rs3=Database.connect(query, Application.MODE_GET);
					if(rs3.next()) {
						if(rs3.getDate("date_time")!=null && Calendar.getInstance().getTime().compareTo(rs3.getDate("date_time"))>0) {
							status=rs2.getInt("status");
							if(recommendations.get(concert_id)!=null) 
								score+=recommendations.get(concert_id)+status;
							else score=status;
							recommendations.put(concert_id, score);
						}
					}
				}
			}*/
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				score=0;
				concert_id=rs.getInt("concert_id");
				status=rs.getInt("status");
				if(status==1)score=2;
				else score=1;
				if(recommendations.get(concert_id)!=null) 
					score+=recommendations.get(concert_id);
				recommendations.put(concert_id, score);
			}
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		Map<Integer, Integer> newMap = 
			     recommendations.entrySet().stream()
			    .sorted(Entry.<Integer, Integer>comparingByValue().reversed())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) ->e1, LinkedHashMap::new));
		//Map<Integer, Integer> newMap = new TreeMap<Integer, Integer>(Collections.emptySortedMap());
		//newMap.putAll(recommendations);
		//JSONArray n=new JSONArray();
		String json="";
		ArrayList<Concerts> resultList=new ArrayList<Concerts>();
		try {
			
			for(int key:  newMap.keySet() ) {
				Concerts temp=new Concerts();
				String resultJson=new ConcertController().getConcert(key);
				temp=Application.gson.fromJson(resultJson, Concerts.class);
				resultList.add(temp);
				//System.out.println(resultJson);
			}
			json=Application.gson.toJson(resultList);
		}catch(java.lang.NullPointerException ex){}
		//json = new ObjectMapper().writeValueAsString(n);
		return json;
	}
	@RequestMapping(value="recommendations2/{userID}",method=RequestMethod.GET)
	public String getRecommendations2(@PathVariable(value="userID") int userID) throws JsonParseException, JsonMappingException, IOException  {
		String query,tag_id;
		int concert_id,status,score;
		score=0;
		Map<Integer, Integer> recommendations=new HashMap<Integer, Integer>();
		Map<String, Integer> tags=new HashMap<String, Integer>();
		ArrayList<Integer> userconcerts=new ArrayList<Integer>();
		ResultSet rs,rs2,rs3;
		query="SELECT concert_id From Attendees WHERE id="+userID;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				concert_id=rs.getInt("concert_id");
				userconcerts.add(concert_id);
				query="SELECT tag_id FROM ConcertTags WHERE concert_id="+concert_id;
				rs2 = Database.connect(query, Application.MODE_GET);
				while(rs2.next()) {
					score=1;
					tag_id=rs2.getString("tag_id");
					if(tags.get(tag_id)!=null) {
						score=tags.get(tag_id)+1;
					}
					tags.put(tag_id, score);
					
				}
			}
			for(String tag:tags.keySet()) {
				query="SELECT ConcertTags.concert_id As concert_id FROM ConcertTags INNER JOIN Concerts ON Concerts.id=ConcertTags.concert_id WHERE tag_id=\""+tag+"\" AND Concerts.date_time > now();";
				rs3 = Database.connect(query, Application.MODE_GET);
				while(rs3.next()) {
					concert_id=rs3.getInt("concert_id");
					if(userconcerts.contains(concert_id)) continue;
					score=tags.get(tag);
					if(recommendations.get(concert_id)!=null) {
						score+=recommendations.get(concert_id);
					}
					recommendations.put(concert_id,score);
				}
			}
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured");
			ex.printStackTrace();
			return "SQL Error occured.";
		}catch(NotSavedException ex) {
			ex.printStackTrace();
			return "Not saved.";
		}
		Map<Integer, Integer> newMap = 
			     recommendations.entrySet().stream()
			    .sorted(Entry.<Integer, Integer>comparingByValue().reversed())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) ->e1, LinkedHashMap::new));
		//Map<Integer, Integer> newMap = new TreeMap<Integer, Integer>(Collections.emptySortedMap());
		//newMap.putAll(recommendations);
		String json="";
		ArrayList<Concerts> resultList=new ArrayList<Concerts>();
		try {
			
			for(int key:  newMap.keySet() ) {
				Concerts temp=new Concerts();
				String resultJson=new ConcertController().getConcert(key);
				temp=Application.gson.fromJson(resultJson, Concerts.class);
				resultList.add(temp);
				//System.out.println(resultJson);
			}
			json=Application.gson.toJson(resultList);
		}catch(java.lang.NullPointerException ex){}
		//String json = new ObjectMapper().writeValueAsString(newMap);
		return json;
	}
}