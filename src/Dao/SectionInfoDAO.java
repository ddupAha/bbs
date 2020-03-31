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
	 * 根据版块id，获得所有子版块
	 * 
	 * @param sId
	 *            版块编号
	 * @return List<SectionInfo> 返回一个集合
	 */
	public List<SectionInfo> getSectionById(Integer sId) {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//版块列表
		String sql = "select * from sectionInfo " +
								"where sparentId = ?";//根据版块编号查找子版块
		try {
			rs = dao.executeQuery(sql, new Object[]{sId });//执行查询
			while (rs != null && rs.next()) {		//如果查找到记录
				SectionInfo section = new SectionInfo();//版块信息对象
				section.setSid(rs.getInt("sId"));		//设置版块编号
				section.setSmasterid(rs.getInt("sMasterId"));//设置版主编号
				section.setSname(rs.getString("sName"));	//设置版块名称
				section.setSparentid(rs.getInt("sParentId"));//设置父版块编号
				section.setStopiccount(rs.getInt("sTopicCount"));//设置版块主题数
				list.add(section);//添加版块对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return list;					//返回版块列表
	}

	public List<SectionInfo> getPSection() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//    б
		String sql = "select * from sectionInfo " +
				"where sparentId = 0";//   ?   ?    ?
		try {
			rs = dao.executeQuery(sql);//? в ?
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
		return list;					//   ?   б
	}




	public Boolean updateSectionInfoById(Integer sId, String sName) {
		String sql = "update sectionInfo set sName = ? " + " where sId = ?";//    SQL
		Integer result = 0;							//   ? ?
		try {
			result = dao.executeUpdate(sql,
					new Object[] { sName, sId });//? и
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
	 * 根据id，获得它的父版块信息
	 * 
//	 * @param id
	 *            版块编号
	 * @return List<SectionInfo> 返回一个对象
	 */

	public SectionInfo getSectionByName(String name) {
		System.out.println("sql的开始：'"+name+"'");
		String sql = "select * from sectionInfo " +
				"where sName = '"+name+"'";//根据版块编号查找子版块
		SectionInfo section = new SectionInfo();
		System.out.println(sql);
		System.out.println("this is sql Name  " + name);
		try {
			rs = dao.executeQuery(sql);//执行查询
			System.out.println(rs);
			//版块信息对象
			if (rs != null && rs.next()) {		//如果查找到记录
				System.out.println(1234556);
				section.setSid(rs.getInt("sId"));		//设置版块编号
				section.setSmasterid(rs.getInt("sMasterId"));//设置版主编号
				section.setSname(rs.getString("sName"));	//设置版块名称
				section.setSparentid(rs.getInt("sParentId"));//设置父版块编号
				section.setStopiccount(rs.getInt("sTopicCount"));//设置版块主题数
			}
			System.out.println("this is sq   "+section.getSname());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return section;					//返回版块列表
	}






	public List<SectionInfo> getChildBoards() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//版块列表
		String sql = "select * from sectionInfo " +
				"where sparentId != 0";//根据版块编号查找子版块
		try {
			rs = dao.executeQuery(sql);//执行查询
			while (rs != null && rs.next()) {		//如果查找到记录
				SectionInfo section = new SectionInfo();//版块信息对象
				section.setSid(rs.getInt("sId"));		//设置版块编号
				section.setSmasterid(rs.getInt("sMasterId"));//设置版主编号
				section.setSname(rs.getString("sName"));	//设置版块名称
				section.setSparentid(rs.getInt("sParentId"));//设置父版块编号
				section.setStopiccount(rs.getInt("sTopicCount"));//设置版块主题数
				list.add(section);//添加版块对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return list;					//返回版块列表
	}





	public List<SectionInfo> getAll() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();//版块列表
		String sql = "select * from sectionInfo ";
		try {
			rs = dao.executeQuery(sql);//执行查询
			while (rs != null && rs.next()) {		//如果查找到记录
				SectionInfo section = new SectionInfo();//版块信息对象
				section.setSid(rs.getInt("sId"));		//设置版块编号
				section.setSmasterid(rs.getInt("sMasterId"));//设置版主编号
				section.setSname(rs.getString("sName"));	//设置版块名称
				section.setSparentid(rs.getInt("sParentId"));//设置父版块编号
				section.setStopiccount(rs.getInt("sTopicCount"));//设置版块主题数
				list.add(section);//添加版块对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();		//关闭结果集
			dao.closeStatement();		//关闭处理对象
			dao.closeConnection();		//关闭连接对象
		}
		return list;					//返回版块列表
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
	 * 根据版块id获得导航条
	 * 
	 * @param sId
	 *            版块编号
	 * @return List<String> 返回一个集合 注意:如果在同一个类中多次调用此方法，则必须调用clearList()清除上一次的结果
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
	 * 清空list对象
	 */
	public void clearList() {
		this.list.clear();
	}

	/**
	 * 根据版块id判断是否父版块
	 * 
	 * @param sId
	 *            版块编号
	 * @return Boolean 返回一个布尔型
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
