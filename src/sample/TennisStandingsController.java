package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.security.PublicKey;
import java.sql.*;
import java.util.ResourceBundle;

public class TennisStandingsController implements Initializable {
    @FXML
    private TableView<TennisStandindClass> stat_table;
    @FXML
    private TableColumn<TennisStandindClass, String> team_col;

    @FXML
    private TableColumn<TennisStandindClass, Integer> played_col;

    @FXML
    private TableColumn<TennisStandindClass, Integer> won_col;

    @FXML
    private TableColumn<TennisStandindClass, Integer> lost_col;

    @FXML
    private TableColumn<TennisStandindClass, Integer> pts_col;
    ObservableList<TennisStandindClass> tournament_list = FXCollections.observableArrayList();
    TennisStandindClass tennisStandindClass;
    public static String tname="";
    public static int a,b,c,d;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();




            PreparedStatement preparedStatementstats = connection.prepareStatement("SELECT distinct team.team_name,\n" +
                    "(SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE team.team_id= game_result.winner_team_id AND game_result.tournament_id=21 ) + \n" +
                    "(SELECT COUNT(game_result.loser_team_id) FROM game_result WHERE team.team_id= game_result.loser_team_id AND game_result.tournament_id=21 )as Played, \n" +
                    "(SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE team.team_id= game_result.winner_team_id AND game_result.teamA_score<>game_result.teamB_score AND  game_result.tournament_id=21 ) as Won, \n" +
                    "(SELECT COUNT(game_result.loser_team_id) FROM game_result WHERE team.team_id= game_result.loser_team_id  AND  game_result.teamA_score<>game_result.teamB_score AND game_result.tournament_id=21) AS LOST,\n" +
                    "(SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE team.team_id= game_result.winner_team_id AND game_result.teamA_score<>game_result.teamB_score AND  game_result.tournament_id=21 )*3 +\n" +
                    "(SELECT (SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE  team.team_id= game_result.winner_team_id AND game_result.teamA_score = game_result.teamB_score AND  game_result.tournament_id=21 ) + (SELECT COUNT(game_result.loser_team_id) FROM game_result WHERE  team.team_id= game_result.loser_team_id AND game_result.teamA_score = game_result.teamB_score AND  game_result.tournament_id=21 )) AS PTS\n" +
                    "FROM team, game_result\n" +
                    "WHERE game_result.tournament_id=21 AND team.sport_id=4 AND (team.team_id=game_result.winner_team_id OR team.team_id=game_result.loser_team_id  )\n" +
                    "\n" +
                    "ORDER BY PTS desc;");



            ResultSet resultSetStats=preparedStatementstats.executeQuery();
            while(resultSetStats.next()){

//                System.out.println( resultSetStats.getString("point_scored"));
                tname=resultSetStats.getString("team_name");
                a = resultSetStats.getInt("Played");
                b=resultSetStats.getInt("Won");
                b=resultSetStats.getInt("Lost");
                b=resultSetStats.getInt("PTS");


                tennisStandindClass = new TennisStandindClass(tname,a,b,c,d);

                tournament_list.add(tennisStandindClass);
//                System.out.println(player_stat_list.size());




            }


            team_col.setCellValueFactory(new PropertyValueFactory<TennisStandindClass, String>("team"));

            played_col.setCellValueFactory(new PropertyValueFactory<TennisStandindClass, Integer>("played"));
            won_col.setCellValueFactory(new PropertyValueFactory<TennisStandindClass, Integer>("won"));

            lost_col.setCellValueFactory(new PropertyValueFactory<TennisStandindClass, Integer>("lost"));

            pts_col.setCellValueFactory(new PropertyValueFactory<TennisStandindClass, Integer>("pts"));

            stat_table.setItems(tournament_list);




        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
