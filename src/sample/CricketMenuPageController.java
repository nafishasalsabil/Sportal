package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CricketMenuPageController implements Initializable {

    @FXML
    Button teams_button;
    @FXML
    Button tournaments_button;
    @FXML
    Button venues_button;
    @FXML
    Button schedule_button;

    @FXML
    private ListView<String> teams_listview;
    ObservableList<String> list = FXCollections.observableArrayList("TeamA","TeamB","TeamC");

    @FXML
    private AnchorPane cricketAnchorPane;

    public void teams_button_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Teams_Page.fxml"));

        cricketAnchorPane.getChildren().setAll(pane);
    }

    public void tournaments_button_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournaments_Page.fxml"));

        cricketAnchorPane.getChildren().setAll(pane);

    }

    public void venues_button_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Venues_Page.fxml"));

        cricketAnchorPane.getChildren().setAll(pane);

    }

    public void schedule_button(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Schedule_Page.fxml"));

        cricketAnchorPane.getChildren().setAll(pane);

    }
    public void team_name_clicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + teams_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        cricketAnchorPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teams_listview.setItems(list);

    }
}
