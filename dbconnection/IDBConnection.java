package dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnection{
    Connection connect() throws SQLException;
    void disconnect() throws SQLException;
}