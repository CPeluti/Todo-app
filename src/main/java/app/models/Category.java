package app.models;

import app.models.User;

public abstract class Category{
    private String type;
    private String icon;
    private String description;
    private User user;


    public Category(String type, String icon, String description) {
    }
}
