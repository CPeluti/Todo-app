module Todo.app.main {
    requires javafx.controls;
    requires javafx.fxml;

    exports app;
    opens app.controllers to javafx.fxml;
}