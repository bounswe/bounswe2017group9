package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.SemanticTags;

public class SemanticTagsChecker {
	public static String insertSementicTagsQuery(SemanticTags tag) {
		String query="INSERT INTO SementicTags (id,label,search,description) VALUES (";
		query+=tag.getId()+",";
		query+=tag.getLabel()+", ";
		query+=tag.getSearch()+", ";
		query+=tag.getDescription()+");";
		return query;
	}
}
