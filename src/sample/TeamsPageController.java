package sample;

import com.sun.corba.se.pept.transport.InboundConnectionCache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamsPageController implements Initializable {
    @FXML
    private Button teams_button1;
    @FXML
    private AnchorPane cricket_anchorpane;
    @FXML
    Button tournaments_button_teams1;
    @FXML
    Button venues_button_teams1;
    @FXML
    Button schedule_button_teams1;
    @FXML
    private ListView<String> teams_listview;
    ObservableList<String> list = FXCollections.observableArrayList("TeamA","TeamB","TeamC");
    @FXML
    private AnchorPane teamsAnchorpane;
    @FXML
    private BorderPane borderpaneCricket;

    @FXML
    private AnchorPane teamsAnchorPane;

    public void tournaments_button_action_teams(ActionEvent actionEvent) throws IOException {
       /* AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournaments_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);
*/
//        loadpage("Tournaments_Page");
        /*teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; ");
*/
    }

    public void venues_button_action_teams(ActionEvent actionEvent) throws IOException {
    /*    AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Venues_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);
*/
    }

    public void schedule_button_teams(ActionEvent actionEvent) throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Schedule_Page.fxml"));
//
//        teamsAnchorPane.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    teams_listview.setItems(list);

    }

    public void team_name_clicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + teams_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        loadpage("Each_Team_Detail_Page");
    }
    private void loadpage(String page) {
        Parent root = null;
        try{
            root=FXMLLoader.load(getClass().getClassLoader().getResource("sample/"+page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpaneCricket.setCenter(root);
    }

    public void venues_button_clicked(MouseEvent mouseEvent) {
        loadpage("Venues_Page");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void schedule_button_clicked(MouseEvent mouseEvent) {
        loadpage("Schedule_Page");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void teams_button_clicked(MouseEvent mouseEvent) {
        borderpaneCricket.setCenter(teamsAnchorpane);
        teams_listview.setItems(list);
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void tournament_button_clicked(MouseEvent mouseEvent) {
        loadpage("Tournaments_Page");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
    }

    public void obNavigationBackClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/menu_page.fxml"));

        cricket_anchorpane.getChildren().setAll(pane);

    }
}
