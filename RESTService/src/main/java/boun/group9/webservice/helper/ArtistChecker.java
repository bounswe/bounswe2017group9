package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.Artists;

public class ArtistChecker {
	public static String insertArtistQuery(String body) {
		return null;
	}
	public static String insertArtistQuery(Artists artist) {
		String query = "INSERT INTO Artists (name) VALUES(";
		query += "'"+artist.getName()+"');";
		return query;
	}
}
