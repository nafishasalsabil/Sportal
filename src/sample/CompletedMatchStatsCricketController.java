package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CompletedMatchStatsCricketController implements Initializable {

    @FXML
    private AnchorPane match_stat_anchorpane;


    @FXML
    private ComboBox winnercombo;

    @FXML
    private ComboBox  losercombo;

    @FXML
    private TextField teamAscore;

    @FXML
    private TextField teamBscore;
    public static String  team_A_score ="",team_B_score="",final_result="",matchcode="matchcode",winner_team="",loser_team="",teamA_name="",teamB_name="";

    int sportid=2;

    @FXML
    private TextField result;
    ObservableList<String> teamwinner_list = FXCollections.observableArrayList();
    ObservableList<String> teamloser_list = FXCollections.observableArrayList();
    @FXML
    private Button submit;
    public static int match_id_pass,teamAid,teamBid,tournament_id,winner_id,loser_id;
    public void pass_match_id(String match_id_retrieve) {
        System.out.println(match_id_retrieve);
        match_id_pass=Integer.parseInt(match_id_retrieve);
    }

    @FXML
    void addButtonClicked(MouseEvent event) throws IOException {
        team_A_score=teamAscore.getText();
        team_B_score=teamBscore.getText();
        final_result=result.getText();
        System.out.println(team_A_score);

        if(winner_team.equals(teamA_name))
        {
            winner_id=teamAid;
            loser_id=teamBid;
        }
        else{
            winner_id=teamBid;
            loser_id=teamAid;
        }



        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();


            System.out.println("match id "+match_id_pass);
            PreparedStatement preparedStatementresult = connection.prepareStatement("INSERT INTO game_result(sport_id,  tournament_id, match_id, winner_team_id, loser_team_id, teamA_score, teamB_score, final_score, game_result_code) VALUES(?,?,?,?,?,?,?,?,?); ");

            System.out.println("enter statement");
            preparedStatementresult.setInt(1,sportid);
            preparedStatementresult.setInt(2,tournament_id);
            preparedStatementresult.setInt(3,match_id_pass);
            preparedStatementresult.setInt(4,winner_id);
            preparedStatementresult.setInt(5,loser_id);
            preparedStatementresult.setInt(6, Integer.parseInt(team_A_score));
            preparedStatementresult.setInt(7, Integer.parseInt(team_B_score));
            preparedStatementresult.setString(8,final_result);
            preparedStatementresult.setString(9,matchcode);



            preparedStatementresult
                    .executeUpdate();
            System.out.println("execute");





        } catch (Exception e) {
            e.printStackTrace();
        }
        FinishedMatchPlayerStatCricketController finishedMatchPlayerStatController = new FinishedMatchPlayerStatCricketController();
        finishedMatchPlayerStatController.pass_info(sportid,tournament_id,match_id_pass,winner_id,loser_id);

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Finished_Match_Player_Stat_Cricket.fxml"));

        match_stat_anchorpane.getChildren().setAll(pane);

    }
    @FXML
    void loserselected(ActionEvent event) {
        System.out.println(losercombo.getSelectionModel().getSelectedItem().toString());
        loser_team=losercombo.getSelectionModel().getSelectedItem().toString();

        if(winnercombo.getSelectionModel().isEmpty())
        {
            teamwinner_list.clear();
            for(int i=0;i<teamloser_list.size();i++){
                if(!(teamloser_list.get(i).equals(losercombo.getSelectionModel().getSelectedItem().toString()))){
                    teamwinner_list.add(teamloser_list.get(i));
                }
            }
            winnercombo.setItems(teamwinner_list);
        }


    }

    @FXML
    void winnerselected(ActionEvent event) {
        System.out.println(winnercombo.getSelectionModel().getSelectedItem().toString());
        winner_team=winnercombo.getSelectionModel().getSelectedItem().toString();
        if(losercombo.getSelectionModel().isEmpty()){
            teamloser_list.clear();
            for(int i=0;i<teamwinner_list.size();i++){
                if(!(teamwinner_list.get(i).equals(winnercombo.getSelectionModel().getSelectedItem().toString()))){
                    teamloser_list.add(teamwinner_list.get(i));
                }
            }
            losercombo.setItems(teamloser_list);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        winnercombo.setItems(teamwinner_list);
        losercombo.setItems(teamloser_list);


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();


            System.out.println("match id "+match_id_pass);
            PreparedStatement preparedStatementmatchid = connection.prepareStatement("select teamA_id,teamB_id,sport_id,tournament_id FROM match_schedule where match_id=? ;");
            preparedStatementmatchid.setInt(1,match_id_pass);


            ResultSet resultSetmatchid = preparedStatementmatchid
                    .executeQuery();

            while (resultSetmatchid.next())
            {
                tournament_id =resultSetmatchid.getInt("tournament_id");
                teamAid=resultSetmatchid.getInt("teamA_id");
                teamBid=resultSetmatchid.getInt("teamB_id");
                System.out.println(teamAid);
                System.out.println(teamBid);

                PreparedStatement preparedStatement_teamname = connection.prepareStatement("select team_name FROM team where team_id=? ;");
                preparedStatement_teamname.setInt(1,teamAid);
                ResultSet resultSetteamA = preparedStatement_teamname.executeQuery();
                while(resultSetteamA.next())
                {
                    System.out.println(resultSetteamA.getString("team_name"));
                    teamwinner_list.add(resultSetteamA.getString("team_name"));
                    teamloser_list.add(resultSetteamA.getString("team_name"));

                    teamA_name=(resultSetteamA.getString("team_name"));
                }

                PreparedStatement preparedStatement_teamname2 = connection.prepareStatement("select team_name FROM team where team_id=? ;");
                preparedStatement_teamname2.setInt(1,teamBid);
                ResultSet resultSetteamB = preparedStatement_teamname2.executeQuery();
                while(resultSetteamB.next())
                {
                    System.out.println(resultSetteamB.getString("team_name"));
                    teamwinner_list.add(resultSetteamB.getString("team_name"));
                    teamloser_list.add(resultSetteamB.getString("team_name"));
                    teamB_name=(resultSetteamB.getString("team_name"));
                }

            }





        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
