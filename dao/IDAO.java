package dao;

import dbconnection.IDBConnection;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> extends IDBConnection {
    void create(T entity) throws SQLException;
    List<T> getAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    T getById(int id) throws SQLException;

}
