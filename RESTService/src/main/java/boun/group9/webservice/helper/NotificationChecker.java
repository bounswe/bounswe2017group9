package boun.group9.webservice.helper;
import java.sql.ResultSet;
import java.util.*;
import boun.group9.webservice.app.*;
import boun.group9.webservice.app.data.Notifications;
import boun.group9.webservice.app.data.Relations;
import boun.group9.webservice.app.data.Users;
public class NotificationChecker {
	public static ArrayList<Notifications> getNotifications(int userId) {
		String query = "SELECT * FROM Notifications WHERE target_id="+userId+";";
		System.out.println(query);
		ArrayList<Notifications> notificationList = new ArrayList<Notifications>();
		ResultSet rs;
		Notifications notification;
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				notification = new Notifications();
				notification.setId(rs.getInt("id"));
				notification.setConcert_id(rs.getInt("concert_id"));
				notification.setContent(rs.getString("content"));
				notification.setTarget_id(rs.getInt("target_id"));
				notificationList.add(notification);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		Database.closeConnection();
		return notificationList;
	}
	public static void notify(int concertId,int userId) {
		String query = "SELECT Users.name,Relations.follower_id FROM Relations INNER JOIN Users ON Relations.following_id = Users.id WHERE following_id="+userId+";";
		ArrayList<Integer> followerList = new ArrayList<Integer>();
		String name="";
		ResultSet rs;
		String content;
		String concertName="";
		try {
			rs = Database.connect(query, Application.MODE_GET);
			while(rs.next()) {
				followerList.add(rs.getInt("Relations.follower_id"));
				name = rs.getString("Users.name");
			}
			Database.closeConnection();
			query = "SELECT name FROM Concerts WHERE id = "+concertId+";";
			rs = Database.connect(query, Application.MODE_GET);
			if(rs.next()) {
				concertName = rs.getString("name");
			}
			query = "INSERT INTO Notifications (content,target_id,concert_id) VALUES ";
			for(Integer id : followerList) {
				query += "('"+name+" is attending to "+concertName+".',"+id+","+concertId+"),";
			}
			System.out.println(query);
			query = query.substring(0, query.length()-1)+";";
			Database.connect(query,Application.MODE_UPDATE);
			Database.closeConnection();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
