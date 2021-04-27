package app.controllers;

import app.models.UserCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserController {

    public Button addNewTask;
    public AnchorPane main;
    public Label newTaskLabel;
    @FXML
    private TasksController TasksController ;


    public void openNewTaskBox(ActionEvent actionEvent) throws IOException {
        AnchorPane taskTabtmp = FXMLLoader.load(getClass().getResource("/app/views/tasksViews.fxml"));
        main.getChildren().addAll(taskTabtmp);
    }

    public void messageNewTask(MouseEvent mouseEvent) {
        newTaskLabel.setVisible(true);
    }

    public void messaNewTaskout(MouseEvent mouseEvent) {
        newTaskLabel.setVisible(false);
    }
}