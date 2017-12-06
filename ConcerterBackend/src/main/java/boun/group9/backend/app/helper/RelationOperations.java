package boun.group9.backend.app.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.Users;

public class RelationOperations {
	
	public static STATUS follow(int followingID,HttpSession session) {
		Users baseUser = (Users)session.getAttribute("loggedInUser");
		try {
			URL url = new URL(Application.API_ENDPOINT+"/follow?follower="+baseUser.getId()+"&"+"following="+followingID);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.connect();
			System.out.println(connection.getResponseCode());
		}catch(Exception ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}
	
	public static STATUS unfollow(int followingID,HttpSession session) {
		Users baseUser = (Users)session.getAttribute("loggedInUser");
		try {
			URL url = new URL(Application.API_ENDPOINT+"/unfollow?follower="+baseUser.getId()+"&"+"following="+followingID);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.connect();
			System.out.println(connection.getResponseCode());
		}catch(Exception ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}

}
