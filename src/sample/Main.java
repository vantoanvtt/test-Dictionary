package sample;

import helper.HandleDataFileV_A;
import helper.HandleDataFromFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.*;
import service.Database;


public class Main extends Application {

    //public static HandleDataFileV_A dataVA = new HandleDataFileV_A();
    //public static HandleDataFromFile dataAV = new HandleDataFromFile();
    public static Database databaseAV = new Database();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../screens/Home.fxml"));
        Scene scene = new Scene(root, 550, 426);
        primaryStage.setTitle("My title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
