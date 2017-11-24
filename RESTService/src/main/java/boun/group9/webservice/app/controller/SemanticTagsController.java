package boun.group9.webservice.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  
import org.json.simple.JSONValue;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.ConcertTags;
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.SemanticTags;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.IJsonSyntaxException;
import boun.group9.webservice.exception.ISQLException;
import boun.group9.webservice.exception.InternalServerException;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.SemanticTagsChecker;
import boun.group9.webservice.helper.UserChecker;


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
	@RequestMapping(value="new-concerttag",method=RequestMethod.POST)
	public String newConcertTag(@RequestBody String body) {
		ConcertTags ctag;
		String query;
		ResultSet rs;
		try {
			ctag = Application.gson.fromJson(body, ConcertTags.class);
			query = SemanticTagsChecker.insertConcertTagsQuery(ctag);
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
	@RequestMapping(value="semantictags",method=RequestMethod.GET)
	public String getsemanticTags() {
		String jsonString="";
		String query="SELECT * FROM semanticTags;";
		System.out.println(query);
		ResultSet rs;
		SemanticTags tag;
		ArrayList<SemanticTags> tagList = new ArrayList<SemanticTags>();
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				tag = new SemanticTags();
				tag.setId(rs.getString("id"));
				tag.setLabel(rs.getString("label"));
				tag.setSearch(rs.getString("search"));
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
		String query="SELECT * FROM semanticTags WHERE id=\""+tagID+"\";";
		System.out.println(query);
		ResultSet rs;
		SemanticTags tag;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			if(rs.next()) {
				tag = new SemanticTags();
				tag.setId(rs.getString("id"));
				tag.setLabel(rs.getString("label"));
				tag.setSearch(rs.getString("search"));
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
	@RequestMapping(value="concerttags/{concertID}",method=RequestMethod.GET)
	public String getConcertTags(@PathVariable(value="concertID") int concertID) {
		String jsonString="";
		String query="SELECT * FROM ConcertTags WHERE concert_id="+concertID+";";
		System.out.println(query);
		ResultSet rs;
		ConcertTags ctag;
		ArrayList<ConcertTags> ctagList = new ArrayList<ConcertTags>();
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				ctag = new ConcertTags();
				ctag.setId(rs.getInt("id"));
				ctag.setTag_id(rs.getString("tag_id"));
				ctag.setConcert_id(rs.getInt("concert_id"));
				ctag.setCreated_at(rs.getDate("created_at"));
				ctagList.add(ctag);
			}
			jsonString = Application.gson.toJson(ctagList);
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
	@RequestMapping(value="searchWikidata/{search}",method=RequestMethod.GET)
	public String getSementicTagsFromWikidata(@PathVariable(value="search") String search) {
		String jsonString="";
		ArrayList<SemanticTags> tagList = new ArrayList<SemanticTags>();
		SemanticTags tag;
		try {

			URL url = new URL("https://www.wikidata.org/w/api.php?action=wbsearchentities&search="+search+"&language=en&format=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output="";
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}
			Object obj=JSONValue.parse(output);  
		    JSONObject jsonObject = (JSONObject) obj;  
		    
		    jsonString =  jsonObject.get("search").toString(); 
		    JSONArray result= (JSONArray) jsonObject.get("search");
		    Iterator i = result.iterator();
		    while (i.hasNext()) {
		    		JSONObject innerObj = (JSONObject) i.next();
		    		tag=new SemanticTags();
		    		tag.setId(innerObj.get("id").toString());
		    		tag.setLabel(innerObj.get("label").toString());
		    		tag.setSearch(search);
		    		tag.setDescription(innerObj.get("description").toString());
		    		tagList.add(tag);
		    	    
		    	    }

		    jsonString = Application.gson.toJson(tagList);
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return jsonString;
	}
}
