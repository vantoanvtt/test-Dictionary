package controllers;

import actions.HistoryAction;
import dictionary.DictionaryManagement;
import dictionary.Word;
import helper.HandleDataFileV_A;
import helper.HandleDataFromFile;
import helper.HandleSearch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import sample.Main;
import service.APItracau;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {
    @FXML
    private Button btn_nav_back, btn_search, btn_history;
    @FXML
    private TextField textField;

    @FXML
    private ComboBox<String> language;
    @FXML
    private ListView<String> sgList;


    ObservableList cb = FXCollections.observableArrayList("EN", "VI");

    HashMap<String, Word> WordList = new HashMap<>();

    HistoryAction history = new HistoryAction();

    @FXML
    private void handleBackButtonAction (ActionEvent event) throws Exception {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btn_nav_back){
            stage = (Stage) btn_nav_back.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/Home.fxml"));
        } else if(event.getSource() == btn_search) {
            stage = (Stage) btn_search.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/screens/DisplayWordInfomation.fxml"));
            myNewScene = loader.load();

            DisplayWordController controller = loader.getController();
            WordList = Main.databaseAV.getWordListHashMap();
            DictionaryManagement db = new DictionaryManagement(WordList);

            Word w = db.getWordSearch(textField.getText());
            APItracau t = new APItracau(w.getWord_target());
            controller.initData(w, t.getSentence());
            history.insertHistory(textField.getText());

        } else if (event.getSource() == btn_history) {
            stage = (Stage) btn_search.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/screens/HistoryScreen.fxml"));
            myNewScene = loader.load();
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Search Screen");
        stage.show();
    }

    @FXML
    public void HandleCombobox(ActionEvent event) throws FileNotFoundException {
        /*String lg = language.getValue();
        System.out.println(lg);
        autoList1.addAll(Main.dataVA.getVNList());
        autoList2.addAll(Main.dataAV.getListWordTarget());
        if (lg == "VI") {
            System.out.println("entered");
            WordList = Main.dataVA.getWordList();
            TextFields.bindAutoCompletion(textField, autoList1);
        } else {
            //autoList = data.getListWordTarget();
            WordList = Main.dataAV.getWordList();
            TextFields.bindAutoCompletion(textField, autoList2);
        }*/
        //autoList.clear();


    }

    @FXML
    public void OnKeyReleaseSgList(KeyEvent event) {
        if (sgList.isEditable() == false) {
            sgList.setVisible(true);
        }
        sgList.getItems().clear();
        HandleSearch s = new HandleSearch(textField.getText());
        sgList.getItems().addAll(s.getSgList());
    }

    @FXML
    public void onMouseClickSgList(MouseEvent arg0) {
        sgList.setVisible(false);
        System.out.println(sgList.getSelectionModel().getSelectedItem());
        textField.setText(sgList.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        language.setItems(cb);
    }
}
