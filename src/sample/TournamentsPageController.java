package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TournamentsPageController {
    @FXML
    Button teams_button_tournament;
    @FXML
    Button venues_button_tournament;
    @FXML
    Button schedule_button_tournament;


    @FXML
    private AnchorPane tournamentsAnchorPane;

    public void teams_button_tournament_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Teams_Page.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);

    }

    public void venues_button_tournament_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Venues_Page.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);

    }

    public void schedule_button_tournament_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Schedule_Page.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);

    }
}
