package app.controllers;

import app.models.Tasks;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;


public class TasksController {
    private Singleton singleton = Singleton.getInstance();
    public DatePicker dateId;
    public TextField taskId;
    public AnchorPane taskCreator;
    public Button savingTask;
    public TextField descriptionId;
    public AnchorPane bg;
    public TextField time;
    public Button closeTaskCreator;
    String[] list = {"TP1", "MUSIC", "CN"};
    public ComboBox categoryId;

    public void initialize() {
        categoryId.getItems().addAll(FXCollections.observableArrayList(list));
    }

    public void saveTask(ActionEvent actionEvent) throws Exception {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        TemporalAccessor timeX = timeFormat.parse(time.getText());
        String titleofthistask = taskId.getText();
        String categoryofthistask = String.valueOf(categoryId.getValue());
        String descriptionofthistask = descriptionId.getText();
        int identification = (int) (new Date().getTime() / 1000);

        if (!time.getText().equals(timeFormat.format(timeX)) || time.getText().equals("")) {
            time.setStyle("-fx-border-color: #ff0000");
        }
        else if (dateId.getValue().equals("")) {
            dateId.setStyle("-fx-border-color: #ff0000");
        }
        else if (taskId.getText().equals("")) {
            taskId.setStyle("-fx-border-color: red");
        }
        else if (categoryId.getValue().equals("")) {
            categoryId.setStyle("-fx-border-color: red");
        }
        else if ( descriptionId.getText().equals("")) {
            descriptionId.setStyle("-fx-border-color: red");
        }
        else {
            LocalDate datetmp = dateId.getValue();
            String timeofthistask = time.getText();
            String date = datetmp.format(dateFormat);
            String datentimeofthistask = date + " " + timeofthistask;
            Tasks tasks = new Tasks(identification, titleofthistask, categoryofthistask, false, false, datentimeofthistask, descriptionofthistask, false);
            singleton.setTasks(tasks);

            this.singleton.listTasks.add(tasks);
            Tasks.create(tasks, singleton);
            taskCreator.setVisible(false);
        }
    }

    public void closeTaskTab(ActionEvent actionEvent) {
        taskCreator.setVisible(false);
    }
}