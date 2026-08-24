package Dental.ICBT.Application.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static dbConnection Instance;
    private Connection connection;

    private dbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunriseDental", "root", "1234");
    }

    public Connection getConnection(){
        return connection;
    }

    public static dbConnection getInstance() throws SQLException {
        return Instance == null ? Instance = new dbConnection() : Instance;
    }
}
