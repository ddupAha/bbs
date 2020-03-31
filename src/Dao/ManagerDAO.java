package Dao;

import Entity.SectionInfo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

	private BaseDAO dao = new BaseDAO();
	private ResultSet rs = null;
	private List<SectionInfo> cList = new ArrayList<SectionInfo>();// �Ѷ����ʽ�������а��
	private List<String> sign = new ArrayList<String>();// ����ָ����ͼƬ
	private List<String> allList = new ArrayList<String>();// ���ַ�����ʽ�������а�������
	/*
	 * �����б�ͼƬ�ļ�
	 */
	// ���ڵ�ͼƬ
	private final String rootImage = "<img src=\"image/tplus.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">"
			+ "<img src=\"image/folders.gif\" width=\"16\" height=\"16\" align=\"absmiddle\">";
	// ��һ�����ڵ�ͼƬ
	private final String firstRootImage = "<img src=\"image/rplus.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">"
			+ "<img src=\"image/folders.gif\" width=\"16\" height=\"16\" align=\"absmiddle\">";
	// ���һ�����ڵ�ͼƬ
	private final String lastRootImage = "<img src=\"image/lplus.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">"
			+ "<img src=\"image/folders.gif\" width=\"16\" height=\"16\" align=\"absmiddle\">";

	// �ӽڵ�ͼƬ
	private final String leafImage = "<img src=\"image/tminus.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">"
			+ "<img src=\"image/folder.gif\" width=\"16\" height=\"16\" align=\"absmiddle\">";
	// ���һ���ӽڵ�ͼƬ
	private final String lastLeafImage = "<img src=\"image/lminus.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">"
			+ "<img src=\"image/folder.gif\" width=\"16\" height=\"16\" align=\"absmiddle\">";

	// �հ�ͼƬ
	private final String noexpandImage = "<img src=\"image/noexpand.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">";
	// ����ͼƬ
	private final String iImage = "<img src=\"image/i.gif\" width=\"19\" height=\"20\" align=\"absmiddle\">";
	// С��ͷͼƬ
	private final String arrow = "<img src=\"image/cal_nextMonth.gif\" width=\"5\" height=\"9\">";

	/* ---------------�ĸ���ť--------------------- */

	// ��Ӱ�ť
	private final String addBt = "<input onClick=\"javascript:window.location.href=\'addSection.jsp\'\" type=\"button\" value=\"  ���\" style=\"background-image:url(image/add.gif); background-repeat:no-repeat; width:50px; height:18px;\">";
	// �༭��ť
	private final String sEditBt = "&nbsp;<input onClick=\"javascript:window.location.href=\'editSection.jsp?sid=";
	private final String eEditBt = "\'\" type=\"button\" value=\"  �༭\" style=\"background-image:url(image/submit.gif); background-repeat:no-repeat; width:50px; height:18px;\">";
	// ɾ����ť
	private final String sDelBt = "&nbsp;<input onClick=\"javascript:if(confirm(\'ɾ���ð����������\\n�Ƿ�Ҫɾ����\')){window.location.href=\'../ServletManager?action=del&sid=";
	private final String eDelBt = "\';}else{return false;}\" type=\"button\" value=\"  ɾ��\" style=\"background-image:url(image/del.gif); background-repeat:no-repeat; width:50px; height:18px;\">";
	// �ƶ���ť
	private final String sMoveBt = "&nbsp;<input onClick=\"javascript:window.location.href=\'moveSection.jsp?sid=";
	private final String eMoveBt = "\'\" type=\"button\" value=\"  �ƶ�\" style=\"background-image:url(image/del.gif); background-repeat:no-repeat; width:50px; height:18px;\">";

	/**
	 * ����id��������ĸ������Ϣ
	 * 
	 * @param id
	 *            �����
	 * @return SectionInfo ����һ������
	 */
	public SectionInfo getPSectionById(Integer id) {
		SectionInfo section = null;
		String sql = "select * from sectionInfo where sId = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { id });
			if (rs != null && rs.next()) {
				section = new SectionInfo();

				section.setSid(rs.getInt("sId"));
				section.setSmasterid(rs.getInt("sMasterId"));
				section.setSname(rs.getString("sName"));
				section.setSparentid(rs.getInt("sParentId"));
				section.setStopiccount(rs.getInt("sTopicCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return section;
	}

	/**
	 * ���ݰ��id����������Ӱ��
	 * 
	 * @param sId
	 *            �����
	 * @return List<SectionInfo> ����һ������
	 */
	public List<SectionInfo> getSectionById(Integer sId) {
		List<SectionInfo> list = new ArrayList<SectionInfo>();
		String sql = "select * from sectionInfo where sparentId = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });
			while (rs != null && rs.next()) {
				SectionInfo section = new SectionInfo();

				section.setSid(rs.getInt("sId"));
				section.setSmasterid(rs.getInt("sMasterId"));
				section.setSname(rs.getString("sName"));
				section.setSparentid(rs.getInt("sParentId"));
				section.setStopiccount(rs.getInt("sTopicCount"));
				list.add(section);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return list;
	}

	/**
	 * �ж϶����Ƿ�������ͬ������е����һ��
	 * 
	 * @param obj
	 *            Ҫ�Ƚϵ�SectionInfo����
	 * @return Boolean ����һ�������� true���� false����
	 */
	public Boolean isLastNode(SectionInfo obj) {
		SectionInfo section = null;
		String sql = "select top 1 * from sectionInfo where sparentId = ? order by sid desc";
		try {
			rs = dao.executeQuery(sql, new Object[] { obj.getSparentid() });
			if (rs != null && rs.next()) {
				section = new SectionInfo();

				section.setSid(rs.getInt("sId"));
				section.setSmasterid(rs.getInt("sMasterId"));
				section.setSname(rs.getString("sName"));
				section.setSparentid(rs.getInt("sParentId"));
				section.setStopiccount(rs.getInt("sTopicCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		// ��������һ����
		if (obj.getSid() == section.getSid()) {
			return true;
		}
		return false;
	}

	/**
	 * ����id���ݹ��ø��ڵ�
	 * 
	 * @param id
	 */
	private void traverseRootNodeById(Integer id) {//
		// ��ǰ��ͼƬ��
		SectionInfo parent = this.getPSectionById(id);// ��õ�ǰ�ڵ�ĸ��ڵ�
		if (parent != null && parent.getSparentid() == 0) {
			this.sign.add(iImage);// �������
		} else if (parent != null && parent.getSparentid() != 0) {// ����Ǹ��ڵ㣬�������ݹ�
			Integer result = this.getParentCount(parent.getSparentid());// ���ͬ�����ڵ�ĸ���
			if (this.isLastNode(parent) || result == 1) {
				this.sign.add(noexpandImage);// ��ӿո�
			} else if (result > 1) {
				this.sign.add(iImage);// �������
			}

			id = parent.getSparentid();

			traverseRootNodeById(id);// �ݹ�
		}
		return;
	}

	/**
	 * ����id���ݹ��ÿո����
	 * 
	 * @param id
	 *            �����
	 * @param blank
	 *            HTML��ʽ�Ŀո��
	 * @return String ���ؿո��ַ���
	 */
	public String traverseRootNodeById(Integer id, String blank) {//
		// ��ǰ��&nbsp;��
		String str = "";
		SectionInfo parent = this.getPSectionById(id);// ��õ�ǰ�ڵ�ĸ��ڵ�
		if (parent != null && parent.getSparentid() == 0) {
			str += blank;// �������
		} else if (parent != null && parent.getSparentid() != 0) {
			str += blank;
			id = parent.getSparentid();
			return str + traverseRootNodeById(id, blank);// �ݹ�
		}
		return str;
	}

	/**
	 * ����id�����ͬ���ڵ�ĸ���
	 * 
	 * @param id
	 *            �����
	 * @return Integer ����һ������
	 */
	private Integer getParentCount(Integer id) {
		String sql = "select count(*) as tt from sectionInfo where sparentId = ?";
		Integer result = 0;
		try {
			rs = dao.executeQuery(sql, new Object[] { id });
			if (rs != null && rs.next()) {
				result = rs.getInt("tt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result;
	}

	/**
	 * ���ݸ����Ľ����list�����㼶��ϵ��������Ӱ��
	 * 
	 * @param listParent
	 *            ���ڵ�Ľ����
	 * @return List<String> ����һ������
	 */
	public List<String> getAllSectionByList(List<SectionInfo> listParent) {
		int sParentId = -1;		//�������
		Boolean flag = false;// Ϊ���һ�����ڵ����Ǻ�
		String image = "";	//ͼƬ��Ϣ
		String name = "";	//������Ϣ
	
		for (int i = 0; i < listParent.size(); i++) {//�������еĸ����
			sParentId = listParent.get(i).getSid();	//��ø������
			if (i == 0) {							//��һ�����ڵ�
				image = firstRootImage;				//������ͼƬ��Ϣ
			} else if (i == listParent.size() - 1) {// ���һ�����ڵ�
				flag = true;						//����Ϊ���һ��
				image = lastRootImage;				//����ͼƬ��Ϣ
			} else {
				image = rootImage;					
			}
			name = "<a href=\"../servletListPage?sid="
			+ listParent.get(i).getSid() + "\" target=\"_blank\">"
			+ listParent.get(i).getSname() + "</a>";//���ð��������Ϣ
			allList.add("<div onMouseOver=\"this.style.backgroundColor=\'#7DDFFF\'\"" +
						" onMouseOut=\"this.style.backgroundColor=\'#FFFFFF\'\">"
						+ image + "&nbsp;"+ name
						+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ arrow + addBt+ sEditBt+ listParent.get(i).getSid()
						+ eEditBt+ sDelBt+ listParent.get(i).getSid()+ eDelBt
						+ sMoveBt+ listParent.get(i).getSid() + eMoveBt + "</div>");
			this.getAllLeafById(sParentId, flag);// ��õ�ǰ����µ������Ӱ��
		}
		return allList;
	}

	/**
	 * ���ݸ����Ľ����list�����㼶��ϵ��������Ӱ�����
	 * 
	 * @param listParent
	 *            ���ڵ�Ľ����
	 * @return List<SectionInfo> ����һ�������
	 */
	public List<SectionInfo> getAllSectionObjByList(List<SectionInfo> listParent) {
		// ��ǰ��&nbsp;��
		int sParentId = -1;

		for (int i = 0; i < listParent.size(); i++) {// ��ø��ڵ���󣨸���飩

			sParentId = listParent.get(i).getSid();

			// System.out.println("���ڵ㣺" + listParent.get(i).getSname());

			this.cList.add(listParent.get(i));// ��ڵ���뷺��
			this.getAllLeafById(sParentId);// ��õ�ǰ���ڵ��µ������ӽڵ�
		}
		return this.cList;
	}

	/**
	 * ���ݰ��id���ݹ��õ�ǰ���ڵ��µ������ӽڵ�
	 * 
	 * @param sId
	 *            ���id
	 * @param flag
	 *            �Ƿ����һ�����ڵ�
//	 * @param isLastNode
	 *            �Ƿ����һ���ڵ� false��δ֪�� true��ȷ���� ע��:�ڲ�ȷ�����������ʹ��false
	 */
	private void getAllLeafById(Integer sId, Boolean flag) {
		List<SectionInfo> list = this.getSectionById(sId);//��õ�ǰ����������Ӱ��
		String image = "";								//ͼƬ��Ϣ
		String signStr = "";							//�ָ���ַ���
		String name = "";								//���������Ϣ
		if (list.size() == 0) {							//����Ӱ����ĿΪ0
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			sId = list.get(i).getSid();					//��ð������Ϣ
			this.sign.clear(); 							//��ʼ���ָ��ͼƬ
			signStr = ""; 								//��ʼ���ָ���ַ���
			traverseRootNodeById(list.get(i).getSparentid());//��÷ָ��
			if (i == list.size() - 1) {					//�Ƿ������һ�����
				image = lastLeafImage;					//����ͼƬ��Ϣ
			} else {
				image = leafImage;						//����ͼƬ��Ϣ
			}
			if (flag && this.sign.size() != 0) {		//��ʽ���ָ��
				this.sign.set(this.sign.size() - 1, noexpandImage);
			}
			for (int j = this.sign.size(); j > 0; j--) {//�������sign��������
				signStr += this.sign.get(j - 1);
			}
			name = "<a href=\"../servletListPage?sid=" + list.get(i).getSid()
					+ "\" target=\"_blank\">&nbsp;" + list.get(i).getSname()
					+ "</a>";							//���ñ��������Ϣ
			allList.add("<div onMouseOver=\"this.style.backgroundColor=\'#7DDFFF\'\" " +
					"onMouseOut=\"this.style.backgroundColor=\'#FFFFFF\'\">"
					+ signStr + image + name
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ arrow + addBt + sEditBt + list.get(i).getSid() + eEditBt
					+ sDelBt + list.get(i).getSid() + eDelBt + sMoveBt
					+ list.get(i).getSid()+ eMoveBt+ "</div>");//��Ӱ��ڵ���Ϣ
			getAllLeafById(sId, flag);				//��������ִ�еݹ�
		}
	
	}

	/**
	 * ���ݰ��id���ݹ��õ�ǰ���ڵ��µ������ӽڵ�
	 * 
	 * @param sId
	 *            ���id
	 */
	private void getAllLeafById(Integer sId) {
		// ��ǰ��&nbsp;��
		List<SectionInfo> list = this.getSectionById(sId);// ��õ�ǰ�ڵ���ȫ���ӽڵ�

		if (list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			sId = list.get(i).getSid();
			cList.add(list.get(i));// ���ڵ���뷺��
			getAllLeafById(sId);// �ٴε����Լ����ݹ�)
		}

	}

	/**
	 * ��Ӱ��
	 * 
	 * @param sParentId
	 *            �������
	 * @param sName
	 *            �����
	 * @return Boolean ����һ��������
	 */
	public Boolean addSection(Integer sParentId, String sName,String uName) {
		String sql = "select uId from userInfo where " +
						"uName = ?";//�����û����������û����
		Integer sMasterId = 0;//��ʼ���û����
		try {
			rs = dao.executeQuery(sql, 
						new Object[] { uName });//ִ�в�ѯ
			if(rs.next()) {//����ܹ����ҵ���¼
				sMasterId = rs.getInt("uId");//�����û����
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Integer result = -1;
		sql = "insert into sectionInfo(sName,sParentId,sMasterId)" +
				" values(?,?,?)";//����SQL���
		try {
			result = dao.executeUpdate(sql, new Object[]
			                { sName, sParentId,sMasterId});//ִ�в���
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//�رս����
			dao.closeStatement();//�رմ������
			dao.closeConnection();//�ر����Ӷ���
		}
		return result > 0 ? true : false;
	}

	/**
	 * ���ݰ��id��ð����Ϣ
	 * 
	 * @param sId
	 *            �����
	 * @return SectionInfo ����һ������
	 */
	public SectionInfo getSectionNameById(Integer sId) {
		String sql = "select * from sectionInfo where sid = ?";
		SectionInfo obj = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });
			if (rs != null && rs.next()) {
				obj = new SectionInfo();
				obj.setSid(rs.getInt("sId"));
				obj.setSmasterid(rs.getInt("sMasterId"));
				obj.setSname(rs.getString("sName"));
				obj.setSparentid(rs.getInt("sParentId"));
				obj.setStopiccount(rs.getInt("sTopicCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return obj;
	}

	/**
	 * �޸İ����Ϣ
	 * 
	 * @param sId
	 *            �����
	 * @param sName
	 *            �����
	 * @return Boolean ����һ��������
	 */
	public Boolean updateSectionInfoById(Integer sId, String sName,String uName) {
		String sql = "select uId from userInfo where " +
									"uName = ?";//�����û����������û����
		Integer sMasterId = 0;//��ʼ���û����
		try {
			rs = dao.executeQuery(sql, 
				new Object[] { uName });//ִ�в�ѯ
			if(rs.next()) {//����ܹ����ҵ���¼
				sMasterId = rs.getInt("uId");//�����û����
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		sql = "update sectionInfo set sName = ?," +
					"sMasterId = ? where sId = ?";//����SQL���
		Integer result = 0;							//���¼�¼��
		try {
			result = dao.executeUpdate(sql, 
					new Object[] { sName,sMasterId, sId });//ִ�и���
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//�رս����
			dao.closeStatement();//�رմ������
			dao.closeConnection();//�ر����Ӷ���
		}
		return result > 0 ? true : false;	//�����Ƿ�ɹ�
	}

	/**
	 * ��Դ�����ΪĿ������Ӱ��
	 * 
	 * @param sourceSid
	 *            ԭ�����
	 * @param targetSid
	 *            Ŀ������
	 * @return Boolean ����һ�������� true:�ɹ�
	 */
	public Boolean moveToChildSectionInfoById(Integer sourceSid,
			Integer targetSid) {
		if (sourceSid == targetSid) {// �������ƶ�
			return false;
		}
		String sql = "update sectionInfo set sParentId = ? where sid = ?";
		Integer result = 0;
		try {
			result = dao.executeUpdate(sql,
					new Object[] { targetSid, sourceSid });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	/**
	 * ��Դ�����Ϊ�����
	 * 
	 * @param sourceSid
	 *            ԭ�����
	 * @return Boolean ����һ�������� true:�ɹ�
	 */
	public Boolean moveToRootSectionInfoById(Integer sourceSid) {
		String sql = "update sectionInfo set sParentId = 0 where sid = ?";
		Integer result = 0;
		try {
			result = dao.executeUpdate(sql, new Object[] { sourceSid });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	/**
	 * ���ݰ��id���ж��Ƿ����ӽڵ�
	 * 
	 * @param sid
	 *            �����
	 * @return Boolean ����һ�������� true����
	 */
	public Boolean isHaveChildNode(Integer sid) {
		String sql = "select * from sectionInfo" +
				" where sParentId = ?";//��ѯָ������µ��Ӱ��
		try {
			rs = dao.
				executeQuery(sql,new Object[]{sid});//ִ�в�ѯ
			if (rs != null && rs.next()) {	//������ڼ�¼
				return true;				//����true
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//�رս����
			dao.closeStatement();//�رմ������
			dao.closeConnection();//�ر����Ӷ���
		}
		return false;
	}

	/**
	 * ���ݰ��id��ɾ�����������͸���
	 * 
	 * @param sid
	 *            �����
	 * @return Boolean ����һ�������� true���ɹ�
	 */
	public Boolean delSectionInfo(Integer sid) {
		String sql1 = "delete from replyInfo where rSid = ?";// ��ɾ��������¼
		String sql2 = "delete from topicInfo where tSid = ?";// ��ɾ��������¼
		String sql3 = "delete from sectionInfo where sid = ?";// ���ɾ������¼
		Integer result = 0;
		try {
			result = dao.executeUpdate(sql1,new Object[] { sid });//ִ�и�����¼ɾ��
			result = dao.executeUpdate(sql2, new Object[] { sid });//ִ��������¼ɾ��
			result = dao.executeUpdate(sql3, new Object[] { sid });//ִ�а���¼ɾ��
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//�رս����
			dao.closeStatement();//�رմ������
			dao.closeConnection();//�ر����Ӷ���
		}
		return result > 0 ? true : false;
	}

	public static void main(String[] args) {
		// ��������
		SectionInfoDAO section_dao = new SectionInfoDAO();
		ManagerDAO manager_dao = new ManagerDAO();
		List<SectionInfo> clist = null;
		String blank = "";
		//
		List<SectionInfo> listParent = section_dao.getSectionById(0);
		clist = manager_dao.getAllSectionObjByList(listParent);
		// �Ȼ�����нڵ����
		for (int i = 0; i < clist.size(); i++) {

			// ���ݶ����spid ��ð����
			blank = manager_dao.traverseRootNodeById(clist.get(i)
					.getSparentid(), "&bnsp;");
			System.out.println(blank + clist.get(i).getSname());
		}

	}
}
