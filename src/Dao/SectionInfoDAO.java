package Dao;


import Entity.SectionInfo;
import page.NavigationPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionInfoDAO {

	private BaseDAO dao = new BaseDAO();
	private ResultSet rs = null;
	private List<NavigationPage> list = new ArrayList<NavigationPage>();

	/**
	 * ���ݰ��id����������Ӱ��
	 * 
	 * @param sId
	 *            �����
	 * @return List<SectionInfo> ����һ������
	 */
	public List<SectionInfo> getSectionById(Integer sId) {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//����б�
		String sql = "select * from sectionInfo " +
								"where sparentId = ?";//���ݰ���Ų����Ӱ��
		try {
			rs = dao.executeQuery(sql, new Object[]{sId });//ִ�в�ѯ
			while (rs != null && rs.next()) {		//������ҵ���¼
				SectionInfo section = new SectionInfo();//�����Ϣ����
				section.setSid(rs.getInt("sId"));		//���ð����
				section.setSmasterid(rs.getInt("sMasterId"));//���ð������
				section.setSname(rs.getString("sName"));	//���ð������
				section.setSparentid(rs.getInt("sParentId"));//���ø������
				section.setStopiccount(rs.getInt("sTopicCount"));//���ð��������
				list.add(section);//��Ӱ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
		}
		return list;					//���ذ���б�
	}

	public List<SectionInfo> getPSection() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//    ��
		String sql = "select * from sectionInfo " +
				"where sparentId = 0";//   ?   ?    ?
		try {
			rs = dao.executeQuery(sql);//? �� ?
			while (rs != null && rs.next()) {		//      ?   ?
				SectionInfo section = new SectionInfo();//     ?
				section.setSid(rs.getInt("sId"));		//   ?
				section.setSmasterid(rs.getInt("sMasterId"));//   ?
				section.setSname(rs.getString("sName"));	//   ?
				section.setSparentid(rs.getInt("sParentId"));//   ?
				section.setStopiccount(rs.getInt("sTopicCount"));//   ?
				list.add(section);//  ?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		// ??
			dao.closeStatement();		// ??
			dao.closeConnection();		// ?    ?
		}
		return list;					//   ?   ��
	}




	public Boolean updateSectionInfoById(Integer sId, String sName) {
		String sql = "update sectionInfo set sName = ? " + " where sId = ?";//    SQL
		Integer result = 0;							//   ? ?
		try {
			result = dao.executeUpdate(sql,
					new Object[] { sName, sId });//? ��
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();// ??
			dao.closeStatement();// ??
			dao.closeConnection();// ?    ?
		}
		return result > 0 ? true : false;	//     ? ?
	}





	/**
	 * ����id��������ĸ������Ϣ
	 * 
//	 * @param id
	 *            �����
	 * @return List<SectionInfo> ����һ������
	 */

	public SectionInfo getSectionByName(String name) {
		System.out.println("sql�Ŀ�ʼ��'"+name+"'");
		String sql = "select * from sectionInfo " +
				"where sName = '"+name+"'";//���ݰ���Ų����Ӱ��
		SectionInfo section = new SectionInfo();
		System.out.println(sql);
		System.out.println("this is sql Name  " + name);
		try {
			rs = dao.executeQuery(sql);//ִ�в�ѯ
			System.out.println(rs);
			//�����Ϣ����
			if (rs != null && rs.next()) {		//������ҵ���¼
				System.out.println(1234556);
				section.setSid(rs.getInt("sId"));		//���ð����
				section.setSmasterid(rs.getInt("sMasterId"));//���ð������
				section.setSname(rs.getString("sName"));	//���ð������
				section.setSparentid(rs.getInt("sParentId"));//���ø������
				section.setStopiccount(rs.getInt("sTopicCount"));//���ð��������
			}
			System.out.println("this is sq   "+section.getSname());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
		}
		return section;					//���ذ���б�
	}






	public List<SectionInfo> getChildBoards() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//����б�
		String sql = "select * from sectionInfo " +
				"where sparentId != 0";//���ݰ���Ų����Ӱ��
		try {
			rs = dao.executeQuery(sql);//ִ�в�ѯ
			while (rs != null && rs.next()) {		//������ҵ���¼
				SectionInfo section = new SectionInfo();//�����Ϣ����
				section.setSid(rs.getInt("sId"));		//���ð����
				section.setSmasterid(rs.getInt("sMasterId"));//���ð������
				section.setSname(rs.getString("sName"));	//���ð������
				section.setSparentid(rs.getInt("sParentId"));//���ø������
				section.setStopiccount(rs.getInt("sTopicCount"));//���ð��������
				list.add(section);//��Ӱ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
		}
		return list;					//���ذ���б�
	}





	public List<SectionInfo> getAll() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//����б�
		String sql = "select * from sectionInfo ";
		try {
			rs = dao.executeQuery(sql);//ִ�в�ѯ
			while (rs != null && rs.next()) {		//������ҵ���¼
				SectionInfo section = new SectionInfo();//�����Ϣ����
				section.setSid(rs.getInt("sId"));		//���ð����
				section.setSmasterid(rs.getInt("sMasterId"));//���ð������
				section.setSname(rs.getString("sName"));	//���ð������
				section.setSparentid(rs.getInt("sParentId"));//���ø������
				section.setStopiccount(rs.getInt("sTopicCount"));//���ð��������
				list.add(section);//��Ӱ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//�رս����
			dao.closeStatement();		//�رմ������
			dao.closeConnection();		//�ر����Ӷ���
		}
		return list;					//���ذ���б�
	}


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
	 * ���ݰ��id��õ�����
	 * 
	 * @param sId
	 *            �����
	 * @return List<String> ����һ������ ע��:�����ͬһ�����ж�ε��ô˷�������������clearList()�����һ�εĽ��
	 */
	public List<NavigationPage> getNavigationMenuById(Integer sId) {
		String sql = "select sParentId,sName,sId from sectionInfo where sid = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });
			if (rs != null && rs.next()) {
				NavigationPage temp = new NavigationPage();
				temp.setParentid(rs.getInt("sParentId"));
				temp.setSid(rs.getInt("sId"));
				temp.setSname(rs.getString("sName"));
				list.add(temp);
				this.getNavigationMenuById(rs.getInt("sParentId"));
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
	 * ���list����
	 */
	public void clearList() {
		this.list.clear();
	}

	/**
	 * ���ݰ��id�ж��Ƿ񸸰��
	 * 
	 * @param sId
	 *            �����
	 * @return Boolean ����һ��������
	 */
	public Boolean isParentById(Integer sId) {
		String sql = "select * from sectionInfo where sid = ? and sParentId = 0";
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });
			if (rs != null && rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return false;
	}

}
