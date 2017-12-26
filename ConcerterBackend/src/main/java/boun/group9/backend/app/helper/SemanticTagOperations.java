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
import boun.group9.backend.app.data.SemanticTag;

public class SemanticTagOperations {
	public static ArrayList<SemanticTag> getSemanticTags(String searchKey) {
		ArrayList<SemanticTag> resultList;
		String resultJson;
		try {
			//System.out.println(searchKey);
			URL url = new URL(Application.API_ENDPOINT+"/searchWikidata/"+searchKey);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			//System.out.println(resultJson);
			resultList = new ArrayList<SemanticTag>(Arrays.asList(Application.gson.fromJson(resultJson, SemanticTag[].class)));
			return resultList;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static STATUS addSemanticTag(SemanticTag tag) {
		try {
			URL url = new URL(Application.API_ENDPOINT+"/new-semantictag");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			String json = Application.gson.toJson(tag);
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
	public static ArrayList<SemanticTag> getTagsByConcertID(int concertID){

		ArrayList<SemanticTag> resultList=new ArrayList<SemanticTag>();

		String resultJson="";

		try {
			URL url = new URL(Application.API_ENDPOINT+"/semantic-tags/" + concertID);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			resultList = new ArrayList<SemanticTag>(Arrays.asList(Application.gson.fromJson(resultJson, SemanticTag[].class)));

			return resultList;
		}catch(MalformedURLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}


}
