package app.controllers;

import app.models.Tasks;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static app.controllers.UserController.listUserCategories;

public class TasksController {
    public DatePicker dateId;
    public TextField taskId;
    public AnchorPane taskCreator;
    public Button savingTask;
    public TextField descriptionId;
    public AnchorPane bg;
    public TextField time;
    public ComboBox categoryId = new ComboBox(FXCollections.observableArrayList(listUserCategories));
    public Button closeTaskCreator;


    public void saveTask(ActionEvent actionEvent) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datetmp = dateId.getValue();
        String timeofthistask = time.getText();
        String date = datetmp.format(dateFormat);
        String datentimeofthistask = date + " " + timeofthistask;
        String titleofthistask = taskId.getText();
        String categoryofthistask = String.valueOf(categoryId.getValue());
        String descriptionofthistask = descriptionId.getText();
        Tasks task = new Tasks(titleofthistask, categoryofthistask, false, false, datentimeofthistask, descriptionofthistask);
        ArrayList<String> newTaskArray = new ArrayList<String>();
        newTaskArray.add(task.title);
        newTaskArray.add(task.category);
        newTaskArray.add(String.valueOf(task.done));
        newTaskArray.add(String.valueOf(task.favourite));
        newTaskArray.add(task.deadline);
        newTaskArray.add(task.description);
        JSONObject newTaskObject = new JSONObject();
        newTaskObject.put("Task:", newTaskArray);
        try (FileWriter file = new FileWriter("tasksList.json", true)) {
            file.write(newTaskObject.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        taskCreator.setVisible(false);
    }

    public void closeTaskTab(ActionEvent actionEvent) {
        taskCreator.setVisible(false);
    }
}
