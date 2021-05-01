package app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class taskBoxController extends Stage implements Initializable {
    public taskBoxController() {

    }

    public void setTaskTitle(Label taskTitle) { this.taskTitle = taskTitle; }

    public void setTaskDeadline(Label taskDeadline) { this.taskDeadline = taskDeadline; }

    public void setOpenTaskInfo(Button openTaskInfo) { this.openTaskInfo = openTaskInfo; }

    public void setDoneButton(RadioButton doneButton) { this.doneButton = doneButton; }

    public Label taskTitle;
    public Label taskDeadline;
    public Button openTaskInfo;
    public RadioButton doneButton;

    public taskBoxController(Parent parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app/views/taskBox.fxml"));
        fxmlLoader.setController(this);
        try{
            setScene(new Scene((Parent) fxmlLoader.load()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void show(UserController userController) {
    }
}
