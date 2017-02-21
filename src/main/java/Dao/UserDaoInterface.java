package Dao;

import Pojo.Rol;
import Pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoInterface {
    User findUser(String userName) throws SQLException;
    List<User> findUser(String username,boolean rool) throws SQLException;
    void insertUser(String userName,String password,List<Rol> roles) throws SQLException;
    List<User> getUsers() throws SQLException;
    void deleteUser(String userName) throws SQLException;
}
