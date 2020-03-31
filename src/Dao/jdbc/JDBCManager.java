package Dao.jdbc;

import java.sql.*;

public class JDBCManager {
    private static String url = "jdbc:mysql://localhost:3306/kechengsheji" ;
    private static String username = "root" ;
    private static String password = "root" ;

    static {
        try {
            // ����MySql��������
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("�Ҳ������������� ����������ʧ�ܣ�");
            e.printStackTrace();
        }
    }

    /**
     * �������ݿ����Ӷ���
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("���ݿ�����ʧ�ܣ�");
            se.printStackTrace();
        }
        return con;
    }

    /**
     * �ر����ݿ����Ӷ���
     */
    public static void close(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection con = JDBCManager.getConnection();
        System.out.println("��ȡ���ݿ����ӳɹ���");
        close(null, null, con);
        System.out.println("�ɹ��ر����ݿ����ӣ�");
    }
}
