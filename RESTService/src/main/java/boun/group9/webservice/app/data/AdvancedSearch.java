package boun.group9.webservice.app.data;
import java.util.Date;

public class AdvancedSearch {
	private String name;
	private String location;
	private String start_date_str;
	private String start_time_str;
	private String end_date_str;
	private String end_time_str;
	private int min_price;
	private int max_price;
	private String artist_name;
	private String location_name;
	private Date start_date;
	private Date end_date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStart_date_str() {
		return start_date_str;
	}
	public void setStart_date_str(String start_date_str) {
		this.start_date_str = start_date_str;
	}
	public String getEnd_date_str() {
		return end_date_str;
	}
	public void setEnd_date_str(String end_date_str) {
		this.end_date_str = end_date_str;
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
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getStart_time_str() {
		return start_time_str;
	}
	public void setStart_time_str(String start_time_str) {
		this.start_time_str = start_time_str;
	}
	public String getEnd_time_str() {
		return end_time_str;
	}
	public void setEnd_time_str(String end_time_str) {
		this.end_time_str = end_time_str;
	}

}

