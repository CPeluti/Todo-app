package app.models;


import com.google.gson.JsonObject;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.json.JSONObject;

import java.net.URI;



public class User{
    String name;
    String lastName;
    String email;
    String timeZone;
    String imageUrl;
    String sessionToken;
    String id;

    public User(String name, String lastName, String email,String timeZone, String imageUrl, String sessionToken, String id) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.sessionToken = sessionToken;
        this.timeZone = timeZone;
        this.id = id;
    }



    public static User validateLogin(String name, String password){
        try {
            /*URL url = new URL("https://api-todo-unb.herokuapp.com/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonString = "{\"name\": "+"\""+name+"\""+", \"passwd\": "+"\""+password+"\"}";
            System.out.println(jsonString);

            OutputStream outputStream = connection.getOutputStream();
            byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input,0, input.length);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder res = new StringBuilder();
            String resLine = null;
            while((resLine = buffer.readLine()) != null){
                res.append(resLine.trim());
            }
            System.out.println(res.toString());
            */


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

            User user = new User(
                    json.getString("name"),
                    json.getString("lastname"),
                    json.getString("email"),
                    json.getString("image"),
                    json.getString("jwt"),
                    json.getString("timezone"),
                    json.getString("id")
            );
            return user;
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
}