package app.controllers;

import app.Main;
import app.models.UserCategory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class UserController {
    public static ArrayList<UserCategory> listUserCategories = new ArrayList<UserCategory>();
    public ScrollPane spCategories;

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
    private Button btnCancelNewCategory;

    @FXML
    private GridPane gpNavBar;

    @FXML
    private ColumnConstraints columnCategory;

    @FXML
    private Button btsSelectIcon;

    @FXML
    private AnchorPane apFileChooser;

    @FXML
    private TextField txtIcon;
    private String URIImage;


    public void initialize(){
        pnNewCategory.setVisible(false);


    }

    @FXML
    void showMsgMakeCategory(MouseEvent event) {
        lbMsgMakeCategory.setVisible(true);
    }

    @FXML
    void notShowMsgMakeCategory(MouseEvent event) {
        lbMsgMakeCategory.setVisible(false);
    }


    @FXML
    void btnMakeCategory(MouseEvent event) {
        pnNewCategory.setVisible(true);
    }

    private void displayCategories(){

        GridPane gpNavBar = new GridPane();
        gpNavBar.setVgap(10);
        ColumnConstraints columnCategory = new ColumnConstraints(10, 100, 100);
        columnCategory.setHgrow(Priority.ALWAYS);
        gpNavBar.getColumnConstraints().addAll(columnCategory);
        int row = 0;
        int column = 0;


        for(UserCategory usercategory : listUserCategories){


            String filePath = URIImage;
            Image icon = new Image(filePath, 35, 35, false, false);
            ImageView imageView = new ImageView(icon);
            imageView.setPreserveRatio(true);

            gpNavBar.add(new ImageView(icon), column, row);
            row++;
        }

        this.spCategories.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.spCategories.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.spCategories.setContent(gpNavBar);
    }

    @FXML
    void saveNewCategory(ActionEvent event) {

        if(txtCategoryName.getText().equals("") || txtIcon.getText().equals("")){

        }
        else{

            String nameCategory = txtCategoryName.getText();
            String descriptionCategory = txtCategoryDescription.getText();
            String icon = txtIcon.getText();

            app.models.UserCategory newCategory = new UserCategory(nameCategory, icon, descriptionCategory);
            listUserCategories.add(newCategory);

            displayCategories();
        }

        txtCategoryDescription.clear();
        txtCategoryName.clear();

        pnNewCategory.setVisible(false);
    }

    @FXML
    void cancelNewCategory(ActionEvent event) {
        txtCategoryDescription.clear();
        txtCategoryName.clear();
        pnNewCategory.setVisible(false);
    }

    @FXML
    void selectIcon(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Escolha um ícone para a nova categoria");

        File file = fileChooser.showOpenDialog(pnNewCategory.getScene().getWindow());

        if (file != null) {
            this.txtIcon.setText(file.toString());
            URIImage = file.toURI().toString();
        }
    }
}