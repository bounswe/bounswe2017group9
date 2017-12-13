package boun.group9.backend.app.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import boun.group9.backend.app.Application;
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
}
