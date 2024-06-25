package dao;

import dbconnection.IDBConnection;
import entity.rezervable.IRezervable;
import entity.rezervable.RoomRezervation;
import entity.user.IUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervableDAO extends DAO<IRezervable> {
    public RezervableDAO(IDBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public void save(IRezervable iRezervable) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInsertQuery())) {
            mapToStatement(ps, iRezervable);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("RezervationDAO save failed");
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
    public void update(IRezervable previousRezervation, IRezervable nextRezervation) throws SQLException {
        int previousRezervationId = getId(previousRezervation);
        if (previousRezervationId == -1) {
            System.out.println("Rezervasyon bulunamadı!");
            return;
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            ps.setInt(1, previousRezervation.getUserId());
            ps.setInt(2, nextRezervation.getRoomId());
            ps.setDate(3, nextRezervation.getStartDate());
            ps.setDate(4, nextRezervation.getEndDate());
            ps.setBoolean(5, nextRezervation.isActive());
            ps.setInt(6, previousRezervationId);

            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("RezervableDAO update failed");
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
    public List<IRezervable> getAll() throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectAllQuery());
             ResultSet rs = ps.executeQuery()) {
            List<IRezervable> iRezervables = new ArrayList<>();
            while (rs.next()) {
                iRezervables.add(mapResultSetToEntity(rs));
            }
            return iRezervables;
        }
    }
    @Override
    public void printGetAll() throws SQLException {
        List<IRezervable> iRezervables = getAll();
        for (IRezervable rezervation : iRezervables) {
            System.out.println(rezervation.toString() + ", Reservation Created Date=" + getCreateDate(rezervation));
        }
    }

    @Override
    public int getFindId(int userId, int roomId, Date startDate) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setInt(1, userId);
            ps.setInt(2, roomId);
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
    public void inactive(IRezervable iRezervable) throws SQLException {
        int id = getId(iRezervable);

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
    public void reactive(IRezervable iRezervable) throws SQLException {
        int id = getId(iRezervable);

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
    public String getCreateDate(IRezervable iRezervable) throws SQLException {
        int rezervationId = getFindId(iRezervable.getUserId(), iRezervable.getRoomId(), iRezervable.getStartDate());
        if (rezervationId == -1) {
            return "-1";
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getCreateDateQuery())) {
            ps.setInt(1, rezervationId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("created_date");
                } else {
                    return "-1";
                }
            }
        }
    }
    public int getUserId(IUser iUser) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getUserIdQuery())) {
            ps.setString(1, iUser.getTckn());
            ps.setString(2, iUser.getEmail());
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
    public int getId(IRezervable rezervable) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setInt(1, rezervable.getUserId());
            ps.setInt(2, rezervable.getRoomId());
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
    protected void mapToStatement(PreparedStatement ps, IRezervable iRezervable) throws SQLException {
        ps.setInt(1, iRezervable.getUserId());
        ps.setInt(2, iRezervable.getRoomId());
        ps.setDate(3, iRezervable.getStartDate());
        ps.setDate(4, iRezervable.getEndDate());
        ps.setBoolean(5, iRezervable.isActive());
    }

    @Override
    protected IRezervable mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new RoomRezervation(
                rs.getInt("userId"),
                rs.getInt("roomId"),
                rs.getDate("startDate"),
                rs.getDate("endDate"),
                rs.getBoolean("isActive")
        );
    }

    protected String getIdQuery() {
        return """
                SELECT id FROM hotelmanagement.rezervations
                WHERE userId = ? AND roomId = ?;""";
    }
    protected String getUserIdQuery() {
        return """
                SELECT id FROM hotelmanagement.users
                WHERE tckn = ? AND email = ?;""";
    }

    protected String getCreateDateQuery() {
        return """
                SELECT created_date FROM hotelmanagement.rezervations
                WHERE id = ?;
                """;
    }

    @Override
    protected String getUpdateQuery() throws SQLException {
        return """
                UPDATE hotelmanagement.rezervations
                SET
                userId = ?,
                roomId = ?,
                startDate = ?,
                endDate = ?,
                isActive = ?
                WHERE id = ?;""";
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO hotelmanagement.rezervations (userId, roomId, startDate, endDate, isActive)
                VALUES (?, ?, ?, ?, ?);""";
    }

    @Override
    protected String getInactiveQuery() {
        return """
                UPDATE hotelmanagement.rezervations
                SET isActive = false
                WHERE id = ?;""";
    }

    @Override
    protected String getReactiveQuery() {
        return """
                UPDATE hotelmanagement.rezervations
                SET isActive = true
                WHERE id = ?;""";
    }

    @Override
    protected String getSelectAllQuery() {
        return """
                SELECT * FROM hotelmanagement.rezervations
                WHERE isActive = true;""";
    }

}
