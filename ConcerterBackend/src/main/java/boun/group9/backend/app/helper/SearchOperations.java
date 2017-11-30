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
}
