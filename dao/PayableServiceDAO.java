package dao;

import dbconnection.IDBConnection;
import entity.payableservice.IPayableService;
import entity.payableservice.SpaService;
import entity.user.IUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayableServiceDAO extends DAO<IPayableService> {
    public PayableServiceDAO(IDBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public void save(IPayableService iPayableService) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInsertQuery())) {
            mapToStatement(ps, iPayableService);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("PayableServiceDAO save failed");
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
    public void update(IPayableService previousT, IPayableService nextT) throws SQLException {
        int previousTId = getId(previousT);
        if(previousTId == -1){
            System.out.println("Servis bulunamadı!");
            return;
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            ps.setInt(1, nextT.getUserId());
            ps.setString(2, nextT.getPayableServiceName());
            ps.setBigDecimal(3, nextT.getPrice());
            ps.setString(4,nextT.getDescription());
            ps.setBoolean(5, nextT.isActive());
            ps.setInt(6, previousTId);

            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("PayableServiceDAO update failed");
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
    public List<IPayableService> getAll() throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectAllQuery());
             ResultSet rs = ps.executeQuery()) {
            List<IPayableService> iPayableServices = new ArrayList<>();
            while (rs.next()) {
                iPayableServices.add(mapResultSetToEntity(rs));
            }
            return iPayableServices;
        }
    }

    @Override
    public void inactive(IPayableService iPayableService) throws SQLException {
        int id = getId(iPayableService);

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
    public void reactive(IPayableService iPayableService) throws SQLException {
        int id = getId(iPayableService);

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
    public void printGetAll() throws SQLException {
        List<IPayableService> iPayableServices = getAll();
        for(IPayableService rezervable : iPayableServices){
            System.out.println(rezervable.toString());
        }

    }

    @Override
    public int getFindId(int userId, String serviceName) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setInt(1, userId);
            ps.setString(2, serviceName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
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
    public int getId(IPayableService iPayableService) throws SQLException {
        try(Connection connection = idbConnection.connect();
            PreparedStatement ps = connection.prepareStatement(getIdQuery())){
            ps.setInt(1,iPayableService.getUserId());
            ps.setString(2,iPayableService.getPayableServiceName());
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id");
                }else{
                    return -1;
                }
            }
        }
    }

    @Override
    protected void mapToStatement(PreparedStatement ps, IPayableService iPayableService) throws SQLException {
        ps.setInt(1,iPayableService.getUserId());
        ps.setString(2,iPayableService.getPayableServiceName());
        ps.setBigDecimal(3,iPayableService.getPrice());
        ps.setString(4,iPayableService.getDescription());
        ps.setBoolean(5,iPayableService.isActive());
    }

    @Override
    protected IPayableService mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new SpaService(
                rs.getInt("userId"),
                rs.getString("serviceName"),
                rs.getBigDecimal("servicePrice"),
                rs.getString("description"),
                rs.getBoolean("isActive")
        );
    }
    protected String getIdQuery() {
        return """
                SELECT id FROM hotelmanagement.payableservices
                WHERE userId = ? AND serviceName = ?""";
    }
    protected String getUserIdQuery() {
        return """
                SELECT id FROM hotelmanagement.users
                WHERE tckn = ? AND email = ?;""";
    }

    @Override
    protected String getUpdateQuery() throws SQLException {
        return """
                UPDATE hotelmanagement.payableservices
                SET
                userId = ?,
                serviceName = ?,
                servicePrice = ?,
                description = ?,
                isActive = ?
                WHERE id = ?;""";
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO hotelmanagement.payableservices(userId,serviceName,servicePrice,description,isActive)
                VALUES  (?, ?, ?, ?, ?);
                """;
    }

    @Override
    protected String getInactiveQuery() {
        return """
                UPDATE hotelmanagement.payableservices
                SET isActive = false
                WHERE id = ?;""";
    }

    @Override
    protected String getReactiveQuery() {
        return """
                UPDATE hotelmanagement.payableservices
                SET isActive = true
                WHERE id = ?;""";
    }

    @Override
    protected String getSelectAllQuery() {
        return """
                SELECT * FROM hotelmanagement.payableservices
                WHERE isActive = true;""";
    }
}
