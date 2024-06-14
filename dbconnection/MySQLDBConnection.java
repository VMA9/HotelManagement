package dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDBConnection extends DBConnection {

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/hotelmanagement";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "password";

    public MySQLDBConnection() {
        super(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
    }
}
