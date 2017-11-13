package boun.group9.backend.app.data;

public class Locations {
	private int id;
	private double longitude;
	private double latitude;
	private String city;
	private String address;
	public Locations() {
		
	}
	public Locations(double longitude,double latitude,String city,String address) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.city = city;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
