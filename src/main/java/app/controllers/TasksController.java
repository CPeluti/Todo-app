package app.controllers;

import app.models.Tasks;
import app.models.UserCategory;
import javafx.collections.FXCollections;
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
    public AnchorPane addTasksPane;
    private Singleton singleton = Singleton.getInstance();
    public DatePicker dateId;
    public TextField taskId;
    public AnchorPane taskCreator;
    public Button savingTask;
    public TextField descriptionId;
    public TextField time;
    public Button closeTaskCreator;
    public ComboBox categoryId;

    public Button infoBoxButton;
    public GridPane listofTasksGrid;

    ArrayList<String> tmpList = new ArrayList<>();
    public static ArrayList<String> lookForNames(ArrayList<String> comboBoxList, ArrayList<UserCategory> list) {
        for (UserCategory find: list) {
            comboBoxList.add(find.getType());
            System.out.println("its working");
        }
        return comboBoxList;
    }
    ArrayList<String> comboBoxList = new ArrayList<>(lookForNames(tmpList, singleton.getListUserCategories()));


    public void saveTask(ActionEvent actionEvent) {
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
            taskCreator.setDisable(false);
        }
    }

    public void messageNewTask(MouseEvent mouseEvent) { newTaskLabel.setVisible(true); }

    public void messageNewTaskout(MouseEvent mouseEvent) { newTaskLabel.setVisible(false); }

    public void closeTaskTab(ActionEvent actionEvent) {
        taskCreator.setVisible(false);
        taskCreator.setDisable(false);
    }

    public void onClickBtnAddTask(ActionEvent actionEvent){
        System.out.println("teste");
    }

}