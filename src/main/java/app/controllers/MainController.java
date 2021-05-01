package app.controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {
    public void onClickAddBtn(){}

    public Label btnCloseApp;

    @FXML
    void btnCloseApp(MouseEvent event) {
        System.exit(0);
    }

}