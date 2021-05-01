package app.controllers;

import app.models.Category;
import app.models.Tasks;
import app.models.UserCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;


public class TasksController {
    public Button addNewTask;
    public Label newTaskLabel;

    private Singleton singleton = Singleton.getInstance();
    public DatePicker dateId;
    public TextField taskId;
    public AnchorPane taskCreator;
    public Button savingTask;
    public TextField descriptionId;
    public TextField time;
    public ComboBox categoryId;
    public GridPane listofTasksGrid;
    public static int selectedCategory;


    public void saveTask(ActionEvent actionEvent) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        TemporalAccessor timeX = timeFormat.parse(time.getText());
        String taskTitle = taskId.getText();
        String taskCategory = String.valueOf(categoryId.getValue());
        String taskDescription = descriptionId.getText();
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
            String taskTime = time.getText();
            String date = datetmp.format(dateFormat);
            String taskDate = date + " " + taskTime;
            Tasks tasks = new Tasks(identification, taskTitle, taskCategory, false, false, taskDate, taskDescription, false);

            this.singleton.listTasks.add(tasks);
            Tasks.create(tasks, singleton);
            taskCreator.setVisible(false);
            taskCreator.setDisable(false);
        }
    }

    public void closeTaskTab(ActionEvent actionEvent) {
        taskCreator.setVisible(false);
    }

    public void onClickBtnAddTask(ActionEvent actionEvent){
        ArrayList<UserCategory> categories = singleton.getUser().getCategories();
        taskCreator.setVisible(true);

        if(!categories.isEmpty()){
            ArrayList<String> categoriesTitle = new ArrayList<>();
            for(UserCategory category: categories){
                categoriesTitle.add(category.getType());
            }
            categoryId.setItems(FXCollections.observableArrayList(categoriesTitle));
        }

    }

}