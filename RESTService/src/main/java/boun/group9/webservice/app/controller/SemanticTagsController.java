package boun.group9.webservice.app.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonSyntaxException;

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
}
