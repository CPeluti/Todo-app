package app.controllers;

import app.models.UserCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.SVGPath;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CategoriesController {
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

    private SVGPath iconSvg;

    @FXML
    private AnchorPane pnIcons;

    @FXML
    private ScrollPane spIcons;

    @FXML
    private Button btnCancelIcon;


    public void initialize(){
        pnNewCategory.setVisible(false);
        pnIcons.setVisible(false);
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
        txtIcon.setDisable(true);
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


            SVGPath selectedIcon = iconSvg;

            //Image icon = new Image(filePath, 35, 35, false, false);
            //ImageView imageView = new ImageView(icon);
            //imageView.setPreserveRatio(true);

            gpNavBar.add(selectedIcon, column, row);
            row++;

            // event that occurs when the icon is clicked
            selectedIcon.setOnMouseClicked((e) -> {
                // show the tasks of the respective category
            });
        }

        this.spCategories.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.spCategories.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.spCategories.setContent(gpNavBar);
    }

    @FXML
    void saveNewCategory(ActionEvent event) {


        // checks if any of the text fields are empty
        if(txtCategoryName.getText().equals("") || txtIcon.getText().equals("") || txtCategoryDescription.getText().equals("")){

        }
        else{

            // take data from the text fields
            String nameCategory = txtCategoryName.getText();
            String descriptionCategory = txtCategoryDescription.getText();
            String icon = txtIcon.getText();

            // create a new UserCategory object and put it in the list of categories
            app.models.UserCategory newCategory = new UserCategory(nameCategory, icon, descriptionCategory);
            listUserCategories.add(newCategory);

            // create a new JSON object to put the new category in the JSON archive
            JSONObject newCategory2Json = new JSONObject();

            FileWriter file = null;

            newCategory2Json.put("type", nameCategory);
            newCategory2Json.put("icon", icon);
            newCategory2Json.put("description", descriptionCategory);

            try{
                file = new FileWriter("categories.json");
                file.write(newCategory2Json.toString());
                file.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            // calls the method to show the categories on the screen
            displayCategories();
        }

        txtCategoryDescription.clear();
        txtCategoryName.clear();

        pnNewCategory.setVisible(false);
    }

    @FXML
    void cancelNewCategory(ActionEvent event) {
        //close button
        txtCategoryDescription.clear();
        txtCategoryName.clear();
        pnNewCategory.setVisible(false);
    }

    @FXML
    void cancelIcon(ActionEvent event) {

        pnIcons.setVisible(false);
    }

    @FXML
    void selectIcon(ActionEvent event) {


        //GridPane gpIcons = new GridPane();
        //gpIcons.setVgap(10);
        //ColumnConstraints columnIcon = new ColumnConstraints(10, 100, 100);
        //columnIcon.setHgrow(Priority.ALWAYS);
        //gpIcons.getColumnConstraints().addAll(columnIcon, columnIcon, columnIcon, columnIcon, columnIcon, columnIcon);
        //int row = 0;
        //int column = 0;

        // Icon 1
        //SVGPath icon = new SVGPath();
        //icon.setContent("M436 160c6.6 0 12-5.4 12-12v-40c0-6.6-5.4-12-12-12h-20V48c0-26.5-21.5-48-48-48H48C21.5 0 0 21.5 0 48v416c0 26.5 21.5 48 48 48h320c26.5 0 48-21.5 48-48v-48h20c6.6 0 12-5.4 12-12v-40c0-6.6-5.4-12-12-12h-20v-64h20c6.6 0 12-5.4 12-12v-40c0-6.6-5.4-12-12-12h-20v-64h20zm-68 304H48V48h320v416zM208 256c35.3 0 64-28.7 64-64s-28.7-64-64-64-64 28.7-64 64 28.7 64 64 64zm-89.6 128h179.2c12.4 0 22.4-8.6 22.4-19.2v-19.2c0-31.8-30.1-57.6-67.2-57.6-10.8 0-18.7 8-44.8 8-26.9 0-33.4-8-44.8-8-37.1 0-67.2 25.8-67.2 57.6v19.2c0 10.6 10 19.2 22.4 19.2z");

        //gpIcons.add(icon, column, row);

        //icon.setOnMouseClicked((e) -> {
        //    iconSvg = icon;
        //});




        //this.spIcons.setStyle("-fx-background: #571da8; -fx-border-color: #571da8");
        //this.spIcons.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //this.spIcons.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //this.spIcons.setContent(gpIcons);

        //pnIcons.setVisible(true);
        //spIcons.setVisible(true);

    }


}

