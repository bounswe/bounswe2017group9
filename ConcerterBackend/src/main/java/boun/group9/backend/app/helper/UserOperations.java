package boun.group9.backend.app.helper;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;

import boun.group9.backend.app.Application.STATUS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserOperations {
	
	public static Users getUser(int userID) {
			String resultJson="";
			try {
				URL url = new URL(Application.API_ENDPOINT+"/user/"+userID);
				HttpURLConnection connection =(HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				System.out.println(resultJson);
				return Application.gson.fromJson(resultJson, Users.class);
			}catch(MalformedURLException ex) {
				ex.printStackTrace();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return null;
		
	}

	public static Users login(Users user) {
		String json = Application.gson.toJson(user);
		try {
			String response;
			URL url = new URL(Application.API_ENDPOINT + "/user");
			System.out.println(Application.API_ENDPOINT + "/user");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			System.out.println(json);
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			if(status == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));	
				response = br.readLine();
				user = Application.gson.fromJson(response, Users.class);
				user.setPassword("");
				return user;
			}else {
				return null;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Users signUp(Users user){
		String json = Application.gson.toJson(user);
		try {
			URL url = new URL(Application.API_ENDPOINT + "/new-user");
			System.out.println("URL: "+Application.API_ENDPOINT + "/new-user");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			System.out.println("Signup json:"+json);
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			json = br.readLine();
			System.out.println("Return json:"+json);
			user = Application.gson.fromJson(json, Users.class);
			int status = connection.getResponseCode();
			System.out.println("Response status: " + status);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return user;
	}

}
