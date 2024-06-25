package dao;

import entity.rezervable.IRezervable;
import entity.user.IUser;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T>{
    void save(T t) throws SQLException;
    default void update(T previous, T next) throws SQLException{

    }
    default void update(T previousT, IUser previous, T nextT) throws SQLException{

    }
    default void printUsersByEmail(String email) throws SQLException{

    }
    default void printUsersByTckn(String tckn) throws SQLException{

    }
    List<T> getAll() throws SQLException;
    void inactive(T t) throws SQLException;
    void reactive(T t) throws SQLException;
    void printGetAll() throws SQLException;

    default int getFindId(String tckn, String password) throws SQLException {
        return -1;
    }
    default int getFindId(String roomName, int roomNumber) throws SQLException {
        return -1;
    }
    default int getFindId(int userId, int roomId, Date startDate) throws SQLException {
        return -1;
    }
    default int getFindId(int userId, String serviceName) throws SQLException{
        return -1;
    }

    default String getCreateDate(T t) throws SQLException{
        return null;
    }
    int getId(T entity) throws SQLException;
    default IUser getByUser(String tckn) throws SQLException{
        return null;
    }
    default IRezervable getByRezervable(int userId) throws SQLException{
        return null;
    }
}
