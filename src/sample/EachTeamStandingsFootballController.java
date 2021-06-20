package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EachTeamStandingsFootballController {

    @FXML
    private AnchorPane teamsAnchorPaneEach;

    @FXML
    private VBox teamVbox;

    @FXML
    private TextField search_textfield;

    @FXML
    private Label teamName_Label;

    @FXML
    private Button squad_button;

    @FXML
    private Button matches_button;

    @FXML
    private Button standings_button;

    public void squad_pressed(ActionEvent actionEvent) throws IOException {
        standings_button.setStyle("-fx-background-color: #383E56;-fx-text-fill: #ffffff;");
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Standings_Football.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);

    }

    public void matches_pressed(ActionEvent actionEvent) throws IOException {
        standings_button.setStyle("-fx-background-color: #383E56;-fx-text-fill: #ffffff;");
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Standings_Football.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);

    }

    public void standings_pressed(ActionEvent actionEvent) throws IOException {
        standings_button.setStyle("-fx-background-color: #383E56;-fx-text-fill: #ffffff;");
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Standings_Football.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);

    }
}
