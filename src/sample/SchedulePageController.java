package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SchedulePageController {
    @FXML
    Button teams_button_schedule;
    @FXML
    Button venues_button_schedule;
    @FXML
    Button tournaments_button_schedule;


    @FXML
    private AnchorPane scheduleAnchorPane;

    public void teams_button_schedule_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Teams_Page.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);

    }

    public void tournaments_button_schedule_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournaments_Page.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);

    }

    public void venues_button_schedule_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Venues_Page.fxml"));

        scheduleAnchorPane.getChildren().setAll(pane);

    }
}
