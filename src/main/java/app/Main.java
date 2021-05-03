package app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    public static void main(String[] args){launch(args);}
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app/views/login.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();
    }
}