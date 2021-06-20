package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SchedulePageController {

    @FXML
    private AnchorPane scheduleAnchorPane;

    @FXML
    private Button player_ranking;

    @FXML
    private Button referee;

    @FXML
    private Button venue;

    @FXML
    private Button institution;

    @FXML
    private Button booking_info;

    @FXML
    void booking_info_pressed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Booking_Info.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    void institution_pressed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Institution.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    void player_ranking_pressed(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Player_Ranking.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    void referee_pressed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Referee.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    void venue_pressed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Venue.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);
    }

}
