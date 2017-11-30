package boun.group9.webservice.app.data;

import java.util.ArrayList;

public class BasicSearch {
	ArrayList<Users> users;
	ArrayList<Concerts> concerts;
	ArrayList<Artists> artists;
	public BasicSearch() {
		
	}
	public BasicSearch(ArrayList<Users> users, ArrayList<Concerts> concerts,ArrayList<Artists> artists) {
		this.users = users;
		this.concerts = concerts;
		this.artists = artists;
	}
}
