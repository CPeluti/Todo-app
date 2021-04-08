package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserController {

    @FXML
    private Label btnNewCategory;

    @FXML
    private Label lbMsgMakeCategory;

    @FXML
    private AnchorPane pnNewCategory;

    @FXML
    private TextField txtCategoryName;

    @FXML
    private TextArea txtCategoryDescription;

    @FXML
    private Button btnSaveNewCategory;

    @FXML
    void showMsgMakeCategory(MouseEvent event) {    // When the mouse enters the button, it shows the message "Criar nova categoria"
        if(!pnNewCategory.isVisible()){
            lbMsgMakeCategory.setVisible(true);
        }
    }

    @FXML
    void btnMakeCategory(MouseEvent event) {
        pnNewCategory.setVisible(true);
    }

    @FXML
    void saveNewCategory(ActionEvent event) {

        String nameCategory = txtCategoryName.getText();
        String descriptionCategory = txtCategoryDescription.getText();

        pnNewCategory.setVisible(false);
    }
}