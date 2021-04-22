package app.controllers;

import app.models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    private Singleton singleton = Singleton.getInstance();
    public TextField userField;
    public TextField passwordField;
    public Button LoginBtn;

    public void onClickLogin(ActionEvent e){
        String username = userField.getText();
        String password = passwordField.getText();

        User user = User.validateLogin(username,password);
        if(user != null){
            singleton.setUser(user);
        }

    }

}
