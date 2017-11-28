package boun.group9.webservice.helper;

public class RecommendationChecker {
    public static String recommendationQuery(int userID) {
        String query= "SELECT  Concerts.id AS Concert_id from Concerts INNER JOIN Attendees ON 	Attendees.concert_id = Concerts.id  INNER JOIN Relations ON Attendees.user_id=Relations.following_id WHERE Relations.follower_id="+userID+" AND Attendees.status =1";
        return query;
    }
}
