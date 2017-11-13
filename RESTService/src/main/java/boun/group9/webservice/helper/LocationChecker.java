package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.Locations;

public class LocationChecker {
	public static String insertLocationQuery(String body) {
		
		return null;
	}
	public static String insertLocationQuery(Locations location) {
		String query = "INSERT INTO Locations (";
		String titles="";
		String values="";
		if(location.getLatitude() != 0) {
			titles+="latitude,";
			values+=location.getLatitude()+",";
		}
		if(location.getLongitude() != 0) {
			titles+="longitude,";
			values+=location.getLongitude()+",";
		}
		titles += "address,";
		values += "'"+location.getAddress()+"',";
		titles += "city";
		values += "'"+location.getCity()+"'";
		query+=titles+") VALUES (";
		query += values+");";
		return query;
	}
}
