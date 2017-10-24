package boun.group9.webservice.app.data;

public class Comments {
	private int id;
	private int commented_by;
	private int up_votes;
	private int down_votes;
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommented_by() {
		return commented_by;
	}
	public void setCommented_by(int commented_by) {
		this.commented_by = commented_by;
	}
	public int getUp_votes() {
		return up_votes;
	}
	public void setUp_votes(int up_votes) {
		this.up_votes = up_votes;
	}
	public int getDown_votes() {
		return down_votes;
	}
	public void setDown_votes(int down_votes) {
		this.down_votes = down_votes;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
