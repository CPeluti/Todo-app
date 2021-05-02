package app.models;

import app.controllers.Singleton;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserCategory extends Category {

    public UserCategory(String type, String icon, String description, int id) {
        super(type, icon, description, id);
    }




    public static void create(UserCategory obj, Singleton singleton) {
        User user = singleton.getUser();

        System.out.println(user.sessionToken);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization", "bearer " + user.sessionToken);

        //String url = "" + user.id;
        String dataString =
                "{" +
                    "\"id\":\""+obj.getId()+"\"," +
                    "\"type\":\""+obj.getType()+"\"," +
                    "\"icon\":\""+obj.getIcon()+"\"," +
                    "\"description\":\""+obj.getDescription()+"\"" +
                "}";

        System.out.println(dataString);
        //System.out.println(url);

        HttpResponse<JsonNode> data = Unirest.post("https://api-todo-unb.herokuapp.com/category/{userId}")
                .routeParam("userId",user.id)
                .headers(header)
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
        String dataString =
                "{\"" +
                    "type\":\""+obj.getType()+"\",\"" +
                    "icon\":\""+obj.getIcon()+"\",\"" +
                    "description\":\""+obj.getDescription()+"\"" +
                "}";

        HttpResponse<JsonNode> data = Unirest.put(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();
    }
}