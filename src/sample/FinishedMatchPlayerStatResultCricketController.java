package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FinishedMatchPlayerStatResultCricketController implements Initializable {

    @FXML
    private AnchorPane anchorpane_finish;

    @FXML
    private TableView<CricketerStatClass> player_stats_table;

    @FXML
    private TableColumn<CricketerStatClass, String> competition_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> innings_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> notout_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> century_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> fifty_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> runs_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> runs_given_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> sixes_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> fours_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> wicket_column;

    @FXML
    private TableColumn<CricketerStatClass, Integer> extra_col;

    @FXML
    private TableColumn<CricketerStatClass, Integer> ball_played_col;

    @FXML
    private TableColumn<CricketerStatClass, Integer> balls_delivered_col;


    @FXML
    private TableView<CricketerStatClass> player_stats_table1;

    @FXML
    private TableColumn<CricketerStatClass, String> competition_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> innings_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> notout_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> century_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> fifty_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> runs_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> runs_given_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> sixes_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> fours_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> wicket_column1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> extra_col1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> ball_played_col1;

    @FXML
    private TableColumn<CricketerStatClass, Integer> balls_delivered_col1;


    int i=0,j=0;
    public static int sport_id_pass,tournament_id_pass,match_id_pass1,winner_id_pass,loser_id_pass,player_id_w,player_id_l;
    ObservableList<CricketerStatClass> plaer_list_winner = FXCollections.observableArrayList();

    ObservableList<CricketerStatClass> plaer_list_loser = FXCollections.observableArrayList();

    public static String nameC,playerName="",playerNameL="";
    public static int  innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered;

    public static String nameCL;
    public static int  inningsL, not_outL, centuryL, half_centuryL, total_runL, total_run_givenL, total_sixesL, total_foursL, wicketsL, extra_runL,total_ball_playedL,total_ball_deliveredL;

    CricketerStatClass cricketerStatClassw;
    CricketerStatClass cricketerStatClassl;






    @FXML
    void done_pressed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule_See_Finished_Matches_Cricket.fxml"));

        anchorpane_finish.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plaer_list_winner.clear();
        plaer_list_loser.clear();
        System.out.println("WERTYUIO");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement1 = connection.prepareStatement("select winner_team_id,loser_team_id from game_result where sport_id=2 and match_id=? and tournament_id=? ");
            preparedStatement1.setInt(1,match_id_pass1);
            preparedStatement1.setInt(2,tournament_id_pass);

            System.out.println(match_id_pass1);
            System.out.println(tournament_id_pass);
            ResultSet resultSet10 = preparedStatement1.executeQuery();
            while(resultSet10.next()){
                winner_id_pass = resultSet10.getInt("winner_team_id");
                loser_id_pass=resultSet10.getInt("loser_team_id");
                System.out.println(winner_id_pass+" "+loser_id_pass);
            }

            PreparedStatement preparedStatementstatwinner = connection.prepareStatement("select   player_id, team_id, innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered from stats_cricket where sport_id=2 and tournament_id=? and match_id=? and team_id=?");
            preparedStatementstatwinner.setInt(1,tournament_id_pass);
            preparedStatementstatwinner.setInt(2,match_id_pass1);
            preparedStatementstatwinner.setInt(3,winner_id_pass);

            //innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered
            System.out.println("$$$$$$$$$$$$");
            System.out.println(tournament_id_pass+" "+match_id_pass1+" "+winner_id_pass);
            ResultSet resultSetStatwinner = preparedStatementstatwinner.executeQuery();
            while(resultSetStatwinner.next()){

                innings=resultSetStatwinner.getInt("innings");
                not_out=resultSetStatwinner.getInt("not_out");
                century=resultSetStatwinner.getInt("century");
                half_century= resultSetStatwinner.getInt("half_century") ;
                total_run=resultSetStatwinner.getInt("total_run") ;
                total_run_given= resultSetStatwinner.getInt("total_run_given") ;
                total_sixes= resultSetStatwinner.getInt("total_sixes") ;
                total_fours= resultSetStatwinner.getInt("total_fours") ;
                wickets= resultSetStatwinner.getInt("wickets") ;
                extra_run= resultSetStatwinner.getInt("extra_run") ;
                total_ball_played= resultSetStatwinner.getInt("total_ball_played") ;
                total_ball_delivered= resultSetStatwinner.getInt("total_ball_delivered") ;

                System.out.println(innings);



            }

            PreparedStatement preparedStatementw = connection.prepareStatement("select player_id,player_name FROM players where team_id= ? ");
            System.out.println("Loading correctly " + winner_id_pass);
            preparedStatementw.setInt(1, winner_id_pass);
            ResultSet resultSet1 = preparedStatementw
                    .executeQuery();
            while (resultSet1.next()) {

                System.out.println("team_id:" +
                        resultSet1.getInt("player_id"));

                System.out.println("team_id:" +
                        resultSet1.getString("player_name"));
                playerName= resultSet1.getString("player_name");
                cricketerStatClassw=new CricketerStatClass(playerName,innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered);
                plaer_list_winner.add(cricketerStatClassw);


            }
            competition_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, String>("nameC"));
            innings_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("innings"));
            notout_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("not_out"));
            century_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("century"));
            fifty_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("half_century"));
            runs_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_run"));
            runs_given_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_run_given"));
            sixes_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_sixes"));
            fours_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_fours"));
            wicket_column.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("wickets"));
            extra_col.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("extra_run"));
            ball_played_col.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_ball_played"));
            balls_delivered_col.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_ball_delivered"));

            player_stats_table.setItems(plaer_list_winner);




