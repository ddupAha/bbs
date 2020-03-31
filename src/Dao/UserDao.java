package Dao;

import Entity.User;

public interface UserDao {

    User getUserByName(String name);
    User getUserByid(String id);
    void insert(User user);
    void update(User user);
    void delete(User user);
}
