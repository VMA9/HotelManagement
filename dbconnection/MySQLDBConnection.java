package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBConnection extends DBConnection {
    protected static final String DBNAME = "hotelmanagement";
    protected static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    protected static final String USER = "root";
    protected static final String PASSWORD = "password";

    public MySQLDBConnection() {
        super(URL, USER, PASSWORD);
    }
    @Override
    public Connection connect() throws SQLException {
        if(connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            }catch(SQLException e) {
                System.out.println("Connection error " + e.getMessage());
            }
        }
        return connection;
    }
    @Override
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            try{
                connection.close();
            }catch (SQLException e) {
                System.out.println("Connection error " + e.getMessage());
                throw e;
            }
        }
    }

}
