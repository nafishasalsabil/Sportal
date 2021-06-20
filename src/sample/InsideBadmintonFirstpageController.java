package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsideBadmintonFirstpageController implements Initializable {

    @FXML
    private AnchorPane badminton_anchorpane;

    @FXML
    private BorderPane borderpaneBadminton;
    @FXML
    private Button teams_button1;

    @FXML
    private Button tournaments_button_teams1;

    @FXML
    private Button venues_button_teams1;

    @FXML
    private Button schedule_button_teams1;

    @FXML
    private Button back_button_navigation;

    @FXML
    private AnchorPane teamsAnchorpane;

    @FXML
    private ListView<String> badmintonteamlistView;
    ObservableList<String> list2 = FXCollections.observableArrayList();
    public static int id_player;
    @FXML
    void obNavigationBackClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/menu_page.fxml"));

        badminton_anchorpane.getChildren().setAll(pane);
    }

    @FXML
    void onBadmintonTeamNameClicked(MouseEvent event) {
        System.out.println("clicked on " + badmintonteamlistView.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        TeamNameClass teamNameClass = new TeamNameClass();
        TennisPlayerProfileController eachTeamDetailPageController = new TennisPlayerProfileController();
        eachTeamDetailPageController.teamname(badmintonteamlistView.getSelectionModel().getSelectedItem());
        loadpage("TennisPlayerProfile");


    }
    private void loadpage(String page) {
        Parent root = null;
        try{
            root=FXMLLoader.load(getClass().getClassLoader().getResource("sample/"+page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpaneBadminton.setCenter(root);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        badmintonteamlistView.setItems(list2);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT players.player_name FROM players WHERE players.sport_id = 4;");


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                list2.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));
//                id_player=resultSet.getInt("player_id");

            }
            //  detail_list.addAll(each_team_detail_class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectMSSQL cnObj = new ConnectMSSQL();

    }

    public void forAddingTeam(MouseEvent mouseEvent) {
        try {
            FXMLLoader addNewItemLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample/AddBadmintonTeamDialogBox.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(addNewItemLoader.load()));
            AddBadmintonTeamDialogBoxController addBadmintonTeamDialogBoxController = addNewItemLoader.getController();
            secondStage.showAndWait();

            Optional<String> result = addBadmintonTeamDialogBoxController.getNewItem();
            if (result.isPresent()) {
                System.out.println("Your name: " + result.get());
                list2.add(result.get());
                badmintonteamlistView.setItems(list2);
            }
        } catch (IOException ex) {
            Logger.getLogger(InsideBadmintonFirstpageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 /*   @FXML
    void schedule_button_clicked(MouseEvent event) {
        loadpage("Schedule_Page");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }*/

    @FXML
    void teams_button_clicked(MouseEvent event) {
        borderpaneBadminton.setCenter(teamsAnchorpane);
        badmintonteamlistView.setItems(list2);
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
       /* schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");*/

    }

    @FXML
    void tournamments_button_clicked(MouseEvent event) {
        loadpage("Tennis_Standings");

        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
       /* schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
*/
    }

    /*@FXML
    void venues_button_clicked(MouseEvent event) {
        loadpage("Venues_Page");

        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }*/

}
