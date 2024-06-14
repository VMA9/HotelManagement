package entity.user;

public abstract class User implements IUser{
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;

    public User(int id, String name, String password, String email, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public void createUser() {
        System.out.println("createUser() in User");
    }

    @Override
    public void listUser() {
        System.out.println("listUser() in User");
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}

