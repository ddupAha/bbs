package Dao;

import java.sql.*;

public class BaseDAO {
	public BaseDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ???? --------------------------
	private Connection conn;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;

	// ???????--------------------------
	public Connection getConn() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/kechengsheji",
						"root", "root");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getConnection????????");
		}
		return conn;
	}

	// ????????????SQL???--------------------
	public int executeUpdate(String sql) throws SQLException {
		if (getConn() == null) {
			System.out.println("?????????????!");
			return -1;
		}
		stm = conn.createStatement();
		return stm.executeUpdate(sql);
	}

	// ????????????????SQL???--------------------
	public int executeUpdate(String sql, Object[] obj) throws SQLException {
		if (getConn() == null) {
			System.out.println("?????????????!");
			return -1;
		}
		pstm = conn.prepareStatement(sql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				pstm.setObject(i + 1, obj[i]);
			}
		}
		return pstm.executeUpdate();
	}

	// ??§Ó??SQL???----------------------------
	public ResultSet executeQuery(String sql) throws SQLException {
		if (getConn() == null) {
			System.out.println("?????????????!");
			return null;
		}
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		return rs;
	}

	// ??????§Ó??SQL???----------------------------
	public ResultSet executeQuery(String sql, Object[] obj) throws SQLException {
		if (getConn() == null) {
			System.out.println("?????????????!");
			return null;
		}
		pstm = conn.prepareStatement(sql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				pstm.setObject(i + 1, obj[i]);
			}
		}
		rs = pstm.executeQuery();
		return rs;
	}

	// ???ResultSet
	public void closeResultSet() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ???Connection
	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ???Statement??PreparedStatement
	public void closeStatement() {
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
