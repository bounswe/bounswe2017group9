package boun.group9.webservice.app.data;

import java.util.Date;

public class Relations {
	private int id;
	private int follower_id; //takip eden
	private int following_id; //takip edilen
	private Date created_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFollower_id() {
		return follower_id;
	}
	public void setFollower_id(int follower_id) {
		this.follower_id = follower_id;
	}
	public int getFollowing_id() {
		return following_id;
	}
	public void setFollowing_id(int following_id) {
		this.following_id = following_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
