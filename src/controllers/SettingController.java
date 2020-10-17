package controllers;

import dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Main;
import service.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private TextField target, spelling;
    @FXML
    private TextArea explain;
    @FXML
    private Button btn_add;

    @FXML
    private void handleAddButtonAction (ActionEvent event) throws IOException {
        Word w = new Word(target.getText(), explain.getText(), spelling.getText());
        if (Main.databaseAV.getListWordTarget().contains(w.getWord_target()) == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Setting Infomation");
            alert.setHeaderText("Notification");
            alert.setContentText("The word is already in the dictionary");
            alert.show();
        } else {
            Database.AddWord(w);
            Main.databaseAV.getListWordTarget().add(w.getWord_target());
            Main.databaseAV.getWordListHashMap().put(w.getWord_target(), w);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Setting Infomation");
            alert.setHeaderText("Notification");
            alert.setContentText("The word is added in the dictionary");
            alert.show();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
