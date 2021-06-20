package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class VenueController implements Initializable {

    @FXML
    private TableView<VenueClass> venue_table;

    @FXML
    private TableColumn<VenueClass, String> place_column;

    @FXML
    private TableColumn<VenueClass, Integer> matches_played_column;

    @FXML
    private TableColumn<VenueClass, Integer> tournaments_payed_column;

    @FXML
    private TableColumn<VenueClass, Integer> goals_scored_column;
    static ObservableList<VenueClass> venue_list = FXCollections.observableArrayList();
    public static String place="";
    public static int match,tournament_play,g;
    VenueClass venueClass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venue_list.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  CAST(venue.place as NVARCHAR(100)) as PLACE,COUNT(match_schedule.match_id) AS matches_played , COUNT(DISTINCT match_schedule.tournament_id) AS  tournaments_played, (SUM(game_result.teamA_score) + SUM(game_result.teamB_score)) AS goal_scored\n" +
                    "FROM match_schedule\n" +
                    "LEFT JOIN venue ON venue.venue_id = match_schedule.venue_id\n" +
                    "LEFT JOIN game_result ON game_result.match_id= match_schedule.match_id\n" +
                    "WHERE match_schedule.sport_id = 1\n" +
                    "GROUP BY CAST(venue.place as NVARCHAR(100))\n" +
                    "ORDER BY COUNT(match_schedule.match_id) desc;");
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                place=resultSet1.getString("PLACE");
                match=resultSet1.getInt("matches_played");
                tournament_play=resultSet1.getInt("tournaments_played");
                g=resultSet1.getInt("goal_scored");


/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/

//                detail_list.addAll(each_team_detail_class);
                venueClass=new VenueClass(place,match,tournament_play,g);
                venue_list.add(venueClass);


            }
            place_column.setCellValueFactory(new PropertyValueFactory<VenueClass, String>("place"));
            matches_played_column.setCellValueFactory(new PropertyValueFactory<VenueClass, Integer>("matches_played"));
            tournaments_payed_column.setCellValueFactory(new PropertyValueFactory<VenueClass, Integer>("tournaments"));
            goals_scored_column.setCellValueFactory(new PropertyValueFactory<VenueClass, Integer>("goals"));




            venue_table.setItems(venue_list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
