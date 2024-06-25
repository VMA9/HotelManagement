package applicationservice;

import entity.user.IUser;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public interface IApplicationService<T>{
    void add(T t);
    void update(T previous, T next);
    void delete(T t);
    void active(T t);
    void printGetAll();
    int getById(IUser t) throws SQLException;
    BigDecimal getPrice();
    Date createDateParam(String date);
    IUser getByUser(String tckn) throws SQLException;
    void login(String tckn, String password) throws IOException, SQLException;
    IUser getCurrentUser();



}
