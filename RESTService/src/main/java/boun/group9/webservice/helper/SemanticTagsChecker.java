package boun.group9.webservice.helper;

import boun.group9.webservice.app.data.ConcertTags;
import boun.group9.webservice.app.data.SemanticTags;

public class SemanticTagsChecker {
	public static String insertSemanticTagsQuery(SemanticTags tag) {

		if(tag.getDescription().length()>70){
			tag.setDescription(tag.getDescription().substring(0,70));
		}
		String query=
				"INSERT INTO SemanticTags (label, description, concert_id,semanticTagId)\n" +
				"SELECT * FROM (SELECT '"+tag.getLabel().replaceAll("\"", " ")+"','"+
				tag.getDescription().replaceAll("\"", " ")+"',"+tag.getConcert_id()+",'"
				+tag.getSemanticTagId()+"') AS tmp\n" +
				"WHERE NOT EXISTS (SELECT semanticTagId,concert_id FROM Semantictags WHERE (semanticTagId = '"+tag.getSemanticTagId()+"' and concert_id="+
						tag.getConcert_id()+")) LIMIT 1;";

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

	public static String findConcertsWithSameTags(String semanticTagId,int concertId){
		String query=
				"SELECT Concerts.ticket AS Concerts_ticket,Concerts.id AS Concerts_id, Concerts.name AS Concerts_name, Concerts.date_time AS Concerts_date_time, Concerts.min_price, Concerts.max_price, Concerts.rate AS Concerts_rate, Concerts.voter_amount as Concerts_voter_amount, Concerts.image_path AS Concerts_image_path, \n" +
				"Users.id AS Users_id, Users.name AS Users_name, Users.email AS Users_email, Users.followers AS Users_followers, Users.followings AS Users_followings, Users.photo_path AS Users_photo_path, Users.created_at AS Users_created_at, Users.updated_at AS Users_updated_at, Users.last_login AS Users_last_login, \n" +
				"Artists.id AS Artists_id, Artists.name AS Artists_name, \n" +
				"Locations.id AS Locations_id, Locations.longitude AS Locations_longitude,Locations.latitude AS Locations_latitude, Locations.city AS Locations_city, Locations.address as Locations_address FROM Concerts \n" +
				"INNER JOIN Users ON Concerts.created_by = Users.id INNER JOIN Artists ON Concerts.artist = Artists.id \n" +
				"INNER JOIN Locations ON Concerts.location = Locations.id\n" +
				"LEFT JOIN \n" +
				"    semanticTags\n" +
				"ON \n" +
				"    semanticTags.concert_id = concerts.id\n" +
				"WHERE \n" +
				"    (semantictags.semanticTagId='"+semanticTagId+"' and concerts.id!="+concertId+");" +
				";";



		return query;

	}
}
