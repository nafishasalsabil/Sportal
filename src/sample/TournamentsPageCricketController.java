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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TournamentsPageCricketController implements Initializable {
    @FXML
    private AnchorPane tournamentsAnchorPane;

    @FXML
    private ListView<String> tournament_listview;
    ObservableList<String> tournament_list = FXCollections.observableArrayList();


    @FXML
    private Button add_tournaments_button;
    public void teams_button_tournament_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Teams_Page.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);

    }



    public void schedule_button_tournament_action(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Schedule_Page.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);

    }

    public void forAddingTounament(MouseEvent mouseEvent) {

    }

    public void addTournamentPage(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Add_Tournaments_Page_Cricket.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tournament_listview.setItems(tournament_list);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select tournament_name FROM tournament where sport_id=2;");


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                tournament_list.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));

            }
            //  detail_list.addAll(each_team_detail_class);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void tournament_clicked(MouseEvent mouseEvent) throws IOException {
        System.out.println(tournament_listview.getSelectionModel().getSelectedItem());

        TournamentMatchScheduleCricketController tournamentMatchScheduleController=new TournamentMatchScheduleCricketController();
        tournamentMatchScheduleController.tournament_name(tournament_listview.getSelectionModel().getSelectedItem());

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule_Cricket.fxml"));

        tournamentsAnchorPane.getChildren().setAll(pane);

    }
}
