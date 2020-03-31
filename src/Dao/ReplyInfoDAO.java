package Dao;

import Entity.*;
import page.*;
import page.IndexPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReplyInfoDAO {

	private BaseDAO dao = new BaseDAO();
	private ResultSet rs = null;

	/**
	 * ���ݵ�ǰ���id�����ͬһ������������������һ��������¼
	 * 
	 * @param sId
	 *            �����
	 * @param tId
	 *            �������
	 * @return IndexPage ����һ������
	 */
	public IndexPage getAllReplyLastTimeById(Integer sId, Integer tId) {
		IndexPage index_page = null;
		String sql = "select (select uName from userInfo where userInfo.uId= tb.rUid ) as author,rTopic as title,rPublishTime as publishTime,rTid as tid"
				+ " from "
				+ " (select * from replyInfo where rPublishTime =(select max(rPublishTime) from replyInfo where rSid = ? and rTid = ?)) as tb";
		try {
			rs = dao.executeQuery(sql, new Object[] { sId, tId });
			if (rs != null && rs.next()) {
				index_page = new IndexPage();
				//
				index_page.setAuthor(rs.getString("author"));
				index_page.setPublishtime(rs.getTimestamp("publishTime"));
				index_page.setTid(rs.getInt("tid"));
				index_page.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return index_page;
	}

	/**
	 * ��������id��ø����û���Ϣ�ļ���
	 * 
	 * @param tid
	 *            �������
	 * @return List<DetailPage> ����һ������
	 */
	public List<DetailPage> getReplyInfoAndUserInfoById(Integer tid) {
		List<DetailPage> list = null;
		String sql = "select rTopic,rContents,rPublishTime,rModifyTime,rId,rUid,"
				+ "(select uName from  userInfo where userInfo.uid = replyInfo.ruid) as rName,"
				+ "(select uFace from  userInfo where userInfo.uid = replyInfo.ruid) as rFace,"
				+ "(select uRegTime from  userInfo where userInfo.uid = replyInfo.ruid) as rRegTime,"
				+ "(select uType from  userInfo where userInfo.uid = replyInfo.ruid) as rUserType,"
				+ "(select uSex from  userInfo where userInfo.uid = replyInfo.ruid) as rUsex"
				+ " from replyInfo where rtid in (?) order by rPublishTime";
		try {
			rs = dao.executeQuery(sql, new Object[] { tid });
			if (rs != null) {
				list = new ArrayList<DetailPage>();
				while (rs.next()) {
					DetailPage temp = new DetailPage();
					//
					temp.setContents(rs.getString("rContents"));
					temp.setFace(rs.getString("rFace"));
					temp.setModifytime(rs.getTimestamp("rModifyTime"));
					temp.setName(rs.getString("rName"));
					temp.setPublishtime(rs.getTimestamp("rPublishTime"));
					temp.setRegtime(rs.getDate("rRegTime"));
					temp.setTitle(rs.getString("rTopic"));
					temp.setType(rs.getInt("rUserType"));
					temp.setSex(rs.getBoolean("rUsex"));
					temp.setId(rs.getInt("rId"));
					temp.setUid(rs.getInt("rUid"));
					//
					list.add(temp);
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

	/**
	 * ���ݸ���id�������Ҫ�޸ĵ���Ϣ
	 * 
	 * @param rid
	 *            �������
	 * @return ReplyInfo ����һ������
	 */
	public ReplyInfo getEditReplyInfoById(Integer rid) {
		ReplyInfo replyinfo = null;
		String sql = "select * from replyInfo where rid = ?";
		String content = "";
		try {
			rs = dao.executeQuery(sql, new Object[] { rid });
			if (rs != null && rs.next()) {
				replyinfo = new ReplyInfo();
				//
				replyinfo.setRmodifytime(rs.getDate("rModifyTime"));
				replyinfo.setRpublishtime(rs.getDate("rPublishTime"));
				// <br>�滻��\r\n
				content = rs.getString("rContents").replace("<br>", "\r\n");
				content = content.replace("&gt;", ">");
				content = content.replace("&lt;", "<");
				content = content.replace("&nbsp", " ");

				replyinfo.setRcontents(content);
				replyinfo.setRid(rs.getInt("rId"));
				replyinfo.setRsid(rs.getInt("rSId"));
				replyinfo.setRtid(rs.getInt("rTId"));
				replyinfo.setRtopic(rs.getString("rTopic"));
				replyinfo.setRuid(rs.getInt("rUId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return replyinfo;
	}

	/**
	 * ���ݸ���id���¸������������
	 * 
	 * @param title
	 *            ����
	 * @param content
	 *            ����
	 * @param rid
	 *            �������
	 * @return Boolean ���ز����� true����ʾ�޸ĳɹ�
	 */
	public Boolean updateReplyInfoById(String title, String content, Integer rid) {
		int result = -1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(new Date());
		// ��\r\n�滻��<br>
		content = content.replace("<", "&lt;");
		content = content.replace(">", "&gt;");
		content = content.replace(" ", "&nbsp");
		content = content.replace("\r\n", "<br>");
		
		String sql = "update replyInfo set rTopic = ?,rContents = ?,rModifyTime = ? where rid = ?";
		try {
			result = dao.executeUpdate(sql, new Object[] { title, content,
					time, rid });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}



	public Boolean delReplyInfoByTid(Integer tid) {
		int result = -1;
		String sql = "delete from replyInfo where rtid = ?";
		try {
			result = dao.executeUpdate(sql, new Object[] { tid });
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
	 * ���ݸ���idɾ������
	 * 
	 * @param rid
	 *            �������
	 * @return Boolean ���ز����� true����ʾɾ���ɹ�
	 */
	public Boolean delReplyInfoById(Integer rid) {
		int result = -1;
		String sql = "delete from replyInfo where rid = ?";
		try {
			result = dao.executeUpdate(sql, new Object[] { rid });
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
	 * ���������Ϣ
	 * 
	 * @param title
	 *            ����
//	 * @param contents
	 *            ����
	 * @param tid
	 *            �������
	 * @param sid
	 *            �����
	 * @param uid
	 *            �û����
	 * @return Boolean ���ز����� true����ʾ����ɹ�
	 */
	public Boolean insertReplyInfo(String title, String content, Integer tid,
			Integer sid, Integer uid) {
		int result = -1;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateString = formatter.format(date);
		System.out.println(dateString);
		String sql = "insert into replyInfo(rTid,rSid,rUid,rTopic,rContents,rPublishTime)"
				+ " values(?,?,?,?,?,?)";
		// ��\r\n�滻��<br>
		content = content.replace("<", "&lt;");
		content = content.replace(">", "&gt;");
		content = content.replace(" ", "&nbsp");
		content = content.replace("\r\n", "<br>");
		System.out.println("in dao  "+ content);
		try {
			result = dao.executeUpdate(sql, new Object[] { tid, sid, uid,
					title, content ,dateString});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	public ArrayList<ReplyInfo> getAllReplyInfo() {
		ArrayList<ReplyInfo> list = new ArrayList<>();
		String sql="select * from replyinfo";
		try {
			rs = dao.executeQuery(sql);
			while (rs != null && rs.next()) {
				ReplyInfo temp = new ReplyInfo();
				temp.setRid(rs.getInt("rid"));
				temp.setRtid(rs.getInt("rtid"));
				temp.setRsid(rs.getInt("rsid"));
				temp.setRuid(rs.getInt("ruid"));
				temp.setRtopic(rs.getString("rTopic"));
				temp.setRcontents(rs.getString("rContents"));
				temp.setRpublishtime(rs.getDate("rpublishtime"));
				temp.setRmodifytime(rs.getDate("rmodifytime"));
				list.add(temp);
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
	 * ���ݰ��id������idͳ�������Ļظ�����
	 * 
	 * @param tid
	 *            �������
	 * @param sid
	 *            �����
	 * @return Boolean ���ز����� true����ʾ����ɹ�
	 */
	public Boolean getReplyCountById(Integer tid, Integer sid) {
		int result = -1;
		String sql = "update topicInfo set tReplyCount = "
				+ " (select count(*) as replyCount from replyInfo where rTid = ? and rSid = ?) "
				+ " where tid = ?";
		try {
			result = dao.executeUpdate(sql, new Object[] { tid, sid, tid });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	// ����
	public static void main(String[] args) {
		ReplyInfoDAO dao = new ReplyInfoDAO();
		// Boolean x = dao.insertTopicInfo("d","d",11,1);
		// System.out.print(x);
	}
}
