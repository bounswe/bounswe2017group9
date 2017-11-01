package boun.group9.backend.app.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMethod;

import boun.group9.backend.app.Application;
import boun.group9.backend.app.Application.STATUS;
import boun.group9.backend.app.data.Artists;
import boun.group9.backend.app.data.Concerts;
import boun.group9.backend.app.data.Locations;
public class ConcertOperations {
	public static ArrayList<Concerts> getAttendingConcerts(int userID){
		 
		return null;
	}
	public static ArrayList<Concerts> getAttendedConcerts(int userID){
		
		return null;
	}
	public static ArrayList<Concerts> getThinkingConcerts(int userID){
		
		return null;
	}
	public static Concerts getConcert(int concertID) {
		
		return null;
	}
	public static ArrayList<Concerts> getAllActiveConcerts(){
		return null;
	}
	public static STATUS createConcert(Concerts concert) {
		concert.setCreated_by_id(7);
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
		concert.setImage_path("not stated");
		String json = Application.gson.toJson(concert);
		
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
}
