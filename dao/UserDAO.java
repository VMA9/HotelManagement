package dao;

import dbconnection.IDBConnection;
import entity.user.Guest;
import entity.user.IUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<IUser> {
    public UserDAO(IDBConnection idbConnection) {
        super(idbConnection);
    }

    @Override
    public void save(IUser iUser) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInsertQuery())) {
            mapToStatement(ps, iUser);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("UserDAO save failed");
            e.printStackTrace();
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void update(IUser previous, IUser next) throws SQLException {
        int id = getFindId(previous.getTckn(), previous.getEmail());
        if (id == -1) {
            System.out.println("Kullanıcı bulunamadı!");
            return;
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            mapToStatement(ps, next);
            ps.setInt(11, id);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            System.out.println("UserDAO update failed");
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public IUser getByUser(String tckn) throws SQLException {
        IUser iUser = getByTckn2(tckn);
//        int userId = getFindId(iUser.getTckn(), iUser.getPassword());
        if (iUser == null) {
            return null;
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getByUserQuery())) {
            ps.setString(1, iUser.getTckn());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    iUser = (IUser) rs;
                }
                return iUser;

            }
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public int getId(IUser iUser) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setString(1, iUser.getTckn());
            ps.setString(2, iUser.getPassword());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getFindId(String tckn, String password) throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getIdQuery())) {
            ps.setString(1, tckn);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String getCreateDate(IUser iUser) throws SQLException {
        int userId = getFindId(iUser.getTckn(), iUser.getEmail());
        if (userId == -1) {
            return "-1";
        }
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getCreateDateQuery())) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("created_date");
                } else {
                    return "-1";
                }
            }
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public List<IUser> getAll() throws SQLException {
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectAllQuery());
             ResultSet rs = ps.executeQuery()) {
            List<IUser> iUsers = new ArrayList<>();
            while (rs.next()) {
                iUsers.add(mapResultSetToEntity(rs));
            }
            return iUsers;
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void printGetAll() throws SQLException {
        List<IUser> users = getAll();
        for (IUser user : users) {
            System.out.println(user.toString() + ", Created Date" + getCreateDate(user));
        }
    }

    public List<IUser> getByTckn(String tckn) throws SQLException {
        List<IUser> users = new ArrayList<>();
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectByTcknQuery())) {
            ps.setString(1, tckn);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToEntity(rs));
                }
            }
        } finally {
            try {
                idbConnection.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return users;
    }

    public IUser getByTckn2(String tckn) throws SQLException {
        IUser iUser = null;
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectByTcknQuery())) {
            ps.setString(1, tckn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    iUser = new Guest(
                            rs.getString("tckn"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getDate("birthday"),
                            rs.getString("address"),
                            rs.getString("gender"),
                            rs.getBoolean("isActive")
                    );
                    return iUser;
                }
            } finally {
                try {
                    idbConnection.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return iUser;
    }

    protected List<IUser> getByEmail(String email) throws SQLException {
        List<IUser> users = new ArrayList<>();
        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getSelectByEmailQuery())) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToEntity(rs));
                }
            }
        }
        return users;
    }

    @Override
    public void printUsersByEmail(String email) throws SQLException {
        List<IUser> usersByEmail = getByEmail(email);
        if (usersByEmail.isEmpty()) {
            System.out.println("No users found with the given email");
        } else {
            usersByEmail.forEach(u -> System.out.println("User found by email: " + u.getTckn() + " " + u.getName() + " " + u.getSurname() + " " + u.getEmail()));
        }
    }

    @Override
    public void printUsersByTckn(String tckn) throws SQLException {
        List<IUser> usersByTckn = getByTckn(tckn);
        if (usersByTckn.isEmpty()) {
            System.out.println("No users found with the given TCKN");
        } else {
            usersByTckn.forEach(u -> System.out.println("User found by TCKN: " + u.getTckn() + " " + u.getName() + " " + u.getSurname()));
        }
    }

    @Override
    public void inactive(IUser iUser) throws SQLException {
        int id = getId(iUser);

        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getInactiveQuery())) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            idbConnection.disconnect();
        }
    }

    @Override
    public void reactive(IUser iUser) throws SQLException {
        int id = getId(iUser);

        try (Connection connection = idbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(getReactiveQuery())) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            System.out.println(count + " rows affected");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            idbConnection.disconnect();
        }
    }

    @Override
    protected void mapToStatement(PreparedStatement ps, IUser iUser) throws SQLException {
        ps.setString(1, iUser.getTckn());
        ps.setString(2, iUser.getName());
        ps.setString(3, iUser.getSurname());
        ps.setString(4, iUser.getPassword());
        ps.setString(5, iUser.getEmail());
        ps.setString(6, iUser.getPhone());
        ps.setDate(7, iUser.getBirthday());
        ps.setString(8, iUser.getAddress());
        ps.setString(9, iUser.getGender());
        ps.setBoolean(10, iUser.isActive());
    }

    @Override
    protected IUser mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Guest(
                rs.getString("tckn"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getDate("birthday"),
                rs.getString("address"),
                rs.getString("gender"),
                rs.getBoolean("isActive")
        );
    }

    protected String getIdQuery() {
        return """
                SELECT id FROM hotelmanagement.users
                WHERE tckn = ? AND password = ?;
                """;
    }

    protected String getByUserQuery() {
        return """
                SELECT * FROM hotelmanagement.users
                WHERE id = ?;
                """;
    }

    protected String getCreateDateQuery() {
        return """
                SELECT created_date FROM hotelmanagement.users
                WHERE id = ?;
                """;
    }

    @Override
    protected String getUpdateQuery() throws SQLException {
        return """
                UPDATE hotelmanagement.users
                SET
                tckn = ?,
                name = ?,
                surname = ?,
                password = ?,
                email = ?,
                phone = ?,
                birthday = ?,
                address = ?,
                gender = ?,
                isActive = ?
                WHERE id = ? ;""";
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO hotelmanagement.users (tckn, name, surname, password, email, phone, birthday, address, gender, isActive )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";
    }

    @Override
    protected String getInactiveQuery() {
        return """
                UPDATE hotelmanagement.users
                SET isActive = false
                WHERE id = ?;""";
    }

    @Override
    protected String getReactiveQuery() {
        return """
                UPDATE hotelmanagement.users
                SET isActive = true
                WHERE id = ?;""";
    }

    @Override
    protected String getSelectAllQuery() {
        return """
                SELECT * FROM hotelmanagement.users
                WHERE isActive = true;""";
    }

    protected String getSelectByTcknQuery() {
        return """
                SELECT * FROM hotelmanagement.users
                WHERE tckn = ? AND isActive = true;""";
    }

    protected String getSelectByEmailQuery() {
        return """
                SELECT * FROM hotelmanagement.users
                WHERE email = ? AND isActive = true;""";
    }
}
