package app.models;


import app.controllers.Singleton;
import javafx.concurrent.Task;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.net.URI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User{
    String id;
    String name;
    String lastName;
    String email;
    String timeZone;
    String imageUrl;
    String sessionToken;
    ArrayList<UserCategory> categories= new ArrayList<>();
    ArrayList<Tasks> tasks = new ArrayList<>();



    public User(String name, String lastName, String email,String timeZone, String imageUrl, String sessionToken, String id,ArrayList<Tasks> tasks,ArrayList<UserCategory> categories) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.sessionToken = sessionToken;
        this.timeZone = timeZone;
        this.id = id;
        this.tasks = tasks;
        this.categories = categories;
    }


    public static void Create(String email, String password, String name){

        //String url = "" + user.id;
        String dataString =
                "{" +
                        "\"email\":\""+email+"\"," +
                        "\"passwd\":\""+password+"\"," +
                        "\"name\":\""+name+"\","+
                        "\"lastname\":\""+""+"\"," +
                        "\"nickname\":\""+""+"\"," +
                        "\"timezone\":\""+""+"\"," +
                        "\"image\":\""+""+"\"" +

                "}";

        HttpResponse<JsonNode> data = Unirest.post("https://api-todo-unb.herokuapp.com/users/create")
                .header("Content-Type","application/json")
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();
        System.out.println(json);

    }

    public static User validateLogin(String name, String password){
        try {
            String resString = "{\"email\":\""+name+"\",\"passwd\":\""+password+"\"}";
            System.out.println(resString);
            HttpResponse<JsonNode> res = Unirest.post("https://api-todo-unb.herokuapp.com/login")
                    .header("Content-Type","application/json")
                    .body(resString)
                    .asJson();
            JSONObject json = res.getBody().getObject();
            System.out.println(json);

            if(json.has("status") && (json.getString("status").equals("500")||json.getString("status").equals("403"))){
                System.out.println("usuario ou senha incorretos");
                return null;
            }

            ArrayList<UserCategory> categoriesRes = new ArrayList<>();
            JSONArray jsonArray = json.getJSONArray("categories");
            System.out.println(jsonArray.toString());
            if (!jsonArray.isEmpty()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject category = jsonArray.getJSONObject(i);
                    UserCategory userCategory = new UserCategory(
                            category.getString("type"),
                            category.getString("icon"),
                            category.getString("description"),
                            Integer.parseInt(category.getString("id"))
                    );
                    categoriesRes.add(userCategory);
                }
                System.out.println(categoriesRes.size());
            }


            ArrayList<Tasks> tasks = new ArrayList<>();
            JSONArray jsonArrayTasks = json.getJSONArray("tasks");

            if(!jsonArrayTasks.isEmpty()){
                for(int i = 0; i<jsonArray.length();i++){
                    JSONObject task = jsonArrayTasks.getJSONObject(i);
                    Tasks userTasks = new Tasks(
                            Integer.parseInt(task.getString("id")),
                            task.getString("title"),
                            task.getString("category"),
                            Boolean.parseBoolean(task.getString("done")),
                            Boolean.parseBoolean(task.getString("favourite")),
                            task.getString("deadline"),
                            task.getString("description"),
                            Boolean.parseBoolean(task.getString("deleted"))
                    );
                    tasks.add(userTasks);
                }
            }


            return new User(
                    json.getString("name"),
                    json.getString("lastname"),
                    json.getString("email"),
                    json.getString("timezone"),
                    json.getString("image"),
                    json.getString("jwt"),
                    json.getString("userId"),
                    tasks,
                    categoriesRes
            );

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void changeImage(URI newImage){

    }
    public void changePassword(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<UserCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<UserCategory> categories) {
        this.categories = categories;
    }

    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(Tasks task){
        this.tasks.add(task);
    }
    public void addCategory(UserCategory category){
        this.categories.add(category);
    }
}