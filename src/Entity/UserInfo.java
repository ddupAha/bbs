package Entity;

import java.util.Date;

public class UserInfo {
	private Integer uid;			//�û�id
	private String uname;			//�û���
	private String upassword;		//�û�����
	private Boolean usex;			//�û��Ա�
	private String uface;			//�û�ͷ��·��
	private Date uregtime;			//�û�ע��ʱ��
	private Integer utype;			//�û�����
	
	public Integer getUtype() {
		return utype;
	}
	public void setUtype(Integer utype) {
		this.utype = utype;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public Boolean getUsex() {
		return usex;
	}
	public void setUsex(Boolean usex) {
		this.usex = usex;
	}
	public String getUface() {
		return uface;
	}
	public void setUface(String uface) {
		this.uface = uface;
	}
	public Date getUregtime() {
		return uregtime;
	}
	public void setUregtime(Date uregtime) {
		this.uregtime = uregtime;
	}
}

