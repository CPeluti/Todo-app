package app.controllers;

import app.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginController{

    public AnchorPane loginAnchor;
    public Label labelErrorLogin;
    public AnchorPane offline;
    public Button offlineLogin;
    private UserInstance userInstance = UserInstance.getInstance();
    public TextField userField;
    public TextField passwordField;
    public Button loginButton;

    public void loginOffline(){
        offline.setVisible(true);
        userInstance.setUser(new User("","","","","","",new ArrayList<>(),new ArrayList<>()));
        offlineLogin.setOnAction(event -> {
            try{
                changeScene();
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
    public void onClickLogin(ActionEvent e) {
        if (!userField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            String username = userField.getText();
            String password = passwordField.getText();
            User user = User.validateLogin(username,password);
            if(user != null) {
                userInstance.setUser(user);
                try {
                    changeScene();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }else if(user.getId().isEmpty() && user.getSessionToken().isEmpty() ){
                loginOffline();
            }
            else{
                labelErrorLogin.setVisible(true);
            }
        }
        else{
            labelErrorLogin.setVisible(true);
        }


    }

    public void signInBtn(MouseEvent e) throws Exception{
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/signIn.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void changeScene() throws Exception{
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}