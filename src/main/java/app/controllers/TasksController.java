package app.controllers;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;




public class TasksController {
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
    /*
    private JSONObject newTaskObject = new JSONObject();

    public TasksController() throws FileNotFoundException {
    }

    public void saveTask(ActionEvent actionEvent) throws Exception {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        TemporalAccessor timeX = timeFormat.parse(time.getText());
        if (!time.getText().equals(timeFormat.format(timeX))) {
            System.out.println("ignorar");//ERRADO;
        }
        if (dateId.getValue() == null || time.getText() == null || taskId.getText() == null||categoryId.getValue() == null || descriptionId.getText() == null ){
            System.out.println("ignorar");//ERRADO
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datetmp = dateId.getValue();
        String timeofthistask = time.getText();
        String date = datetmp.format(dateFormat);
        String datentimeofthistask = date + " " + timeofthistask;
        String titleofthistask = taskId.getText();
        String categoryofthistask = String.valueOf(categoryId.getValue());
        String descriptionofthistask = descriptionId.getText();
        int identification = (int) (new Date().getTime()/1000);

        Tasks task = new Tasks(identification, titleofthistask, categoryofthistask, false, false, datentimeofthistask, descriptionofthistask, false);
        newTaskObject.put("id", String.valueOf(task.id));
        newTaskObject.put("title", task.title);
        newTaskObject.put("category", task.category);
        newTaskObject.put("done", String.valueOf(task.done));
        newTaskObject.put("favourite", String.valueOf(task.favourite));
        newTaskObject.put("deadline", task.deadline);
        newTaskObject.put("description", task.description);
        newTaskObject.put("deleted", String.valueOf(task.deleted));

        try (FileWriter file = new FileWriter("tasksList.json", true)) {
            file.write(newTaskObject.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }



        taskCreator.setVisible(false);
    }

     */

    public void closeTaskTab(ActionEvent actionEvent) {
        taskCreator.setVisible(false);
    }
}
