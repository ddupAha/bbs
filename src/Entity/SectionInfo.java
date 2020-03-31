package Entity;

public class SectionInfo {

	private Integer sid; // °æ¿é±àºÅ
	private String sname;// °æ¿éÃû
	private Integer smasterid;// °æÖ÷±àºÅ
	private Integer stopiccount;// Ìû×ÓÊıÁ¿
	private Integer sparentid;// ¸¸°æ¿é±àºÅ

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSmasterid() {
		return smasterid;
	}

	public void setSmasterid(Integer smasterid) {
		this.smasterid = smasterid;
	}

	public Integer getStopiccount() {
		return stopiccount;
	}

	public void setStopiccount(Integer stopiccount) {
		this.stopiccount = stopiccount;
	}

	public Integer getSparentid() {
		return sparentid;
	}

	public void setSparentid(Integer sparentid) {
		this.sparentid = sparentid;
	}

}
