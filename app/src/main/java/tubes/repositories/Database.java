package tubes.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static final String DATABASE = "tubes";
    public static final String PORT = "5432";
    public static final String HOST = "localhost";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
    private static Connection connection = null;

    public static Connection connect() {

        if(connection != null){
            return connection;
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return connection;
    }
}
