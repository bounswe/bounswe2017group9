package boun.group9.backend.app.data;

import java.util.ArrayList;

public class Spotify_user {
	private String display_name;
	private String email;
	private String href;
	private String id;
	private String type;
	private String uri;
	private ArrayList<Spotify_image> images;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public ArrayList<Spotify_image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Spotify_image> images) {
		this.images = images;
	}
}
