package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

public class InsideFootballFirstpageController implements Initializable {
    @FXML
    private AnchorPane football_anchorpane;

    @FXML
    private BorderPane borderpaneCricket;

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
    private Button add_team_button1;
    @FXML
    private ListView<String> footballteamlist;

    ObservableList<String> list1 = FXCollections.observableArrayList();

    public void obNavigationBackClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/menu_page.fxml"));

        football_anchorpane.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        footballteamlist.setItems(list1);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select team_name FROM team where sport_id=1;");


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                list1.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));

            }
            //  detail_list.addAll(each_team_detail_class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectMSSQL cnObj = new ConnectMSSQL();



    }

    public void forAddingTeam(MouseEvent mouseEvent) {
        try {
          /*  Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/AddFootballTeamDialogBox.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();*/
            FXMLLoader addNewItemLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample/AddFootballTeamDialogBox.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(addNewItemLoader.load()));
            AddFootballTeamDialogBoxController addFootballTeamDialogBoxController = addNewItemLoader.getController();
            secondStage.showAndWait();

            Optional<String> result = addFootballTeamDialogBoxController.getNewItem();
            if (result.isPresent()) {
                System.out.println("Your name: " + result.get());
                list1.add(result.get());
                footballteamlist.setItems(list1);
            }

            } catch (IOException ex) {
            Logger.getLogger(TeamsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void team_name_clicked(MouseEvent mouseEvent) {

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

    public void teamnameclicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + footballteamlist.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        EachTeamDetailPageForFootballController eachTeamDetailPageController = new EachTeamDetailPageForFootballController();
        eachTeamDetailPageController.teamname(footballteamlist.getSelectionModel().getSelectedItem());
        loadpage("Each_Team_Detail_Page_For_Football");
/*
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page_For_Football.fxml"));
        Scene tableViewScene = new Scene(parent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
*/


    }

    public void teams_button_clicked(MouseEvent mouseEvent) {
        borderpaneCricket.setCenter(teamsAnchorpane);
        footballteamlist.setItems(list1);
        add_team_button1.setVisible(true);
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void tournaments_button_clicKed(MouseEvent mouseEvent) {
        loadpage("Tournaments_Page");
        add_team_button1.setVisible(false);

        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void venues_button_clicked(MouseEvent mouseEvent) {

        loadpage("Venues_Page");
        add_team_button1.setVisible(false);

        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void schedule_button_clicked(MouseEvent mouseEvent) {
        loadpage("Schedule_Page");
        add_team_button1.setVisible(false);
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }
}
