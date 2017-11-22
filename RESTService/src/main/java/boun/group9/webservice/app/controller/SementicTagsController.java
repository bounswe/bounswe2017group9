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
import boun.group9.webservice.app.data.Concerts;
import boun.group9.webservice.app.data.SementicTags;
import boun.group9.webservice.app.data.Users;
import boun.group9.webservice.exception.IJsonSyntaxException;
import boun.group9.webservice.exception.ISQLException;
import boun.group9.webservice.exception.InternalServerException;
import boun.group9.webservice.exception.NotSavedException;
import boun.group9.webservice.helper.Database;
import boun.group9.webservice.helper.SementicTagsChecker;
import boun.group9.webservice.helper.UserChecker;

@RestController
public class SementicTagsController {
	@RequestMapping(value="new-sementictag",method=RequestMethod.POST)
	public String newSementicTag(@RequestBody String body) {
		SementicTags tag;
		String query;
		ResultSet rs;
		try {
			tag = Application.gson.fromJson(body, SementicTags.class);
			query = SementicTagsChecker.insertSementicTagsQuery(tag);
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
	@RequestMapping(value="sementictags",method=RequestMethod.GET)
	public String getSementicTags() {
		String jsonString="";
		String query="SELECT * FROM SementicTags;";
		System.out.println(query);
		ResultSet rs;
		SementicTags tag;
		ArrayList<SementicTags> tagList = new ArrayList<SementicTags>();
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				tag = new SementicTags();
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
	@RequestMapping(value="sementictags/{tagID}",method=RequestMethod.GET)
	public String getSementicTag(@PathVariable(value="tagID") String tagID) {
		String jsonString="";
		String query="SELECT * FROM SementicTags WHERE id=\""+tagID+"\";";
		System.out.println(query);
		ResultSet rs;
		SementicTags tag;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			if(rs.next()) {
				tag = new SementicTags();
				tag.setId(rs.getString("id"));
				tag.setLabel(rs.getString("label"));
				tag.setSearch(rs.getString("search"));
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
}
