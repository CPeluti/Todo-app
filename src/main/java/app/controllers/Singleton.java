package app.controllers;

import app.models.Tasks;
import app.models.User;
import app.models.UserCategory;

import java.util.ArrayList;

public class Singleton {

    private static Singleton instance;

    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }





    protected Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

}
