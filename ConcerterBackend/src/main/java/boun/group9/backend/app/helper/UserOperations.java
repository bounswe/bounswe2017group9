package boun.group9.backend.app.helper;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Users;

import boun.group9.backend.app.Application.STATUS;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserOperations {
	public static Users getUser(int userID) {
		
		return null;
	}

	public static STATUS login(Users user) {
		String json = Application.gson.toJson(user);
		try {
			URL url = new URL(Application.API_ENDPOINT + "/user");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println("Response status: " + status);
		} catch (IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}

	public static STATUS signUp(Users user){
		String json = Application.gson.toJson(user);
		try {
			URL url = new URL(Application.API_ENDPOINT + "/new-user");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println("Response status: " + status);
		} catch (IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}

}
