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
import boun.group9.backend.app.data.Comments;

public class CommentOperations {
	public static STATUS createComment(Comments comment) {
		try {
			URL url = new URL(Application.API_ENDPOINT+"/comments");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			String json = Application.gson.toJson(comment);
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
	public static STATUS createCommentwithCategory(Comments newComment) {
		try {
			URL url = new URL(Application.API_ENDPOINT+"/newcomment");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			String json = Application.gson.toJson(newComment);
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			int status = connection.getResponseCode();
			System.out.println(status);
			if(status==200){
				return Application.STATUS.SUCCESS;
			}
		}catch(IOException ex) {
			ex.printStackTrace();
			return Application.STATUS.ERROR;
		}
		return Application.STATUS.ERROR;
		
	}
	
		public static ArrayList<Comments> getCommentsByConcertID(int concertID){

			ArrayList<Comments> resultList;

			String resultJson="";

			try {
				URL url = new URL(Application.API_ENDPOINT+"/concerts/" + concertID + "/comments");
				HttpURLConnection connection =(HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				resultList = new ArrayList<Comments>(Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));

				return resultList;
			}catch(MalformedURLException ex) {
				ex.printStackTrace();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}

		
		public static STATUS deleteComment(int commentID , int userID){

			try {
				URL url = new URL(Application.API_ENDPOINT+ "/"+ userID + "/comments/" + commentID + "/deleteComment");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("PUT");
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.connect();

				int status = connection.getResponseCode();
				if(status == 200) {

					return Application.STATUS.SUCCESS;
				
				}
				System.out.println(status);

				System.out.println(status);

			}catch(Exception ex) {
				ex.printStackTrace();
				return Application.STATUS.ERROR;
			}
			return Application.STATUS.ERROR;
		}

		
		public static STATUS upVoteForComment(int commentID, int userID){

			try {
				URL url = new URL(Application.API_ENDPOINT+ "/"+userID +"/comments/" + commentID + "/upVote" );
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("PUT");
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.connect();

				int status = connection.getResponseCode();
				if(status == 200) {

					return Application.STATUS.SUCCESS;
				
				}
				System.out.println(status);

			}catch(IOException ex) {
				ex.printStackTrace();
				return Application.STATUS.ERROR;
			}
			return Application.STATUS.ERROR;
		}
		
		
		public static STATUS downVoteForComment(int commentID, int userID) {
			try {
				URL url = new URL(Application.API_ENDPOINT+ "/" +userID + "/comments/" + commentID + "/downVote" );
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("PUT");
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.connect();

				int status = connection.getResponseCode();
				System.out.println(status);
				if(status == 200) {

					return Application.STATUS.SUCCESS;
				
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
				return Application.STATUS.ERROR;
			}
			return Application.STATUS.ERROR;
		}

		public static ArrayList<Comments> getCommentsByCategory(int concertID, int category){
			ArrayList<Comments> resultList;

			String resultJson="";

			try {
				URL url = new URL(Application.API_ENDPOINT+"/concerts/" + concertID + "/comments/"+category);
				HttpURLConnection connection =(HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				resultJson = br.readLine();
				resultList = new ArrayList<Comments>(Arrays.asList(Application.gson.fromJson(resultJson, Comments[].class)));

				return resultList;
			}catch(MalformedURLException ex) {
				ex.printStackTrace();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}


	
}
