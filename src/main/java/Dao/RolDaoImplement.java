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
 * Created by blackwidow on 13/12/16.
 */
public class RolDaoImplement implements RolDaoInterface {
    private DataBase ddbb;

    public void setDdbb(DataBase ddbb) {
        this.ddbb = ddbb;
    }

    public List<Rol> getRoles() throws SQLException {
        String sql = "SELECT * FROM rol";
        PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Rol> roles = new ArrayList<Rol>();
        while (rs.next()){
            Rol rol = new Rol();
            rol.setRol_name(rs.getString("rol_name"));
            rol.setRol_desc(rs.getString("rol_desc"));
            roles.add(rol);
        }
        return roles;
    }

    public void insertRol(String rol_name, String rol_desc) throws SQLException {
        String sql = "INSERT INTO rol values(?,?)";
        try {
            PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
            ps.setString(1,rol_name);
            ps.setString(2,rol_desc);
            ps.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteRol(String rol_name) throws SQLException {
        String sql = "DELETE FROM user_roles WHERE role_name = ?";
        PreparedStatement ps = ddbb.getCon().prepareStatement(sql);
        ps.setString(1,rol_name);
        ps.execute();
        sql = "DELETE FROM rol where rol_name = ?";
        ps = ddbb.getCon().prepareStatement(sql);
        ps.setString(1,rol_name);
        ps.execute();
    }
}
