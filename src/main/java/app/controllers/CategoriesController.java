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
import javafx.stage.FileChooser;
import org.json.JSONObject;

import java.io.File;
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

    @FXML
    private AnchorPane pnIcons;



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


            String filePath = URIImage;
            Image icon = new Image(filePath, 35, 35, false, false);
            ImageView imageView = new ImageView(icon);
            imageView.setPreserveRatio(true);

            gpNavBar.add(new ImageView(icon), column, row);
            row++;

            // event that occurs when the icon is clicked
            imageView.setOnMouseClicked((e) -> {
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
    void selectIcon(ActionEvent event) {

        //FileChooser fileChooser = new FileChooser();
        //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
        //fileChooser.getExtensionFilters().add(extFilter);
        //fileChooser.setTitle("Escolha um ícone para a nova categoria");

        //File file = fileChooser.showOpenDialog(pnNewCategory.getScene().getWindow());

        //if (file != null) {
        //    this.txtIcon.setText(file.toString());
        //    URIImage = file.toURI().toString();
        //}

        pnIcons.setVisible(true);

    }
}
