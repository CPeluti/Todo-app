package app.controllers;

import app.models.Tasks;
import app.models.User;
import app.models.UserCategory;

import java.util.ArrayList;

public class Singleton {

    private static Singleton instance;

    private User user;

    protected ArrayList<UserCategory> listUserCategories = new ArrayList<UserCategory>();

    protected ArrayList<Tasks> listTasks = new ArrayList<>();
    private Tasks tasks;

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

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }
}
