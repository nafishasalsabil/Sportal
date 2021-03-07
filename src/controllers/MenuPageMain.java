package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuPageMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("menu_page.fxml")));
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
