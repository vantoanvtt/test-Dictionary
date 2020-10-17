package controllers;

import actions.HistoryAction;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private JFXListView<String> historyList;

    @FXML
    private Button btn_nav_back;

    HistoryAction history = new HistoryAction();
    ObservableList<String> listv = FXCollections.observableArrayList(history.getHistoryfromFile());

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
        historyList.setItems(listv);
        historyList.setCellFactory(studentListView -> new HistoryListCellController());
    }
}
