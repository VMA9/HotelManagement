package dao;

import entity.room.IRoom;
import entity.user.IUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends DAO<IUser> {
    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

}
