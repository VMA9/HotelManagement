package dao;

import dbconnection.IDBConnection;
import entity.room.IRoom;
import entity.room.KingRoom;
import entity.room.Room;
import entity.user.Guest;
import entity.user.IUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends DAO<IRoom> {
    public RoomDAO(IDBConnection idbConnection) {
        super(idbConnection);
    }

    @Override
    public void save(IRoom iRoom) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInsertQuery())) {
            mapToStatement(ps, iRoom);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("RoomDAO save failed");
            e.printStackTrace();
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(IRoom previous, IRoom next) throws SQLException {
        int id = getFindId(previous.getRoomName(), previous.getRoomNumber());
        if (id == -1) {
            System.out.println("Oda bulunamadı!");
            return;
        }

        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            mapToStatement(ps, next);
            ps.setInt(12, id);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("RoomDAO update failed");
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getId(IRoom room) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setString(1, room.getRoomName());
            ps.setInt(2, room.getRoomNumber());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        }
    }

    @Override
    public int getFindId(String roomName, int roomNumber) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setString(1, roomName);
            ps.setInt(2, roomNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        }
    }

    @Override
    public List<IRoom> getAll() throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectAllQuery());
             ResultSet rs = ps.executeQuery()) {
            List<IRoom> iRooms = new ArrayList<>();
            while (rs.next()) {
                iRooms.add(mapResultSetToEntity(rs));
            }
            return iRooms;
        }
    }

    @Override
    public void printGetAll() throws SQLException {
        List<IRoom> rooms = getAll();
        for (IRoom room : rooms) {
            System.out.println(room.toString());
        }
    }

    protected List<IRoom> getByRoomName(String roomName) throws SQLException {
        List<IRoom> rooms = new ArrayList<>();
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectByRoomNameQuery())) {
            ps.setString(1, roomName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rooms.add(mapResultSetToEntity(rs));
                }
            }
        }
        return rooms;
    }

    public void printUsersByRoomName(String roomName) throws SQLException {
        List<IRoom> byRoomName = getByRoomName(roomName);
        if (byRoomName.isEmpty()) {
            System.out.println("No users found with the given email");
        } else {
            byRoomName.forEach(u -> System.out.println("User found by room: " + u.getRoomName() + " " + u.getRoomNumber() + " " + u.getPrice() + " " + u.getRoomClass() + " " + u.getDescription()));
        }
    }
    @Override
    public void inactive(IRoom iRoom) throws SQLException {
        int id = getId(iRoom);

        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInactiveQuery())) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            idbConnection.disconnect();
        }
    }

    @Override
    public void reactive(IRoom iRoom) throws SQLException {
        int id = getId(iRoom);

        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getReactiveQuery())) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            idbConnection.disconnect();
        }
    }

    @Override
    protected void mapToStatement(PreparedStatement ps, IRoom iRoom) throws SQLException {
        ps.setString(1, iRoom.getRoomName());
        ps.setInt(2, iRoom.getRoomNumber());
        ps.setInt(3, iRoom.getCapacity());
        ps.setBigDecimal(4, iRoom.getPrice());
        ps.setString(5, iRoom.getRoomClass());
        ps.setString(6, iRoom.getDescription());
        ps.setBoolean(7, iRoom.getHasSeaView());
        ps.setBoolean(8, iRoom.getHasJacuzzi());
        ps.setBoolean(9, iRoom.getHasSafeBox());
        ps.setBoolean(10, iRoom.getHasWifi());
        ps.setBoolean(11, iRoom.isActive());
    }

    @Override
    protected IRoom mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new KingRoom(
                rs.getString("roomName"),
                rs.getInt("roomNumber"),
                rs.getInt("capacity"),
                rs.getBigDecimal("price"),
                rs.getString("roomClass"),
                rs.getString("description"),
                rs.getBoolean("hasSeaView"),
                rs.getBoolean("hasJacuzzi"),
                rs.getBoolean("hasSafeBox"),
                rs.getBoolean("hasWifi"),
                rs.getBoolean("isActive")
        );
    }

    protected String getIdQuery() {
        return """
                SELECT id FROM hotelmanagement.rooms
                WHERE roomName = ? AND roomNumber = ?;
                """;
    }

    @Override
    protected String getUpdateQuery() throws SQLException {
        return """
                UPDATE hotelmanagement.rooms
                SET
                roomName = ?,
                roomNumber = ?,
                capacity = ?,
                price = ?,
                roomClass = ?,
                description = ?,
                hasSeaView = ?,
                hasJacuzzi = ?,
                hasSafeBox = ?,
                hasWifi = ?,
                isActive = ?
                WHERE id = ? ;""";
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO hotelmanagement.rooms (roomName, roomNumber, capacity, price, roomClass, description, hasSeaView, hasJacuzzi, hasSafeBox, hasWifi, isActive)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";
    }

    @Override
    protected String getInactiveQuery() {
        return """
                UPDATE hotelmanagement.rooms
                SET isActive = false
                WHERE id = ?;""";
    }

    @Override
    protected String getReactiveQuery() {
        return """
                UPDATE hotelmanagement.rooms
                SET isActive = true
                WHERE id = ?;""";
    }

    @Override
    protected String getSelectAllQuery() {
        return """
                SELECT * FROM hotelmanagement.rooms
                WHERE isActive = true;""";
    }

    protected String getSelectByRoomNameQuery() {
        return """
                SELECT * FROM hotelmanagement.rooms
                WHERE roomName = ? AND isActive = true;""";
    }
}
