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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.swing.text.html.ImageView;
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
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane teamsAnchorpane;
    @FXML
    private BorderPane borderpaneCricket;
    @FXML
    private Button add_team_button;
    @FXML
    private AnchorPane teamsAnchorPane;
    public static String team_name="";


    TeamNameClass teamNameClass;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teams_listview.setItems(list);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select team_name FROM team where sport_id=2;");


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                list.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));

            }
            //  detail_list.addAll(each_team_detail_class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectMSSQL cnObj = new ConnectMSSQL();



    }

    public void team_name_clicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + teams_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
            TeamNameClass teamNameClass = new TeamNameClass();
            EachTeamDetailPageForCricketController eachTeamDetailPageController = new EachTeamDetailPageForCricketController();
            eachTeamDetailPageController.teamname(teams_listview.getSelectionModel().getSelectedItem());
/*
        eachTeamDetailPageController.setTeamname(teams_listview.getSelectionModel().getSelectedItem());
*/

        loadpage("Each_Team_Detail_Page_For_Cricket");
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

  /*  public void venues_button_clicked(MouseEvent mouseEvent) {
        loadpage("Venues_Page");
        add_team_button.setVisible(false);

        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
//        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }*/

   /* public void schedule_button_clicked(MouseEvent mouseEvent) {
        loadpage("Schedule_Page");
        add_team_button.setVisible(false);
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }*/

    public void teams_button_clicked(MouseEvent mouseEvent) {
        borderpaneCricket.setCenter(teamsAnchorpane);
        teams_listview.setItems(list);
        add_team_button.setVisible(true);
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
//        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
//        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");

    }

    public void tournament_button_clicked(MouseEvent mouseEvent) {
        loadpage("Tournaments_Page_Cricket");
        add_team_button.setVisible(false);

        teams_button1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD; -fx-background-color: #ffffff; ");
        tournaments_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #BD9354;-fx-background-color: #ffffff; ");
//        schedule_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
//        venues_button_teams1.setStyle("-fx-border-width:    0px 0px 4px 0px; -fx-border-color: #DDDDDD;-fx-background-color: #ffffff; ");
    }

    public void obNavigationBackClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/menu_page.fxml"));

        cricket_anchorpane.getChildren().setAll(pane);

    }

    public void forAddingTeam(MouseEvent mouseEvent) {
        try {
           /* Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/AddTeamDialogBox.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();*/
            FXMLLoader addNewItemLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample/AddTeamDialogBox.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(addNewItemLoader.load()));
            AddTeamDialogBoxController addNewItemController = addNewItemLoader.getController();
            secondStage.showAndWait();

            Optional<String> result = addNewItemController.getNewItem();
            if (result.isPresent()) {
                System.out.println("Your name: " + result.get());
                list.add(result.get());
                teams_listview.setItems(list);

            }
        }catch (IOException ex) {
            Logger.getLogger(TeamsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void name_method(String name1) {
        team_name = name1;
        System.out.println("||||||||"+team_name);
//        list.add(team_name);
//        teams_listview.setItems(list);
            teams_listview.getItems().add(team_name);

    }

    public void tournaments_button_action_teams(ActionEvent actionEvent) {
    }

    public void venues_button_action_teams(ActionEvent actionEvent) {
    }

    public void schedule_button_teams(ActionEvent actionEvent) {
    }

    public void added(boolean b) {
        if(b=true)
        {
            teams_listview.setItems(list);

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("select team_name FROM team where sport_id=2;");


                while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                    list.add(resultSet.getString(1));
                    System.out.println(resultSet.getString(1));

                }
                //  detail_list.addAll(each_team_detail_class);


            } catch (Exception e) {
                e.printStackTrace();
            }
            ConnectMSSQL cnObj = new ConnectMSSQL();
        }
    }
}
