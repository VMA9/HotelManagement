package dao;

import dbconnection.IDBConnection;
import dbconnection.MySQLDBConnection;
import entity.user.IUser;

import java.sql.*;

public abstract class DAO<T> implements IDAO<T> {
    protected IDBConnection idbConnection;

    public DAO(IDBConnection dbConnection) {
        this.idbConnection = dbConnection;
    }

    protected abstract void mapToStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    protected abstract String getUpdateQuery() throws SQLException;

    protected abstract String getInsertQuery();

    protected abstract String getInactiveQuery();

    protected abstract String getReactiveQuery();

    protected abstract String getSelectAllQuery();
}