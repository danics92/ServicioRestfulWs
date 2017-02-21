package Dao;

import Pojo.Rol;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by blackwidow on 13/12/16.
 */
public interface RolDaoInterface {
    List<Rol> getRoles() throws SQLException;
    void insertRol(String rol_name,String rol_desc) throws SQLException;
    void deleteRol(String rol_name) throws SQLException;
}
