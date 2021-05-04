package app.controllers;

import app.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public Label emailCadastrado;

    public void createUser(){
        if(passwd.getText().equals(passwdConfirm.getText()) && !passwd.getText().isEmpty()) {
            int code = User.create(email.getText(), passwd.getText(), name.getText());
            if (code == 1) {
                System.out.println("Email ja cadastrado");
                email.setStyle("-fx-border-color: red");
                emailCadastrado.setText("Email ja cadastrado");
                emailCadastrado.setVisible(true);
                return;
            }

            try {
                Stage stage = (Stage) signInButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            emailCadastrado.setText("As senhas precisam ser iguais");
            passwd.setStyle("-fx-border-color: red");
            passwdConfirm.setStyle("-fx-border-color: red");
            emailCadastrado.setVisible(true);
            System.out.println("As senhas precisam ser iguais");
        }
        if(passwd.getText().isEmpty() || passwdConfirm.getText().isEmpty()){
            passwd.setStyle("-fx-border-color: red");
            passwdConfirm.setStyle("-fx-border-color: red");
            emailCadastrado.setText("Campo(s) obrigatorio(s)");
            emailCadastrado.setVisible(true);
        }
        if(email.getText().isEmpty()){
            email.setStyle("-fx-border-color: red");
            emailCadastrado.setText("Campo(s) obrigatorio(s)");
            emailCadastrado.setVisible(true);
        }

    }
}
