package dao;

import entity.room.IRoom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomDAO extends DAO<IRoom> {
    public RoomDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public void create(IRoom entity) {
        super.create(entity);
    }

    @Override
    public void update(IRoom entity) {
        super.update(entity);
    }

    @Override
    public void delete(IRoom entity) {
        super.delete(entity);
    }

    @Override
    public IRoom getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<IRoom> getAll() {
        return super.getAll();
    }
    //    @Override
//    protected String getTableName() {
//        return "rooms";
//    }

//    @Override
//    protected IRoom mapResultSetToEntity(ResultSet rs) throws SQLException {
//        String roomName = rs.getString("room_name");
//        int roomNumber = rs.getInt("room_number");
//        int roomCapacity = rs.getInt("room_capacity");
//        double roomPrice = rs.getDouble("room_price");
//        return new IRoom(
//                rs.getString(roomName),
//                rs.getInt(roomNumber),
//                rs.getInt(roomCapacity),
//                rs.getDouble(roomPrice),
//                rs.getDouble());
//        return null;
//    }
}
