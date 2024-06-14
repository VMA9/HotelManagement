package entity.user;

public class Admin extends User {
    public Admin(int id, String name, String password, String email, String phone) {
        super(id, name, password, email, phone);
    }
    public void manage() {

    }

    @Override
    public void createUser() {
        System.out.println("createUser() in Admin");

    }

    @Override
    public void listUser() {
        System.out.println("listUser() in Admin");
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
