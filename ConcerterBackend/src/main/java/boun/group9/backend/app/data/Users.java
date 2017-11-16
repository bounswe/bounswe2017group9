package boun.group9.backend.app.data;

import java.sql.Timestamp;
import java.util.Date;

public class Users {
	private int id;
	
	private String name;
	private String email;
	private String password;
	private int followers;
	private int followings;
	private String photo_path;
	private Date created_at;
	private Date updated_at;
	private Date last_login;
	private String username;
	public Users() {
		
	}
	public Users(Spotify_user spotifyUser) {
		this.name = spotifyUser.getDisplay_name();
		this.email = spotifyUser.getEmail();
		this.photo_path = spotifyUser.getImages().get(0).getUrl();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public int getFollowings() {
		return followings;
	}
	public void setFollowings(int followings) {
		this.followings = followings;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = new Date(created_at.getTime());
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = new Date(updated_at.getTime());
	}
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Timestamp last_login) {
		this.last_login = new Date(last_login.getTime());
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
