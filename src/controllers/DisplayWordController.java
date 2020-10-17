package controllers;

import actions.BookMarkAction;
import com.jfoenix.controls.JFXTextArea;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import dictionary.Word;
import helper.APISpeech;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.Database;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayWordController implements Initializable {
    private Word resultWord;

    @FXML
    private Label word_target, word_spelling;
    @FXML
    private JFXTextArea word_explain, the_sentence;

    @FXML
    private Button btn_nav_back, btn_star, btn_speech, btn_edit, btn_delete;

    BookMarkAction bookmark = new BookMarkAction();

    public void initData(Word selectedWord, String sentence) {
        resultWord = selectedWord;
        //System.out.println("spellingggggggg" + resultWord.getWord_spelling());

        word_target.setText(resultWord.getWord_target());
        word_spelling.setText(resultWord.getWord_spelling());
        word_explain.setText(resultWord.getWord_explain());
        the_sentence.setText(sentence);
    }

    @FXML
    private void handleBackButtonAction (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btn_nav_back){
            stage = (Stage) btn_nav_back.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/SearchScreen.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Search Screen");
        stage.show();
    }

    @FXML
    private void handleDeleteButtonAction (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == btn_delete){
            Database.DeleteWord(resultWord);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Infomation");
            alert.setHeaderText("Notification");
            alert.setContentText("The word is deleted in the dictionary");
            alert.show();
            stage = (Stage) btn_delete.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("/screens/Home.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Home Screen");
        stage.show();
    }

    @FXML
    private void handleBookMark (ActionEvent event) throws IOException {
        if (!bookmark.getBookMarkfromFile().contains(resultWord.getWord_target()))
        {
            bookmark.insertBookMark(resultWord.getWord_target());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Mark Infomation");
            alert.setHeaderText("Notification");
            alert.setContentText("The word is saved in the book mark");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Mark Infomation");
            alert.setHeaderText("Notification");
            alert.setContentText("The word is already in the book mark");
            alert.show();
        }

    }

    @FXML
    private void handleButtonSpeech (ActionEvent event) {

        APISpeech speech = new APISpeech(resultWord.getWord_target());
        /*System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin16");
        voice.allocate();
        try {
            voice.speak(resultWord.getWord_target());
        } catch (Exception e) {

        }*/
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
