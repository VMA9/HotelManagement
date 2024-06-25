package entity.user;

import java.sql.Date;

public abstract class User implements IUser {
    protected String tckn;
    protected String name;
    protected String surname;
    protected String password;
    protected String email;
    protected String phone;
    protected Date birthday;
    protected String address;
    protected String gender;
    protected boolean isActive;

    public User(String tckn, String name, String surname, String password, String email, String phone, Date birthday, String address, String gender, boolean isActive) {
        this.tckn = tckn;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
        this.isActive = isActive;
    }

    @Override
    public String getTckn() {
        return tckn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
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

