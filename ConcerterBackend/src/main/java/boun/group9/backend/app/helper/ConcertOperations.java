package boun.group9.backend.app.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMethod;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.Artists;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Locations;
import boun.group9.backend.app.data.Users;
public class ConcertOperations {
	public static ArrayList<Concerts> getRecommendedConcerts(Users user){
		String resultJson;
		ArrayList<Concerts> resultList = new ArrayList<Concerts>();
		try {
			URL url = new URL(Application.API_ENDPOINT+"/recommend/"+user.getId()+";");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
			for(Concerts oneConcert : resultList) {
				ArrayList<Comments> commentList;
				Comments comment;
				url = new URL(Application.API_ENDPOINT+"/concerts/"+oneConcert.getId()+"/comments");
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
				oneConcert.setCommentList(commentList);
			}
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Concerts> getAttendingConcerts(int userID){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concerts?user_id="+userID+"&"+"status=attending");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
			for(Concerts oneConcert : resultList) {
				ArrayList<Comments> commentList;
				Comments comment;
				url = new URL(Application.API_ENDPOINT+"/concerts/"+oneConcert.getId()+"/comments");
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
				oneConcert.setCommentList(commentList);
			}
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Concerts> getAttendedConcerts(int userID){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concerts?user_id="+userID+"&"+"status=attended");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
			for(Concerts oneConcert : resultList) {
				ArrayList<Comments> commentList;
				Comments comment;
				url = new URL(Application.API_ENDPOINT+"/concerts/"+oneConcert.getId()+"/comments");
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
				oneConcert.setCommentList(commentList);
			}
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Concerts> getThinkingConcerts(int userID){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concerts?user_id="+userID+"&"+"status=thinking");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
			for(Concerts oneConcert : resultList) {
				ArrayList<Comments> commentList;
				Comments comment;
				url = new URL(Application.API_ENDPOINT+"/concerts/"+oneConcert.getId()+"/comments");
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
				oneConcert.setCommentList(commentList);
			}
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static Concerts getConcert(int concertID) {
		String resultJson="";
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concerts/"+concertID);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			return Application.gson.fromJson(resultJson, Concerts.class);
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Concerts> getAllActiveConcerts(){
		String resultJson="";
		ArrayList<Concerts> resultList;
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concerts");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			//System.out.println(resultJson);
			resultList = new ArrayList<Concerts>(Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
			//System.out.println(resultList.get(0).getDate_time());
			for(Concerts oneConcert : resultList) {
				oneConcert.setDate_str(oneConcert.getDate_time().toString());
				ArrayList<Comments> commentList;
				Comments comment;
				url = new URL(Application.API_ENDPOINT+"/concerts/"+oneConcert.getId()+"/comments");
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
				oneConcert.setCommentList(commentList);
			}
			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static STATUS createConcert(Concerts concert) {
		//concert.setCreated_by_id(7);
		concert.setArtist(new Artists(concert.getArtist_name()));
		concert.setLocation(new Locations(45.12312,63.43432,concert.getLocation_name(),concert.getLocation_name()));
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			date=sdf.parse(concert.getDate_str()+" "+concert.getTime_str());
			concert.setDate_time(date);
		}catch(ParseException ex) {
			ex.printStackTrace();
			System.out.println("Data is not well formatted.");
		}
		String json = Application.gson.toJson(concert);
		System.out.println(json);
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concerts");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println("Response status: "+status);
		}catch(IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}
	public static ArrayList<Comments> getAllComments(int concertID){
		
		String resultJson="";
		ArrayList<Comments> commentList=new ArrayList<Comments>();
		try{
		
		URL url = new URL(Application.API_ENDPOINT+"/concerts/"+concertID+"/comments");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		resultJson = br.readLine();
		commentList = new ArrayList<Comments> (Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));
		return commentList;
	}catch(MalformedURLException ex) {
		ex.printStackTrace();
	}catch(IOException ex) {
		ex.printStackTrace();
	}
	return null;
	
	}
	public static STATUS attendConcert(Users user, int id) {
		try {
			String resultJson;
			URL url = new URL(Application.API_ENDPOINT+"/concert/"+id+"/attendee/"+user.getId());
			System.out.println(Application.API_ENDPOINT+"/concert/"+id+"/attendee/"+user.getId());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
	}
	
	public static STATUS submitRateForConcert(int concertID , int rate) {
		try {
			URL url = new URL(Application.API_ENDPOINT+"/concert/" + concertID+ "/" + rate );
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os = connection.getOutputStream();
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println("Response status: "+status);
			System.out.println(url);
		}catch(IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.SUCCESS;
		
	}
}
