package app.interfaces;

import app.controllers.UserInstance;
import app.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public interface Storable {
    public void create(UserInstance userInstance);

    public void delete(UserInstance userInstance);

    public void update(UserInstance userInstance);

}
