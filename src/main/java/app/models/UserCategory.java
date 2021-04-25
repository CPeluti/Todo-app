package app.models;

import app.controllers.Singleton;
import app.models.Category;
import app.models.User;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;

public class UserCategory extends Category {

    public UserCategory() {
        super();
    }

    public UserCategory(String type, String icon, String description, int id) {
        super(type, icon, description, id);
    }

    public String getIcon(){
        return this.icon;
    }


    public static void create(UserCategory obj, Singleton singleton) {

        User user = singleton.getUser();
        String url = "https://api-todo-unb.herokuapp.com/category/" + user.id;
        String dataString = "{\"type\":\""+obj.type+"\",\"icon\":\""+obj.icon+"\",\"description\":\""+obj.description+"\"}";

        HttpResponse<JsonNode> data = Unirest.post(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();

    }


    public void delete(int id, Singleton singleton) {

        User user = singleton.getUser();
        String url = "https://api-todo-unb.herokuapp.com/category/" + user.id;

        String dataString = "{\"categoryId\":\""+id+"\"}";

        HttpResponse<JsonNode> data = Unirest.delete(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();
    }


    public void update(UserCategory obj, Singleton singleton) {
        User user = singleton.getUser();
        String url = "https://api-todo-unb.herokuapp.com/category/" + user.id;
        String dataString = "{\"type\":\""+obj.type+"\",\"icon\":\""+obj.icon+"\",\"description\":\""+obj.description+"\"}";

        HttpResponse<JsonNode> data = Unirest.put(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();
    }
}