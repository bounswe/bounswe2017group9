package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.ConcertTags;
import boun.group9.webservice.app.data.SemanticTags;

public class SemanticTagsChecker {
	public static String insertSemanticTagsQuery(SemanticTags tag) {
		String query="INSERT INTO SemanticTags (id,label,search,description) VALUES (";
		query+="\""+tag.getId()+"\",";
		query+="\""+tag.getLabel()+"\", ";
		query+="\""+tag.getSearch()+"\", ";
		query+="\""+tag.getDescription()+"\");";
		return query;
	}
	public static String insertConcertTagsQuery(ConcertTags ctag) {
		String query="INSERT INTO ConcertTags(id,tag_id,concert_id,created_at) VALUES (";
		query+=ctag.getId()+", ";
		query+="\""+ctag.getTag_id()+"\", ";
		query+=ctag.getConcert_id()+", ";
		query+="\""+ctag.getCreated_at()+"\");";
		return query;
	}
}
