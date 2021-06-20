package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EachTeamMatchesFootballController implements Initializable {
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

    @FXML
    private ComboBox tournament_combobox;
    @FXML
    private TableView<?> matches_table;
    @FXML
    private TableColumn<?, ?> timecolumn;

    @FXML
    private TableColumn<?, ?> teamAcolumn;

    @FXML
    private TableColumn<?, ?> goalAcolumn;

    @FXML
    private TableColumn<?, ?> hyphencolumn;

    @FXML
    private TableColumn<?, ?> goalBcolumn;

    @FXML
    private TableColumn<?, ?> teamBcolumn;

    ObservableList<String> tournament_list = FXCollections.observableArrayList("argh","qwertyj","qwerty","sdfghj");

    public void squad_pressed(ActionEvent actionEvent) throws IOException {
        squad_button.setStyle("-fx-background-color: #383E56;-fx-text-fill: #ffffff;");
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Standings_Football.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);
    }

    public void matches_pressed(ActionEvent actionEvent) throws IOException {
        matches_button.setStyle("-fx-background-color: #383E56;-fx-text-fill: #ffffff;");
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Standings_Football.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);
    }

    public void standings_pressed(ActionEvent actionEvent) {
    }

    public void select_tournament(ActionEvent actionEvent) {
        System.out.println(tournament_combobox.getSelectionModel().getSelectedItem().toString());
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tournament_combobox.setItems(tournament_list);
    }



}
