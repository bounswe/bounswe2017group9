package boun.group9.backend.app.data;

public class MusicalInterests {
	 private int id;
	    private String musicalInterestId; //wikidata id
	    private int user_id;
	    private String label;	//wikidata label

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getUser_id() {
	        return user_id;
	    }

	    public void setUser_id(int user_id) {
	        this.user_id = user_id;
	    }

	    public String getLabel() {
	        return label;
	    }

	    public void setLabel(String label) {
	        this.label = label;
	    }

	    public String getMusicalInterestId() {
	        return musicalInterestId;
	    }

	    public void setMusicalInterestId(String musicalInterestId) {
	        this.musicalInterestId = musicalInterestId;
	    }
	}
