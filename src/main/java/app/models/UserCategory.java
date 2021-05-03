package app.models;

import app.controllers.Singleton;
import app.interfaces.Storable;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserCategory extends Category implements Storable {

    public UserCategory(String type, String icon, String description, int id) {
        super(type, icon, description, id);
    }




    public static void create(UserCategory obj, Singleton singleton) {
        User user = singleton.getUser();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization", "bearer " + user.getSessionToken());

        String dataString =
                "{" +
                    "\"id\":\""+obj.getId()+"\"," +
                    "\"type\":\""+obj.getType()+"\"," +
                    "\"icon\":\""+obj.getIcon()+"\"," +
                    "\"description\":\""+obj.getDescription()+"\"" +
                "}";


        Unirest.post("https://api-todo-unb.herokuapp.com/category/{userId}")
                .routeParam("userId",user.getId())
                .headers(header)
                .body(dataString)
                .asJson();

    }

    public static void delete(int id, Singleton singleton) {

        User user = singleton.getUser();
        String url = "https://api-todo-unb.herokuapp.com/category/" + user.getId();

        String dataString = "{\"categoryId\":\""+id+"\"}";

        Unirest.delete(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
    }

    public static void update(Category obj, Singleton singleton) {
        User user = singleton.getUser();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization", "bearer " + user.getSessionToken());

        String dataString =
                "{" +
                        "\"id\":\""+obj.getId()+"\"," +
                        "\"type\":\""+obj.getType()+"\"," +
                        "\"icon\":\""+obj.getIcon()+"\"," +
                        "\"description\":\""+obj.getDescription()+"\"" +
                        "}";

       Unirest.put("https://api-todo-unb.herokuapp.com/category/{userId}")
                .routeParam("userId",user.getId())
                .headers(header)
                .body(dataString)
                .asJson();
    }
}