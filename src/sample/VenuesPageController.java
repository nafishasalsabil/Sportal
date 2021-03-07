package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VenuesPageController {
    @FXML
    Button teams_button_venue;
    @FXML
    Button tournament_button_venue;
    @FXML
    Button schedule_button_venue;


    @FXML
    private AnchorPane venueAnchorPane;

    public void teams_button_venue_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Teams_Page.fxml"));

        venueAnchorPane.getChildren().setAll(pane);

    }

    public void tournament_button_venue_action(ActionEvent actionEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournaments_Page.fxml"));

        venueAnchorPane.getChildren().setAll(pane);
    }

    public void schedule_button_venue_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Schedule_Page.fxml"));

        venueAnchorPane.getChildren().setAll(pane);

    }
}
