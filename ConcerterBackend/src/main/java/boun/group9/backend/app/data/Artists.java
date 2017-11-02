package boun.group9.backend.app.data;

public class Artists {
	private int id;
	private String name;
	public Artists() {
		
	}
	public Artists(String name) {
		this.name = name;
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
}
