package boun.group9.backend.app.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.OutputStream;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.data.AdvancedSearch;
import boun.group9.backend.app.data.Comments;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.SearchResult;
import boun.group9.backend.app.data.Users;

public class SearchOperations {
	
	public static SearchResult basicSearchConcert(String searchKey) {
		SearchResult searchResult;
		String resultJson;
		try {
			System.out.println(searchKey);
			URL url = new URL(Application.API_ENDPOINT+"/basic-search?searchKey="+searchKey);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			System.out.println(resultJson);
			searchResult = Application.gson.fromJson(resultJson, SearchResult.class);
			return searchResult;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Concerts> advancedSearch(AdvancedSearch search){
		ArrayList<Concerts> concertList = new ArrayList<Concerts>();
		String json = Application.gson.toJson(search,AdvancedSearch.class);
		String resultJson;
		try {
			System.out.println("going JSON"+json);
			URL url = new URL(Application.API_ENDPOINT+"/advanced-search");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			byte[] jsonBytes = json.getBytes("UTF-8");
			OutputStream os = connection.getOutputStream();
			os.write(jsonBytes);
			os.close();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			resultJson = br.readLine();
			System.out.println(resultJson);
			concertList = new ArrayList<Concerts> (Arrays.asList(Application.gson.fromJson(resultJson, Concerts[].class)));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return concertList;
	}
}
