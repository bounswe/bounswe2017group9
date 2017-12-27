package boun.group9.webservice.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import boun.group9.webservice.app.Application;
import boun.group9.webservice.app.data.Concerts;

public class ConcertChecker {
	public static String insertConcertQuery(String body) {
		return null;
	}
	public static String insertConcertQuery(Concerts concert) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String query = "INSERT INTO Concerts (name,created_by,artist,location,date_time,min_price,max_price,image_path,ticket) VALUES ('"+concert.getName()+"',"+concert.getCreated_by_id()+","+concert.getArtist_id()+","+concert.getLocation_id()+",'"+sdf.format(concert.getDate_time())+"',"+concert.getMin_price()+","+concert.getMax_price()+",'"+concert.getImage_path()+"','"+concert.getTicket()+"');";
		return query;
	}
	public static String attend(int concertID,int userID) {
		String query = "INSERT INTO Attendees (concert_id,status,user_id) VALUES("+concertID+",1,"+userID+");";
		return query;
	}
	public static String postConcertRate(int concert_id , int current_rate){
		return "Update concerts Set concerts.rate = Truncate ((" + current_rate + " + concerts.rate * (concerts.voter_amount-1))/concerts.voter_amount , 2) where concerts.id = "+concert_id + ";";
	}

	public static String updateRateNumber(int concert_id){
		return "Update concerts Set concerts.voter_amount = concerts.voter_amount + 1 where concerts.id = "+ concert_id +";";
	}

	//concerts that the user attended is considered.
	public static String postRate(int userID , int concert_id , int current_rate){

		return "UPDATE concerts\n" +
				"INNER JOIN attendees ON concerts.id = attendees.concert_id\n" +
				"Set concerts.rate = Truncate (( " + current_rate + " + concerts.rate * (concerts.voter_amount-1))/concerts.voter_amount , 2) \n" +
				"WHERE concerts.id = " + concert_id + " and attendees.user_id = " + userID + " and attendees.status = 2;";
	}

	//concerts that the user attended is considered.
	public static String updateRateNum(int concert_id){
		return "Update concerts Set concerts.voter_amount = concerts.voter_amount + 1 where concerts.id = "+ concert_id +";";
	}

	public static ArrayList<Concerts> sortByDate(ArrayList<Concerts> concertList){
		Collections.sort(concertList, new Comparator<Concerts>() {
			public int compare(Concerts o1, Concerts o2) {
				if (o1.getDate_time() == null || o2.getDate_time() == null)
					return 0;
				return o1.getDate_time().compareTo(o2.getDate_time());
			}
		});

		Collections.sort(concertList, Collections.reverseOrder());
		return concertList;
	}
}
