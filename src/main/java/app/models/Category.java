package app.models;

public abstract class Category {
    private String type;
    private String icon;
    private String description;
    private int id;


    public Category(String type, String icon, String description, int id) {
        this.type = type;
        this.icon = icon;
        this.description = description;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
