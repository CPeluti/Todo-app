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

public class LoginController{

    public AnchorPane loginAnchor;
    public Label labelErrorLogin;
    private UserInstance userInstance = UserInstance.getInstance();
    public TextField userField;
    public TextField passwordField;
    public Button loginButton;

    public void onClickLogin(ActionEvent e) {
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
        //}else if(user.getId().isEmpty()){


        }else{
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