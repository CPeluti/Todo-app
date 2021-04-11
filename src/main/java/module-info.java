module Todo.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    exports app;
    opens app.controllers to javafx.fxml;
}