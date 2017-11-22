package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.SementicTags;

public class SementicTagsChecker {
	public static String insertSementicTagsQuery(SementicTags tag) {
		String query="INSERT INTO SementicTags (id,label,search,description) VALUES (";
		query+=tag.getId()+", ";
		query+=tag.getLabel()+", ";
		query+=tag.getSearch()+", ";
		query+=tag.getDescription()+");";
		return query;
	}
}
