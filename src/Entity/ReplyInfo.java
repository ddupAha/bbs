package Entity;

import java.util.Date;

public class ReplyInfo{
	private Integer rid;			// �������
	private Integer rtid;			// �ظ����������
	private Integer rsid; 			// �������ڰ����
	private Integer ruid; 			// �����˱��
	private String rtopic; 			// ��������
	private String rcontents; 		// ��������
	private Date rpublishtime; 		// ����ʱ��
	private Date rmodifytime; 		// �޸�ʱ��
	
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
