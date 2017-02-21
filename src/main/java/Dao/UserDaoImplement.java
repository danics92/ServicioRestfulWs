package Dao;

import DataBase.DataBase;
import Pojo.Rol;
import Pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blackwidow on 30/11/16.
 */
public class UserDaoImplement implements UserDaoInterface {
    private DataBase ddbb = new DataBase();

    private List<User> usuarios = new ArrayList<User>();

    public UserDaoImplement() throws SQLException, ClassNotFoundException {
    }

    public List<User> getUsuarios() throws SQLException {
        String users = "SELECT user,host,password FROM user";
        PreparedStatement ps = ddbb.getCon().prepareStatement(users);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            User user = new User();
            user.setName(rs.getString("user"));
            user.setPassword(rs.getString("password"));
            usuarios.add(user);
        }
        return usuarios;
    }

    public User findUser(String userName) throws SQLException {
        String users = "SELECT user_name,user_pass FROM user where user_name= ?";
        PreparedStatement ps = ddbb.getCon().prepareStatement(users);
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            User user = new User();
            user.setName(rs.getString("user_name"));
            user.setPassword(rs.getString("user_pass"));
            return user;
        }
        return null;
    }

    public List<User> findUser(String username, boolean rool) throws SQLException {
        String sql = "SELECT u.user_name,u.user_pass ";
        sql += rool?",r.rol_name,r.rol_desc ":"";
        sql += "FROM user as u ";
        sql += rool?"INNER JOIN user_roles as ur ON u.user_name = ur.user_name INNER JOIN rol as r ON ur.role_name = r.rol_name ":"";
        sql += "WHERE u.user_name = ?";
        PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        List<User> usuarios = new ArrayList<User>();
        List<Rol> roles = new ArrayList<Rol>();
        User user = null;
        while (rs.next()){
            if(user == null){
                user = new User();
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_pass"));
            }
            if(rool) {
                Rol rol = new Rol();
                rol.setRol_name(rs.getString("rol_name"));
                rol.setRol_desc(rs.getString("rol_desc"));
                roles.add(rol);
            }
        }
        user.setRoles(roles);
        usuarios.add(user);
        return usuarios;
    }

    public void insertUser(String userName, String password, List<Rol> roles) throws SQLException {
        String sql = "INSERT INTO user VALUES(?,?)";
        PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,password);
        ps.execute();
        sql = "INSERT INTO user_roles VALUES(?,?)";
        for (int i = 0; i < roles.size(); i++) {
            ps = ddbb.getCon().prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,roles.get(i).getRol_name());
            ps.execute();
        }
    }

    public List<User> getUsers() throws SQLException {
        String sql = "SELECT u.user_name,u.user_pass,r.rol_name,r.rol_desc FROM user as u INNER JOIN user_roles as ur ON u.user_name = ur.user_name INNER JOIN rol as r ON ur.role_name = r.rol_name";
        PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<User> usuarios = new ArrayList<User>();
        List<Rol> roles = new ArrayList<Rol>();
        String usuario = "";
        User user = null;
        Rol rol = null;
        while (rs.next()) {
            if (usuario.equals("")) {
                user = new User();
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_pass"));
            } else if (!usuario.equals(rs.getString("user_name"))) {
                user.setRoles(roles);
                usuarios.add(user);
                user = (User) new User();
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_pass"));
                roles = new ArrayList<Rol>();
            }
                rol = new Rol();
                usuario = rs.getString("user_name");
                rol.setRol_name(rs.getString("rol_name"));
                rol.setRol_desc(rs.getString("rol_desc"));
                roles.add(rol);
        }
        user.setRoles(roles);
        usuarios.add(user);
        return usuarios;
    }

    public void deleteUser(String userName) throws SQLException {
        String sql = "DELETE FROM user_roles where user_name = ?";
        PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
        ps = ddbb.getCon().prepareStatement(sql);
        ps.setString(1,userName);
        ps.execute();
        sql = "DELETE FROM user WHERE user_name = ?";
        ps.setString(1,userName);
        ps.execute();

    }

    public void setDdbb(DataBase ddbb) {
        this.ddbb = ddbb;
    }
}
