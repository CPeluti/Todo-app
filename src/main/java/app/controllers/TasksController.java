package app.controllers;

import app.models.Tasks;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TasksController {
    public RadioButton favouriteId;
    public DatePicker dateId;
    public TextField taskId;
    public TextField categoryId;
    public AnchorPane taskCreator;
    public Button addNEW; //adding new Task (opens up a new box with the addTask)
    public Button savingTask;
    public TextField descriptionId;
    public AnchorPane bg;

    public void newTaskBoxOpen(ActionEvent actionEvent) {
        taskCreator.setVisible(true);
    }

    public void amIFavourite(ActionEvent actionEvent) {
        favouriteId.setSelected(true);
    }

    public void saveTask(ActionEvent actionEvent) {
        try {
            FileWriter writetofile = new FileWriter("Tasks.txt", true);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = dateId.getValue();
            String dateofthistask = date.format(dateFormat);
            String titleofthistask = taskId.getText();
            String categoryofthistask = categoryId.getText();
            String descriptionofthistask = descriptionId.getText();
            Boolean isfavouriteofthistask = (Boolean) favouriteId.isSelected();
            Tasks task = new Tasks(titleofthistask, categoryofthistask, false, isfavouriteofthistask, dateofthistask, descriptionofthistask);
            writetofile.write(titleofthistask + "-");
            writetofile.write(categoryofthistask + "-");
            writetofile.write(String.valueOf(false) + "-");
            writetofile.write(String.valueOf(isfavouriteofthistask) + "-");
            writetofile.write(dateofthistask + "-");
            writetofile.write(descriptionofthistask + "-");
            writetofile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        taskCreator.setVisible(false);
    }
}
