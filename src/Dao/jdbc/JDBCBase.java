package Dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCBase {

    public void saveOrUpdateOrDelete(String sql, Object values[]) {
        PreparedStatement stmt = null;
        Connection conn = JDBCManager.getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    stmt.setObject(i + 1, values[i]);
                }
            }
            System.out.println(stmt.toString());
            stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCManager.close(null, stmt, conn);
        }
    }

}
