package Entity;

import java.util.Date;

public class TopicInfo {
	private Integer tid;			// ���ӱ��
	private Integer tsid;			// ���ڰ����
	private Integer tuid;			// �����û����
	private String ttopic;			// ���ӱ���
	private String tcontents;		// ��������
	private Integer treplycount;	// �ظ�����
	private Integer tclickcount; 	// �������
	private Date tpublishtime;		// ����ʱ��
	private Date tmodifytime; 		// �޸�ʱ��



	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getTsid() {
		return tsid;
	}

	public void setTsid(Integer tsid) {
		this.tsid = tsid;
	}

	public Integer getTuid() {
		return tuid;
	}

	public void setTuid(Integer tuid) {
		this.tuid = tuid;
	}

	public String getTtopic() {
		return ttopic;
	}

	public void setTtopic(String ttopic) {
		this.ttopic = ttopic;
	}

	public String getTcontents() {
		return tcontents;
	}

	public void setTcontents(String tcontents) {
		this.tcontents = tcontents;
	}

	public Integer getTreplycount() {
		return treplycount;
	}

	public void setTreplycount(Integer treplycount) {
		this.treplycount = treplycount;
	}

	public Integer getTclickcount() {
		return tclickcount;
	}

	public void setTclickcount(Integer tclickcount) {
		this.tclickcount = tclickcount;
	}

	public Date getTpublishtime() {
		return tpublishtime;
	}

	public void setTpublishtime(Date tpublishtime) {
		this.tpublishtime = tpublishtime;
	}

	public Date getTmodifytime() {
		return tmodifytime;
	}

	public void setTmodifytime(Date tmodifytime) {
		this.tmodifytime = tmodifytime;
	}
}
