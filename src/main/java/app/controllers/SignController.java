package app.controllers;

import app.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignController {
    public TextField email;
    public Button signInButton;
    public TextField name;
    public PasswordField passwd;
    public PasswordField passwdConfirm;

    public void createUser(){
        if(passwd.getText().equals(passwdConfirm.getText())){
            User.Create(email.getText(),passwd.getText(),name.getText());

            try{
                Stage stage = (Stage) signInButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("As senhas precisam ser iguais");
        }

    }
}
