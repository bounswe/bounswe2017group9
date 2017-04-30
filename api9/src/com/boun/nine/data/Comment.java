package com.boun.nine.data;
import java.util.Date;

public class Comment {
	private String content;
	private int id;
	private int concertID;
	private Date dateTime;
	private int owner;
	private int upVote;
	private int downVote;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getConcertID() {
		return concertID;
	}
	public void setConcertID(int concertID) {
		this.concertID = concertID;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public long getUpVote() {
		return upVote;
	}
	public void setUpVote(int upVote) {
		this.upVote = upVote;
	}
	public long getDownVote() {
		return downVote;
	}
	public void setDownVote(int downVote) {
		this.downVote = downVote;
	}
	
}
