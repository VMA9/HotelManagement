package dao;

import dbconnection.IDBConnection;
import entity.invoice.EFatura;
import entity.invoice.IInvoice;
import entity.user.IUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO extends DAO<IInvoice> {
    public InvoiceDAO(IDBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public void save(IInvoice iInvoice) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInsertQuery())) {
            mapToStatement(ps, iInvoice);
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
    public void update(IInvoice previousT, IInvoice nextT) throws SQLException {
        int previousTId = getId(previousT);
        if (previousTId == -1) {
            System.out.println("Servis bulunamadı!");
            return;
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            ps.setString(1, nextT.getInvoiceNumber());
            ps.setInt(2, nextT.getUserId());
            ps.setBigDecimal(3, nextT.getTotalAmount());
            ps.setDate(4, nextT.getInvoiceDate());
            ps.setBoolean(5, nextT.isActive());
            ps.setInt(6, previousTId);

            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("InvoiceDAO update failed");
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
    public List<IInvoice> getAll() throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectAllQuery());
             ResultSet rs = ps.executeQuery()) {
            List<IInvoice> invoices = new ArrayList<>();
            while (rs.next()) {
                invoices.add(mapResultSetToEntity(rs));
            }
            return invoices;
        }
    }
    @Override
    public void printGetAll() throws SQLException {
        List<IInvoice> iInvoices = getAll();
        for (IInvoice invoice : iInvoices) {
            System.out.println(invoice.toString() + ", Created Date=" + invoice.getInvoiceDate());
        }

    }

    @Override
    public void inactive(IInvoice iInvoice) throws SQLException {
        int id = getId(iInvoice);
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
    public void reactive(IInvoice iInvoice) throws SQLException {
        int id = getId(iInvoice);

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
    public int getFindId(int userId, String invoiceName) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setInt(1, userId);
            ps.setString(2, invoiceName);
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
    public int getId(IInvoice iInvoice) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setInt(1, iInvoice.getUserId());
            ps.setString(2, iInvoice.getInvoiceNumber());
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
    protected void mapToStatement(PreparedStatement ps, IInvoice iInvoice) throws SQLException {
        ps.setString(1, iInvoice.getInvoiceNumber());
        ps.setInt(2, iInvoice.getUserId());
        ps.setBigDecimal(3, iInvoice.getTotalAmount());
        ps.setDate(4, iInvoice.getInvoiceDate());
        ps.setBoolean(5, iInvoice.isActive());

    }

    @Override
    protected IInvoice mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new EFatura(
                rs.getString("invoiceNumber"),
                rs.getInt("userId"),
                rs.getBigDecimal("totalAmount"),
                rs.getDate("invoiceDate"),
                rs.getBoolean("isActive")
        );
    }

    protected String getIdQuery() {
        return """
                SELECT id FROM hotelmanagement.invoices
                WHERE userId = ? AND invoiceNumber = ?;""";
    }

    protected String getUserIdQuery() {
        return """
                SELECT id FROM hotelmanagement.users
                WHERE tckn = ? AND email = ?;""";
    }

    @Override
    protected String getUpdateQuery() throws SQLException {
        return """
                UPDATE hotelmanagement.invoices
                SET
                invoiceNumber = ?,
                userId = ?,
                totalAmount = ?,
                invoiceDate = ?,
                isActive = ?
                WHERE id = ?;""";
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO hotelmanagement.invoices (invoiceNumber, userId, totalAmount, invoiceDate, isActive)
                VALUES (?, ?, ?, ?, ?);""";
    }

    @Override
    protected String getInactiveQuery() {
        return """
                UPDATE hotelmanagement.invoices
                SET isActive = false
                WHERE id = ?;""";
    }

    @Override
    protected String getReactiveQuery() {
        return """
                UPDATE hotelmanagement.invoices
                SET isActive = true
                WHERE id = ?;""";
    }

    @Override
    protected String getSelectAllQuery() {
        return """
                SELECT * FROM hotelmanagement.invoices
                WHERE isActive = true;""";
    }
}
