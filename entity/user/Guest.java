package entity.user;

public class Guest extends User {
    private int birthday;
    private String address;
    private String gender;

    public Guest(int id, String name, String password, String email, String phone, int birthday, String address, String gender) {
        super(id, name, password, email, phone);
        this.birthday = birthday;
        this.address = address;
        this.gender = gender;
    }

    @Override
    public void createUser() {
        System.out.println("createUser() in Guest");
    }

    @Override
    public void listUser() {
        System.out.println("listUser() in Guest");
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
