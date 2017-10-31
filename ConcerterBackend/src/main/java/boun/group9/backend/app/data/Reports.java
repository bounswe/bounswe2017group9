package boun.group9.backend.app.data;

public class Reports {
	private int id;
	private int concert_id;
	private int reported_by;
	private int report_type;
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
	public int getReported_by() {
		return reported_by;
	}
	public void setReported_by(int reported_by) {
		this.reported_by = reported_by;
	}
	public int getReport_type() {
		return report_type;
	}
	public void setReport_type(int report_type) {
		this.report_type = report_type;
	}
}
