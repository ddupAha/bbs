package Entity;

import java.util.Date;

public class ReplyInfo{
	private Integer rid;			// ¸úÌû±àºÅ
	private Integer rtid;			// »Ø¸´µÄÖ÷Ìû±àºÅ
	private Integer rsid; 			// ¸úÌûËùÔÚ°æ¿é±àºÅ
	private Integer ruid; 			// ¸úÌûÈË±àºÅ
	private String rtopic; 			// ¸úÌûÖ÷Ìâ
	private String rcontents; 		// ¸úÌûÄÚÈİ
	private Date rpublishtime; 		// ¸úÌûÊ±¼ä
	private Date rmodifytime; 		// ĞŞ¸ÄÊ±¼ä
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getRtid() {
		return rtid;
	}
	public void setRtid(Integer rtid) {
		this.rtid = rtid;
	}
	public Integer getRsid() {
		return rsid;
	}
	public void setRsid(Integer rsid) {
		this.rsid = rsid;
	}
	public Integer getRuid() {
		return ruid;
	}
	public void setRuid(Integer ruid) {
		this.ruid = ruid;
	}
	public String getRtopic() {
		return rtopic;
	}
	public void setRtopic(String rtopic) {
		this.rtopic = rtopic;
	}
	public String getRcontents() {
		return rcontents;
	}
	public void setRcontents(String rcontents) {
		this.rcontents = rcontents;
	}
	public Date getRpublishtime() {
		return rpublishtime;
	}
	public void setRpublishtime(Date rpublishtime) {
		this.rpublishtime = rpublishtime;
	}
	public Date getRmodifytime() {
		return rmodifytime;
	}
	public void setRmodifytime(Date rmodifytime) {
		this.rmodifytime = rmodifytime;
	}
}
