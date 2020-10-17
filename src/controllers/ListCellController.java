package controllers;

import actions.BookMarkAction;
import helper.GotoDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListCellController extends ListCell<String> implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Button button, btn_view;
    @FXML
    private GridPane gridPane;

    private int indexc;

    public void setIndexc(int d) {
        indexc = d;
    }

    public int getIndexc() {
        return indexc;
    }

    BookMarkAction bm = new BookMarkAction();
    @Override
    protected void updateItem(String word, boolean empty) {
        super.updateItem(word, empty);
        Parent Hbox = null;
        if(empty || word == null) {

            setText(null);
            setGraphic(null);

        } else {
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/components/ListCell.fxml"));
            mLLoader.setController(this);

            try {
                Hbox = mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            setOnMouseEntered(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    //This works
                    //System.out.println("index: " + getIndex());
                    setIndexc(getIndex());
                }
            });

            setOnMouseClicked(e -> {
                GotoDisplay dp = new GotoDisplay(getItem(), e);
            });

            label.setText(word);

            setText(null);
            setGraphic(Hbox);
        }

    }

    public void clickDelete(ActionEvent event) throws IOException {
        getListView().getItems().remove(getItem());
        bm.deleteBookMark(getIndexc());

        //int index = getListView().getSelectionModel().getSelectedIndex();
        //System.out.println(getIndexc());
    }

    public void setLabel(String s) {
        this.label.setText(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}
