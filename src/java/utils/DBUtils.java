package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class DBUtils {

    private static final String DB_NAME = "BookMNG";
    private static final String DB_USER_NAME = "";
    private static final String DB_PASSWORD = "";

    public static Connection createConnection() {

        Connection con = null;
        String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName =" + DB_NAME;
        try {
            //Connection to database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("Error connection at .utils createConnection()");
        }
        return con;

    }
}

