package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.ConcertTags;
import boun.group9.webservice.app.data.SemanticTags;

public class SemanticTagsChecker {
	public static String insertSemanticTagsQuery(SemanticTags tag) {
		String query="INSERT INTO SemanticTags (semanticTagId,concert_id,label,description) VALUES (";
		query+="\""+tag.getSemanticTagId()+"\", ";
		query+="\""+tag.getConcert_id()+"\", ";
		query+="\""+tag.getLabel().replaceAll("\"", " ") +"\", ";
		query+="\""+tag.getDescription().replaceAll("\"", " ")+"\");";
		return query;
	}
	/*public static String insertConcertTagsQuery(ConcertTags ctag) {
		String query="INSERT INTO ConcertTags(id,tag_id,concert_id,created_at) VALUES (";
		query+=ctag.getId()+", ";
		query+="\""+ctag.getTag_id()+"\", ";
		query+=ctag.getConcert_id()+", ";
		query+="\""+ctag.getCreated_at()+"\");";
		return query;
	}*/
}
