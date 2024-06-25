package entity.user;

import dao.UserDAO;
import dbconnection.IDBConnection;

import java.sql.Date;

public interface IUser{
    String getTckn();
    String getName();
    String getSurname();
    String getPassword();
    String getEmail();
    String getPhone();
    Date getBirthday();
    String getAddress();
    String getGender();
    boolean isActive();


}
