package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/java/carsharing/db/carsharing";
    static final String USER = "";
    static final String PASS = "";

    public Connection conn;

    public DBConnection() {
        conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public void exit() {
        try {
            conn.close();
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
