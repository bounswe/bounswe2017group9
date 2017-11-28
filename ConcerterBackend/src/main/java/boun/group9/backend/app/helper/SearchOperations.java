package boun.group9.backend.app.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Users;

public class SearchOperations {
	
	public static ArrayList<Users> basicSearchUser(String user){
		String resultJson="";
		ArrayList<Users> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/basicsearchuser/"+user);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Users>(Arrays.asList(Application.gson.fromJson(resultJson,Users[].class)));
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Concerts> basicSearchConcert(String concert){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/basicsearchconcert/"+concert);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson,Concerts[].class)));
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Concerts> advancedSearchPrice(int min, int max){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/advancedSearchPrice/"+ min +"/" +max );
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson,Concerts[].class)));
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Concerts> advancedSearchLocation(String location){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/advancedSearchLocation/"+ location );
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson,Concerts[].class)));
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Concerts> advancedSearchDate(String start,  String end){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/advancedSearchDate/"+ start + "/" + end );
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson,Concerts[].class)));
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
