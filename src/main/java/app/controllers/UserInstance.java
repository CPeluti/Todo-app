package app.controllers;

import app.models.User;

public class UserInstance {

    private static UserInstance instance;

    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }





    protected UserInstance(){}

    public static UserInstance getInstance(){
        if(instance == null){
            instance = new UserInstance();
        }
        return instance;
    }

}
