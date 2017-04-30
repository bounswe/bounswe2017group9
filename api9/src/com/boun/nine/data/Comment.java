package com.boun.nine.data;
import java.util.Date;
public class Comment {
	private int id;
	private int concertId;
	private int owner_id;
	private int up_vote;
	private int down_vote;
	private String content;
	private Date date;
	public int getId() {
		return id;
	}
	public int getConcertId() {
		return concertId;
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
		return owner_id;
	}
	public void setOwnerId(int owner_id) {
		this.owner_id = owner_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int get_up_vote()
	{
		return up_vote;
	}
	public int get_down_vote()
	{
		return down_vote;
	}
	public void set_up_vote(int upVote)
	{
		this.up_vote = upVote;
	}
	public void set_down_vote(int downVote)
	{
		this.down_vote = downVote;
	}
	public void up_vote()
	{
		//TODO
		this.up_vote+=1;
	}
	public void down_vote()
	{
		this.down_vote+=1;
	}
}
