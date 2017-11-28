package boun.group9.backend.app.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.Users;

public class RelationOperations {
	
	public static STATUS follow(int followerID,Users followinguser) {
		String json = Application.gson.toJson(followinguser);
		try {
			
			URL url = new URL(Application.API_ENDPOINT + "/follow/"+followerID+"/"+followinguser.getId());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println("Response status: " + status);
			if(status == 200) {
				return Application.STATUS.SUCCESS;
			}
			return Application.STATUS.ERROR;
		} catch (IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
			
	}
	public static STATUS unfollow(int followerID,Users followinguser) {
		String json = Application.gson.toJson(followinguser);
		try {
			
			URL url = new URL(Application.API_ENDPOINT + "/unfollow/"+followerID+"/"+followinguser.getId());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println("Response status: " + status);
			if(status == 200) {
				return Application.STATUS.SUCCESS;
			}
			return Application.STATUS.ERROR;
		} catch (IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
			
	}


}
