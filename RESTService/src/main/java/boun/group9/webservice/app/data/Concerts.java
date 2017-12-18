package boun.group9.webservice.app.data;

import java.sql.Timestamp;
import java.util.Date;

public class Concerts {
	private int id;
	private String name;
	private Users created_by;
	private int created_by_id;
	private Artists artist;
	private Locations location;
	private Date date_time;
	private int artist_id;
	private int location_id;
	private int min_price;
	private int max_price;
	private float rate;
	private int voter_amount;
	private String image_path;
	private String ticket;
	public Concerts() {
	
	}
	public int getCreated_by_id() {
		return this.created_by_id;
	}
	public void setCreated_by_id(int id) {
		this.created_by_id = id;
	}
	public int getArtist_id() {
		return this.artist_id;
	}
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}
	public int getLocation_id() {
		return this.location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Users getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Users created_by) {
		this.created_by = created_by;
	}
	public Artists getArtist() {
		return artist;
	}
	public void setArtist(Artists artist_name) {
		this.artist = artist_name;
	}
	public Locations getLocation() {
		return location;
	}
	public void setLocation(Locations location) {
		this.location = location;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Timestamp date_time) {
		if(date_time != null) {
			this.date_time = new Date(date_time.getTime());
		}
		
	}
	public int getMin_price() {
		return min_price;
	}
	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}
	public int getMax_price() {
		return max_price;
	}
	public void setMax_price(int max_price) {
		this.max_price = max_price;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getVoter_amount() {
		return voter_amount;
	}
	public void setVoter_amount(int voter_amount) {
		this.voter_amount = voter_amount;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket){
		this.ticket=ticket;
	}
}
