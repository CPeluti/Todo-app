package app.models;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User{
    public String id;
    public String name;
    public String lastName;
    public String email;
    public String imageUrl;
    public String sessionToken;
    public ArrayList<Category> categories= new ArrayList<>();
    public ArrayList<Tasks> tasks;



    public User(String name, String lastName, String email, String imageUrl, String sessionToken, String id,ArrayList<Tasks> tasks,ArrayList<Category> categories) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.sessionToken = sessionToken;
        this.id = id;
        this.tasks = tasks;
        this.categories.add(new DefaultCategory("Tarefas importantes","M528.1 171.5L382 150.2 316.7 17.8c-11.7-23.6-45.6-23.9-57.4 0L194 150.2 47.9 171.5c-26.2 3.8-36.7 36.1-17.7 54.6l105.7 103-25 145.5c-4.5 26.3 23.2 46 46.4 33.7L288 439.6l130.7 68.7c23.2 12.2 50.9-7.4 46.4-33.7l-25-145.5 105.7-103c19-18.5 8.5-50.8-17.7-54.6zM388.6 312.3l23.7 138.4L288 385.4l-124.3 65.3 23.7-138.4-100.6-98 139-20.2 62.2-126 62.2 126 139 20.2-100.6 98z","Tarefas marcadas como importante",0));
        this.categories.add(new DefaultCategory("Tarefas do dia","M256 160c-52.9 0-96 43.1-96 96s43.1 96 96 96 96-43.1 96-96-43.1-96-96-96zm246.4 80.5l-94.7-47.3 33.5-100.4c4.5-13.6-8.4-26.5-21.9-21.9l-100.4 33.5-47.4-94.8c-6.4-12.8-24.6-12.8-31 0l-47.3 94.7L92.7 70.8c-13.6-4.5-26.5 8.4-21.9 21.9l33.5 100.4-94.7 47.4c-12.8 6.4-12.8 24.6 0 31l94.7 47.3-33.5 100.5c-4.5 13.6 8.4 26.5 21.9 21.9l100.4-33.5 47.3 94.7c6.4 12.8 24.6 12.8 31 0l47.3-94.7 100.4 33.5c13.6 4.5 26.5-8.4 21.9-21.9l-33.5-100.4 94.7-47.3c13-6.5 13-24.7.2-31.1zm-155.9 106c-49.9 49.9-131.1 49.9-181 0-49.9-49.9-49.9-131.1 0-181 49.9-49.9 131.1-49.9 181 0 49.9 49.9 49.9 131.1 0 181z","Tarefas do dia",1));
        this.categories.add(new DefaultCategory("Tarefas Concluidas","M504 256c0 136.967-111.033 248-248 248S8 392.967 8 256 119.033 8 256 8s248 111.033 248 248zM227.314 387.314l184-184c6.248-6.248 6.248-16.379 0-22.627l-22.627-22.627c-6.248-6.249-16.379-6.249-22.628 0L216 308.118l-70.059-70.059c-6.248-6.248-16.379-6.248-22.628 0l-22.627 22.627c-6.248 6.248-6.248 16.379 0 22.627l104 104c6.249 6.249 16.379 6.249 22.628.001z","Tarefas marcadas como concluidas",2));
        this.categories.add(new DefaultCategory("Tarefas Excluidas","M32 464a48 48 0 0 0 48 48h288a48 48 0 0 0 48-48V128H32zm272-256a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zm-96 0a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zm-96 0a16 16 0 0 1 32 0v224a16 16 0 0 1-32 0zM432 32H312l-9.4-18.7A24 24 0 0 0 281.1 0H166.8a23.72 23.72 0 0 0-21.4 13.3L136 32H16A16 16 0 0 0 0 48v32a16 16 0 0 0 16 16h416a16 16 0 0 0 16-16V48a16 16 0 0 0-16-16z","Tarefas excluidas",3));
        this.categories.addAll(categories);

    }


    public static void create(String email, String password, String name){

        String dataString =
                "{" +
                        "\"email\":\""+email+"\"," +
                        "\"passwd\":\""+password+"\"," +
                        "\"name\":\""+name+"\","+
                        "\"lastname\":\""+""+"\"," +
                        "\"nickname\":\""+""+"\"," +
                        "\"image\":\""+""+"\"" +

                "}";

        Unirest.post("https://api-todo-unb.herokuapp.com/users/create")
                .header("Content-Type","application/json")
                .body(dataString)
                .asJson();


    }

    public static void update(User user){
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization", "bearer " + user.getSessionToken());

        String dataString =
                "{" +
                        "\"id\":\""+user.getId()+"\","+
                        "\"name\":\""+user.getName()+"\","+
                        "\"lastname\":\""+user.getLastName()+"\"," +
                        "\"image\":\""+user.getImageUrl()+"\"" +
                        "}";

        Unirest.put("https://api-todo-unb.herokuapp.com/users/")
                .headers(header)
                .body(dataString)
                .asJson();
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
            }else if(json.has("status") && (json.getString("status").equals("404"))){
                System.out.println("nao foi possivel se conectar");
                return new User("","","","","","",new ArrayList<>(),new ArrayList<>());
            }

            ArrayList<Category> categoriesRes = new ArrayList<>();
            JSONArray jsonArray = json.getJSONArray("categories");
            System.out.println(jsonArray.toString());
            System.out.println(jsonArray.length());
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
            System.out.println(jsonArrayTasks.toString());
            if(!jsonArrayTasks.isEmpty()){
                for(int i = 0; i<jsonArrayTasks.length();i++){
                    JSONObject task = jsonArrayTasks.getJSONObject(i);
                    Tasks userTasks = new Tasks(
                            Integer.parseInt(task.getString("id")),
                            task.getString("title"),
                            Integer.parseInt(task.getString("category")),
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

    public static void exportToJson(User user,String url){
        try(Writer writer = new FileWriter(url+"\\backup.json")){
            Gson gson = new GsonBuilder().create();
            gson.toJson(user,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
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

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
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

    public void addCategory(Category category){
        this.categories.add(category);
    }
}