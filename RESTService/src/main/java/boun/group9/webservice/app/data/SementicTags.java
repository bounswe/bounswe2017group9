package boun.group9.webservice.app.data;

public class SementicTags {
	private String id;			//wikidata id
	private String label;		//wikidata label
	private String search;		//search word for sementic tag
	private String description;	//wikidata description
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
