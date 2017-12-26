package boun.group9.webservice.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class WikiDataUtility {
    public static JSONArray searchData(String search) throws IOException {
        search=search.trim().replaceAll(" ","_");
        URL url = new URL("https://www.wikidata.org/w/api.php?action=wbsearchentities&search="+search+"&language=en&format=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output="";
        String line;
        while ((line = br.readLine()) != null) {
            output += line;
        }
        Object obj= JSONValue.parse(output);
        JSONObject jsonObject = (JSONObject) obj;

        JSONArray result= (JSONArray) jsonObject.get("search");
        conn.disconnect();
        return result;
    }
}
