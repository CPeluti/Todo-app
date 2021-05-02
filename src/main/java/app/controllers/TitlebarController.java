package app.controllers;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TitlebarController {
    public Label btnCloseApp;

    public void btnCloseApp(MouseEvent event) {
        System.exit(0);
    }
}
