package controllers;

import actions.BookMarkAction;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookMarkController implements Initializable {
    @FXML
    private JFXListView<String> list;

    @FXML
    private Button btn_nav_back;

    BookMarkAction bookmark = new BookMarkAction();
    ObservableList<String> listv = FXCollections.observableArrayList(bookmark.getBookMarkfromFile());



    @FXML
    private void handleBackButtonAction (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btn_nav_back){
            stage = (Stage) btn_nav_back.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/Home.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Home Screen");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(listv);
        list.setCellFactory(studentListView -> new ListCellController());
    }
}