//            System.out.println(player_list_winner_player_name);




            for (int i = 0; i < plaer_list_winner.size(); i++) {
                System.out.println("dfgh");
            }

//           winner_stats_table.setItems(player_list_winner_player_name);

//            winner_stats_table.getItems().add(playerClass);


            PreparedStatement preparedStatementstatloser = connection.prepareStatement("select  goal_scored, assist, yellow_card, red_card, substituition_on, substituition_off, starting_xi, goal_save from stats_football where sport_id=1 and tournament_id=? and match_id=? and team_id=?");
            preparedStatementstatloser.setInt(1,tournament_id_pass);
            preparedStatementstatloser.setInt(2,match_id_pass1);
            preparedStatementstatloser.setInt(3,loser_id_pass);

            ResultSet resultSetStatloser = preparedStatementstatwinner.executeQuery();
            while(resultSetStatloser.next()) {

                inningsL=resultSetStatloser.getInt("innings");
                not_outL=resultSetStatloser.getInt("not_out");
                centuryL=resultSetStatloser.getInt("century");
                half_centuryL= resultSetStatloser.getInt("half_century") ;
                total_runL=resultSetStatloser.getInt("total_run") ;
                total_run_givenL= resultSetStatloser.getInt("total_run_given") ;
                total_sixesL= resultSetStatloser.getInt("total_sixes") ;
                total_foursL= resultSetStatloser.getInt("total_fours") ;
                wicketsL= resultSetStatloser.getInt("wickets") ;
                extra_runL= resultSetStatloser.getInt("extra_run") ;
                total_ball_playedL= resultSetStatloser.getInt("total_ball_played") ;
                total_ball_deliveredL= resultSetStatloser.getInt("total_ball_delivered") ;

                System.out.println(inningsL);



            }

            PreparedStatement preparedStatement = connection.prepareStatement("select player_id,player_name FROM players where team_id= ? ");
            System.out.println("Loading correctly " + loser_id_pass);
            preparedStatement.setInt(1, loser_id_pass);
            ResultSet resultSet2 = preparedStatement
                    .executeQuery();
            while (resultSet2.next()) {

                System.out.println("team_id:" +
                        resultSet2.getInt("player_id"));

                System.out.println("team_id:" +
                        resultSet2.getString("player_name"));


                playerNameL = resultSet2.getString("player_name");
                cricketerStatClassl = new CricketerStatClass(playerNameL,inningsL, not_outL, centuryL, half_centuryL, total_runL, total_run_givenL, total_sixesL, total_foursL, wicketsL, extra_runL,total_ball_playedL,total_ball_deliveredL);
                plaer_list_loser.add(cricketerStatClassl);


            }

            competition_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, String>("nameC"));
            innings_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("innings"));
            notout_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("not_out"));
            century_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("century"));
            fifty_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("half_century"));
            runs_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_run"));
            runs_given_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_run_given"));
            sixes_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_sixes"));
            fours_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_fours"));
            wicket_column1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("wickets"));
            extra_col1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("extra_run"));
            ball_played_col1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_ball_played"));
            balls_delivered_col1.setCellValueFactory(new PropertyValueFactory<CricketerStatClass, Integer>("total_ball_delivered"));

            player_stats_table1.setItems(plaer_list_loser);






            for (int i = 0; i < plaer_list_loser.size(); i++) {
                System.out.println("dfgh");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void pass_data(int sport_id_pass1, int tournament_id_pass1, String match_id_pass2, int winner_id_pass1, int loser_id_pass1) {
        sport_id_pass=sport_id_pass1;
        tournament_id_pass=tournament_id_pass1;
        System.out.println(tournament_id_pass1+"00000000000");
        match_id_pass1=Integer.parseInt(match_id_pass2);
//        winner_id_pass=winner_id_pass1;
//        loser_id_pass=loser_id_pass1;
    }
}
