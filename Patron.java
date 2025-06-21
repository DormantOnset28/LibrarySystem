//Class responsible for handling library user details everything final dont modify

public class Patron {
    private final String name;
    private final String id;
    private final String email;
    private final String phone;

    Patron(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}