package boun.group9.webservice.app.data;

import java.util.Date;

public class Attendees {
	private int id;
	private int concert_id;
	private int status;
	private Date created_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConcert_id() {
		return concert_id;
	}
	public void setConcert_id(int concert_id) {
		this.concert_id = concert_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
