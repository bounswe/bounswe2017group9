package boun.group9.webservice.helper;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Relations;

public class RelationChecker {
   public static void follow(int follower,int following) {
	   String query = "INSERT INTO Relations (follower_id,following_id) VALUES ("+follower+","+following+");";
	   try {
		   Database.connect(query, Application.MODE_UPDATE);
		   Database.closeConnection();
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   }
	   
   }
   public static void unfollow(int follower,int following) {
	   String query = "DELETE FROM Relations WHERE follower_id="+follower+" AND following_id="+following+";";
	   System.out.println(query);
	   try {
		   Database.connect(query, Application.MODE_UPDATE);
		   Database.closeConnection();
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   }
   }

}
