package boun.group9.webservice.app.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;

@RestController
public class RecommendationsController {
	@RequestMapping(value="recommendations/{userID}",method=RequestMethod.GET)
	public String getRecommendations(@PathVariable(value="userID") int userID) throws JsonParseException, JsonMappingException, IOException  {
		String query;
		ResultSet rs,rs2,rs3;
		int followings,concert_id,status,score;
		score=0;
		Map<Integer, Integer> recommendations=new HashMap<Integer, Integer>();
		//query="SELECT following_id FROM Relations WHERE follower_id="+userID;
		query="SELECT Attendees.concert_id As concert_id, Attendees.status As status FROM Relations INNER JOIN Attendees ON Attendees.id=Relations.following_id INNER JOIN Concerts ON Concerts.id=Attendees.concert_id  WHERE Relations.follower_id="+userID+" AND Concerts.date_time < now() AND Attendees.status != 2";
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
		Map<Integer, Integer> newMap = new TreeMap(Collections.emptySortedMap());
		newMap.putAll(recommendations);
		String json = new ObjectMapper().writeValueAsString(newMap);
		return json;
	}
}