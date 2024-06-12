package entity.user;

public class Admin extends User {
    public Admin(int id, String name, String password, String email, String phone) {
        super(id, name, password, email, phone);
    }
    public void manage() {

    }
}
