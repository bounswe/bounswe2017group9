package boun.group9.webservice.app.data;

public class SemanticTags {
	private int id;			//wikidata id
	private String semanticTagId;		//wikidata label
	private int concertId;		//search word for semantic tag
	private String label;	//wikidata description
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConcertId() {
		return concertId;
	}
	public void setConcertId(int concertId) {
		this.concertId = concertId;
	}
	public String getSemanticTagId() {
		return semanticTagId;
	}
	public void setSemanticTagId(String semanticTagId) {
		this.semanticTagId = semanticTagId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

}
