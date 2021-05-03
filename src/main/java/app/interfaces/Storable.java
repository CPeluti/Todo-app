package app.interfaces;

import app.controllers.Singleton;
import app.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public interface Storable {
    public static void create(Object obj, Singleton singleton){};

    public static void delete(int id, Singleton singleton){
        User user = singleton.getUser();
        String url = "https://api-todo-unb.herokuapp.com/tasks/" + user.getId();
        String dataString = "{\"taskId\":\""+id+"\"}";

        HttpResponse<JsonNode> data = Unirest.delete(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();
    }
    public static void update(Object obj, Singleton singleton){};

}
