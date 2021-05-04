package app.models;

import app.controllers.UserInstance;
import app.models.Category;
import app.models.User;

public class DefaultCategory extends Category {

    public DefaultCategory(String type, String icon, String description, int id) {
        super(type, icon, description, id);
    }



    @Override
    public void create(UserInstance userInstance) {

    }

    @Override
    public void delete(UserInstance userInstance) {

    }

    @Override
    public void update(UserInstance userInstance) {

    }
}