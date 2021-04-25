package app.models;

public abstract class Category {
    public String type;
    public String icon;
    public String description;
    public int id;
    public User user;


    public Category(String type, String icon, String description, int id) {
    }

    public Category() {

    }
}
