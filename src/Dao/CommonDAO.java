package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDAO {

	// �����û�id����û�����
	public static String getUserNameById(Integer uId) {
		BaseDAO dao = new BaseDAO();
		ResultSet rs = null;
		String sql = "select * from userInfo where uId = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { uId });
			if (rs != null && rs.next()) {
				return rs.getString("uName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return null;
	}

	// ��ʽ��ʱ��
	public static String getDateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (date != null) {
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * ��һ���ַ����滻����һ���ַ���
	 * 
	 * @param input
	 *            ԭ�ַ���
	 * @param needReplaceStr
	 *            ��Ҫ�滻�Ĳ���
	 * @param replaceStr
	 *            �滻�Ĳ���
	 * @return String �����滻��Ľ��
	 */
	public static String replace(String input, String needReplaceStr,
			String replaceStr) {

		if (input == null || input.length() == 0
				|| input.indexOf(needReplaceStr, 0) < 0) {
			return input;
		} else {
			int i = 0;
			int j = 0;
			int needReplaceLength = needReplaceStr.length();
			StringBuffer buffer = new StringBuffer();
			while ((i = input.indexOf(needReplaceStr, i)) >= 0) {
				buffer.append(input.substring(j, i)).append(replaceStr);
				j = (i += needReplaceLength);
			}
			input = buffer.append(input.substring(j)).toString();
			buffer = null;
			return input;
		}
	}

	// ����
	public static void main(String[] args) {

	}
}
