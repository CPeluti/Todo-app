package app.interfaces;

import app.controllers.UserInstance;
import app.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public interface Storable {
    public static void create(Object obj, UserInstance userInstance){};

    public static void delete(int id, UserInstance userInstance){
        User user = userInstance.getUser();
        String url = "https://api-todo-unb.herokuapp.com/tasks/" + user.getId();
        String dataString = "{\"taskId\":\""+id+"\"}";

        HttpResponse<JsonNode> data = Unirest.delete(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
        JSONObject json = data.getBody().getObject();
    }
    public static void update(Object obj, UserInstance userInstance){};

}
