package Dao;

import Entity.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserInfoDAO {
	private BaseDAO dao = new BaseDAO();

	/**
	 * ����ע���û�
	 * 
	 * @param userName
	 *            �û���
	 * @param passWord
	 *            ����
	 * @param sex
	 *            �Ա�
	 * @param userFace
	 *            ͷ��ͼƬ��
	 * @return boolean ����true��ʾע��ɹ�
	 */
	public boolean checkReg(String userName, String passWord, boolean sex,
			String userFace) {
		String sql = "insert into userInfo(uName,uPassWord,uSex,uFace) values(?,?,?,?)";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { userName, passWord,
					sex, userFace });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	// ����
	public UserInfo getUserInfoByID(int uid) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uId=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { uid });
			if (rs != null && rs.next()) {
				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return user;
	}
	
	/**
	 * ����û���¼��������
	 * 
	 * @param userName
	 *            �û���
	 * @param passWord
	 *            ����
	 * @return boolean ����true��ʾ�û��Ϸ�
	 */
	public boolean checkLogin(String userName, String passWord) {
		String sql = "select * from userInfo where uName=? and uPassWord=? ";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { userName, passWord, });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return (rs != null && rs.next()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return false;
	}

	/**
	 * �����û���������û���Ϣ
	 * 
	 * @param userName
	 *            �û���
	 * @return UserInfo ����һ������
	 */
	public UserInfo getUserInfo(String userName) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uName=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { userName });
			if (rs != null && rs.next()) {

				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return user;
	}


	public UserInfo getUserInfoByName(String name) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uname=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { name });
			if (rs != null && rs.next()) {
				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		if(user.getUid()==null)
			return null;
		return user;
	}


	public Boolean delUserInfo(Integer uid) {
		String sql1 = "delete from replyInfo where rUid = ?";//   ?        ?
		String sql2 = "delete from topicInfo where tUid = ?";//   ?        ?
		String sql3 = "delete from userInfo where uid = ?";//    ?      ?
		Integer result = 0;
		try {
			result = dao.executeUpdate(sql1,new Object[] { uid });//? ��     ??
			result = dao.executeUpdate(sql2, new Object[] { uid });//?        ??
			result = dao.executeUpdate(sql3, new Object[] { uid });//? ��   ??
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();// ??
			dao.closeStatement();// ??
			dao.closeConnection();// ?    ?
		}
		return result > 0 ? true : false;
	}


	public Boolean updateUserType(int id,int type) {
		int result = -1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(new Date());
		// ��\r\n�滻��<br>

		String sql = "update userinfo set utype="+type+" where uid = ?";
		try {
			result = dao.executeUpdate(sql, new Object[] {id});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}


	public Boolean updateUser(int id,String name, String face, int sex) {
		int result = -1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(new Date());
		// ��\r\n�滻��<br>

		String sql = "update userinfo set uname = ?,uface = ?,usex = ? where uid = ?";
		try {
			result = dao.executeUpdate(sql, new Object[] { name, face,
					sex, id });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}



	public ArrayList<UserInfo> getAllUserInfo() {
		ArrayList<UserInfo> list=new ArrayList<>();
		String sql = "select * from userInfo";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					UserInfo user = new UserInfo();
					user.setUid(rs.getInt("uId"));
					user.setUname(rs.getString("uName"));
					user.setUpassword(rs.getString("uPassWord"));
					user.setUsex(rs.getBoolean("uSex"));
					user.setUface(rs.getString("uFace"));
					user.setUregtime(rs.getDate("uRegTime"));
					user.setUtype(rs.getInt("uType"));
					list.add(user);
				}
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



	public Boolean insert(String name, String pwd, int sex,
						  String face) {
		int result = -1;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateString = formatter.format(date);

		String sql = "insert into userinfo(uname,upassword,usex,uface,uregtime,utype)"
				+ " values(?,?,?,?,?,?)";
		//   \r\n �I  <br>

//		System.out.println("in dao  "+ content);
		try {
			result = dao.executeUpdate(sql, new Object[] { name, pwd, sex,
					face,dateString,0});
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
	 * �����û�����id���������
	 * 
	 * @param id
	 *            �û����ͱ��
	 * @return �����û���������
	 */
	public String getUserTypeNameById(Integer id) {
		switch (id) {
		case 0:
			return "��Ա";
		case 1:
			return "����";
		case 2:
			return "����Ա";
		}
		return "";
	}

	/**
	 * ���ݲ���ֵ����Ա�����
	 * 
	 * @param sex
	 *            ����ֵ true���� false��Ů
	 * @return �����Ա���
	 */
	public String getSexName(Boolean sex) {
		return sex ? "��" : "Ů";
	}
}
