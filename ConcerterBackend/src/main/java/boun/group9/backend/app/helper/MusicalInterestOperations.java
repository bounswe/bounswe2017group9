package boun.group9.backend.app.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.MusicalInterests;

public class MusicalInterestOperations {
	
	public static ArrayList<MusicalInterests> searchMusicalInterests(String search){

		ArrayList<MusicalInterests> resultList;
		String resultJson="";

		try {
			URL url = new URL(Application.API_ENDPOINT+"/musical-interests/search/"+search);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<MusicalInterests>(Arrays.asList(Application.gson.fromJson(resultJson, MusicalInterests[].class)));

			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	
	
	public static STATUS addMusicalInterests(MusicalInterests musical_interests) {
		try {
			URL url = new URL(Application.API_ENDPOINT+"/user/musical-interests");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			String json = Application.gson.toJson(musical_interests);
			System.out.println(json);
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println(status);
		}catch(IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}

	public static ArrayList<MusicalInterests> getMusicalInterestsbyUser(int userID){

		ArrayList<MusicalInterests> resultList;

		String resultJson="";

		try {
			URL url = new URL(Application.API_ENDPOINT+"/user/"+userID+"/musical-interests");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<MusicalInterests>(Arrays.asList(Application.gson.fromJson(resultJson, MusicalInterests[].class)));

			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static STATUS deleteMusicalInterest(int interestID , int userID){

		try {
			URL url = new URL(Application.API_ENDPOINT+ "/user/"+userID+"/musical-interests/"+interestID);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();

			int status = connection.getResponseCode();
			if(status == 200) {

				return Application.STATUS.SUCCESS;
			
			}
			System.out.println(status);

		}catch(Exception ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.ERROR;
	}


	
}
