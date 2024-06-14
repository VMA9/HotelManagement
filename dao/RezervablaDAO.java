package dao;

import entity.rezervable.IRezervable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RezervablaDAO extends DAO<IRezervable>{
    public RezervablaDAO(Connection connection) {
        super(connection);
    }
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
    @Override
    public void create(IRezervable entity) {
        super.create(entity);
    }

    @Override
    public void update(IRezervable entity) {
        super.update(entity);
    }

    @Override
    public void delete(IRezervable entity) {
        super.delete(entity);
    }

    @Override
    public IRezervable getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<IRezervable> getAll() {
        return super.getAll();
    }
}
