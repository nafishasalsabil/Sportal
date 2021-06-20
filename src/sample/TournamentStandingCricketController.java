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

public class TournamentStandingCricketController implements Initializable {

    @FXML
    private TableView<StandingInfoClass> tournament_standing_tableview;

    @FXML
    private TableColumn<StandingInfoClass, String> team_column;

    @FXML
    private TableColumn<StandingInfoClass, Integer> matches_played_column;

    @FXML
    private TableColumn<StandingInfoClass, Integer> won_column;



    @FXML
    private TableColumn<StandingInfoClass, Integer> lost_column;



    @FXML
    private TableColumn<StandingInfoClass, Integer> points_column;
    ObservableList<StandingInfoClass> tournament_stat_list = FXCollections.observableArrayList();

    StandingInfoClass standingInfoClass;
    public static String tname="",teamN="";
    public static int matches,won,draw,lost,goal_diff,points;
    public static int tou_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tournament_stat_list.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();

            PreparedStatement tourna_prep= connection.prepareStatement("select tournament_id from tournament where tournament_name=?");
            tourna_prep.setString(1,tname);
            ResultSet torna_set= tourna_prep.executeQuery();
            while(torna_set.next()){
                tou_id=torna_set.getInt("tournament_id");

            }

            PreparedStatement standingstatement = connection.prepareStatement("SELECT distinct  team.team_name,\n" +
                    "(SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE team.team_id= game_result.winner_team_id AND game_result.tournament_id=? ) + \n" +
                    "(SELECT COUNT(game_result.loser_team_id) FROM game_result WHERE team.team_id= game_result.loser_team_id AND game_result.tournament_id=? )as Played, \n" +
                    "(SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE team.team_id= game_result.winner_team_id AND game_result.teamA_score<>game_result.teamB_score AND  game_result.tournament_id=? ) as Won, \n" +
                    "(SELECT COUNT(game_result.loser_team_id) FROM game_result WHERE team.team_id= game_result.loser_team_id  AND  game_result.teamA_score<>game_result.teamB_score AND game_result.tournament_id=?) AS Lost,\n" +
                    "(SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE team.team_id= game_result.winner_team_id AND game_result.teamA_score<>game_result.teamB_score AND  game_result.tournament_id=? )*3 +\n" +
                    "(SELECT (SELECT COUNT(game_result.winner_team_id) FROM game_result WHERE  team.team_id= game_result.winner_team_id AND game_result.teamA_score = game_result.teamB_score AND  game_result.tournament_id=? ) + (SELECT COUNT(game_result.loser_team_id) FROM game_result WHERE  team.team_id= game_result.loser_team_id AND game_result.teamA_score = game_result.teamB_score AND  game_result.tournament_id=? )) AS PTS\n" +
                    "FROM team, game_result\n" +
                    " WHERE game_result.tournament_id=? AND team.sport_id=2 AND (team.team_id=game_result.winner_team_id OR team.team_id=game_result.loser_team_id  )\n" +
                    "ORDER BY PTS desc;");

            standingstatement.setInt(1,tou_id);
            standingstatement.setInt(2,tou_id);
            standingstatement.setInt(3,tou_id);
            standingstatement.setInt(4,tou_id);
            standingstatement.setInt(5,tou_id);
            standingstatement.setInt(6,tou_id);
            standingstatement.setInt(7,tou_id);
            standingstatement.setInt(8,tou_id);


            ResultSet standingset = standingstatement.executeQuery();
            while (standingset.next()){
                teamN=standingset.getString("team_name");
                matches=standingset.getInt("Played");
                won=standingset.getInt("Won");

                lost=standingset.getInt("Lost");

                points=standingset.getInt("PTS");


                standingInfoClass = new StandingInfoClass(teamN,matches,won,lost,points);
                tournament_stat_list.add(standingInfoClass);
            }



            team_column.setCellValueFactory(new PropertyValueFactory<StandingInfoClass, String>("nameP"));
            matches_played_column.setCellValueFactory(new PropertyValueFactory<StandingInfoClass, Integer>("matches_played"));
            won_column.setCellValueFactory(new PropertyValueFactory<StandingInfoClass, Integer>("won"));
            lost_column.setCellValueFactory(new PropertyValueFactory<StandingInfoClass, Integer>("lost"));
            points_column.setCellValueFactory(new PropertyValueFactory<StandingInfoClass, Integer>("points"));


            tournament_standing_tableview.setItems(tournament_stat_list);





        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void tname(String tournament_name1) {
        tname=tournament_name1;

    }
}
