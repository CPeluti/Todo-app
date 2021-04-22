package app.controllers;

import app.models.Tasks;
import app.models.User;
import app.models.UserCategory;

import java.util.ArrayList;

public class Singleton {

    private static Singleton instance;

    private User user;

    private ArrayList<UserCategory> listUserCategories = new ArrayList<UserCategory>();

    private ArrayList<Tasks> listTasks = new ArrayList<>();

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<UserCategory> getListUserCategories() {
        return this.listUserCategories;
    }
    public void setListUserCategories(ArrayList<UserCategory> listUserCategories) {
        this.listUserCategories = listUserCategories;
    }
    public void addToCategoryArray(UserCategory element){
        this.listUserCategories.add(element);
    }


    public ArrayList<Tasks> getListTasks() {
        return listTasks;
    }
    public void setListTasks(ArrayList<Tasks> listTasks) {
        this.listTasks = listTasks;
    }
    public void addToTaskArray(Tasks element){
        this.listTasks.add(element);
    }



    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
