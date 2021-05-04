package app.models;

import app.controllers.UserInstance;
import app.interfaces.Storable;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

public class UserCategory extends Category implements Storable {

    public UserCategory(String type, String icon, String description, int id) {
        super(type, icon, description, id);
    }


    public void create(UserInstance userInstance) {
        User user = userInstance.getUser();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization", "bearer " + user.getSessionToken());

        String dataString =
                "{" +
                    "\"id\":\""+this.getId()+"\"," +
                    "\"type\":\""+this.getType()+"\"," +
                    "\"icon\":\""+this.getIcon()+"\"," +
                    "\"description\":\""+this.getDescription()+"\"" +
                "}";


        Unirest.post("https://api-todo-unb.herokuapp.com/category/{userId}")
                .routeParam("userId",user.getId())
                .headers(header)
                .body(dataString)
                .asJson();

    }

    public void delete(UserInstance userInstance) {

        User user = userInstance.getUser();
        String url = "https://api-todo-unb.herokuapp.com/category/" + user.getId();

        String dataString = "{\"categoryId\":\""+this.getId()+"\"}";

        Unirest.delete(url)
                .header("Content-Type","application/json")
                .header("Authorization", "bearer " + user.getSessionToken())
                .body(dataString)
                .asJson();
    }

    public void update(UserInstance userInstance) {
        User user = userInstance.getUser();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization", "bearer " + user.getSessionToken());

        String dataString =
                "{" +
                        "\"id\":\""+this.getId()+"\"," +
                        "\"type\":\""+this.getType()+"\"," +
                        "\"icon\":\""+this.getIcon()+"\"," +
                        "\"description\":\""+this.getDescription()+"\"" +
                        "}";

       Unirest.put("https://api-todo-unb.herokuapp.com/category/{userId}")
                .routeParam("userId",user.getId())
                .headers(header)
                .body(dataString)
                .asJson();
    }
}