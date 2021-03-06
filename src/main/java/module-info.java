module Todo.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx.fontawesome;

    requires fontawesomefx.controls;

    requires unirest.java;
    requires com.google.gson;


    exports app;

    opens app.controllers to javafx.fxml;
    opens app.models to com.google.gson;
}
