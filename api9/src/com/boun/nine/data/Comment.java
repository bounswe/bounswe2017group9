package com.boun.nine.data;
import java.util.Date;
public class Comment {
	private int id;
	private int concertId;
	private int ownerId;
	private int upVote;
	private int downVote;
	private String content;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setConcertId(int concertId) {
		this.concertId = concertId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUpVote()
	{
		return upVote;
	}
	public int getDownVote()
	{
		return downVote;
	}
	public void upVote()
	{
		//TODO
		this.upVote+=1;
	}
	public void downVote()
	{
		this.downVote+=1;
	}
}
