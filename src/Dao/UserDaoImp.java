package Dao;


import Dao.jdbc.JDBCBase;
import Dao.jdbc.JDBCManager;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImp extends JDBCBase implements UserDao {


    @Override
    public User getUserByName(String name) {
//        Connection con = JDBCManager.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        User user = new User();
//        String sql = "SELECT * FROM userinfo WHERE uname ='" + name + "'";
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            if(rs.next()){
//                user.setUid(rs.getInt("uid"));
//                user.setUname(rs.getString("uname"));
//                user.setUpassword(rs.getString("uPassWord"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            JDBCManager.close(rs,ps,con);
//        }
//        if (user.getUsername() == null)//因为new了一个user所以要判断是不是null
//            return null;
//        return user;
        return null;
    }

    @Override
    public User getUserByid(String id) {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
