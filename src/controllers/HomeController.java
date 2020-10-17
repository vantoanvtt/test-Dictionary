package controllers;

import helper.HandleDataFileV_A;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btn_nav_search, btn_nav_history, btn_nav_bookmark, btn_nav_settings;

    public HomeController() {
    }

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btn_nav_search){
            stage = (Stage) btn_nav_search.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/SearchScreen.fxml"));
        } else if (event.getSource() == btn_nav_history){
            stage = (Stage) btn_nav_history.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/HistoryScreen.fxml"));
        } else if (event.getSource() == btn_nav_bookmark) {
            stage=(Stage) btn_nav_bookmark.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/BookMarkScreen.fxml"));
        } else if (event.getSource() == btn_nav_settings) {
            stage=(Stage) btn_nav_settings.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/SettingScreen.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
