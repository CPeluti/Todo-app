package app.models;

public abstract class Category{
    public String type;
    public String icon;
    public String description;
    private User user;


    public Category(String type, String icon, String description) {
    }
}
