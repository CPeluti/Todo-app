module Todo.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx.fontawesome;
    requires unirest.java;
    requires com.google.gson;

    exports app;
    opens app.controllers to javafx.fxml;
}