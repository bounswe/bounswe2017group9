package boun.group9.webservice.app.data;

public class SemanticTags {
	private String description;	//wikidata description
	private String search;		//search word for semantic tag
	private int id;
	private String semanticTagId;
	private int concert_id;
	private String label;	//wikidata label


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
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
