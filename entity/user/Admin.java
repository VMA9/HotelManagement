package entity.user;

import java.sql.Date;

public class Admin extends User {
    public Admin(String tckn, String name, String surname, String password, String email, String phone, Date birthday, String address, String gender, boolean isActive) {
        super(tckn, name, surname, password, email, null, null, null, gender, isActive);
    }

    public void manage() {

    }

    @Override
    public String getTckn() {
        return super.getTckn();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public Date getBirthday() {
        return super.getBirthday();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Admin{");
        sb.append(", tckn='").append(tckn).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
