package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> implements IDAO<T> {
    Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public T getById(int id) {

        return null;
    }

    @Override
    public List<T> getAll() {

        return new ArrayList<>();
    }

//    protected abstract String getTableName();

//    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
}