package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by blackwidow on 30/11/16.
 */
public class DataBase {
    //private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://172.16.5.166/dwes";
    private String USER = "root";
    private String PSWD = "test";
    private Connection con = null;

    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        con = DriverManager.getConnection(DB_URL,USER,PSWD);
    }

    public Connection getCon() {
        return con;
    }

}
