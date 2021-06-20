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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FinishedMatchPlayerStatResultController implements Initializable {

    @FXML
    private AnchorPane anchorpane_finish;

    @FXML
    private TableView<PlayerClass> winner_stats_table;

    @FXML
    private TableColumn<PlayerClass, String> player_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> goals_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> assists_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> yellow_card_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> red_card_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> starting_11_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> substitution_on_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> substitution_off_column_winner;

    @FXML
    private TableColumn<PlayerClass, Integer> clean_sheet_column_winner;

    @FXML
    private TableView<PlayerClass> loser_stats_table;

    @FXML
    private TableColumn<PlayerClass, String> player_name_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> goals_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> assists_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> yellow_card_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> red_card_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> starting_11_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> substitution_on_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> substitution_off_column_loser;

    @FXML
    private TableColumn<PlayerClass, Integer> clean_sheet_column_loser;

    @FXML
    private Button done_button;

    ObservableList<PlayerClass> plaer_list_winner = FXCollections.observableArrayList();
    ObservableList<String> player_list_winner_player_name = FXCollections.observableArrayList();
    ObservableList<String> player_list_loser_player_name = FXCollections.observableArrayList();



    ObservableList<PlayerClass> plaer_list_loser = FXCollections.observableArrayList();

    public static int sport_id_pass,tournament_id_pass,match_id_pass1,winner_id_pass,loser_id_pass,player_id_w,player_id_l;
    public static String playerName="";
    public static int goals,assist,yellow,red,starting11,subon,suboff,goals_saved,name_player;

    public static String playerNameL="";
    public static int goalsL,assistL,yellowL,redL,starting11L,subonL,suboffL,goals_savedL,name_playerL;

    PlayerClass playerClass_winner;
    PlayerClass playerClass_loser;
    int i=0,j=0;


    @FXML
    void done_pressed(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule_See_Finished_Matches.fxml"));

        anchorpane_finish.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plaer_list_winner.clear();
        plaer_list_loser.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement1 = connection.prepareStatement("select winner_team_id,loser_team_id from game_result where sport_id=1 and match_id=? and tournament_id=? ");
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

            PreparedStatement preparedStatementstatwinner = connection.prepareStatement("select  goal_scored, assist, yellow_card, red_card, substituition_on, substituition_off, starting_xi, goal_save from stats_football where sport_id=1 and tournament_id=? and match_id=? and team_id=?");
            preparedStatementstatwinner.setInt(1,tournament_id_pass);
            preparedStatementstatwinner.setInt(2,match_id_pass1);
            preparedStatementstatwinner.setInt(3,winner_id_pass);

            System.out.println("$$$$$$$$$$$$");
            System.out.println(tournament_id_pass+" "+match_id_pass1+" "+winner_id_pass);
            ResultSet resultSetStatwinner = preparedStatementstatwinner.executeQuery();
            while(resultSetStatwinner.next()){

                goals=resultSetStatwinner.getInt("goal_scored");
                assist=resultSetStatwinner.getInt("assist");
                yellow=resultSetStatwinner.getInt("yellow_card");
                red= resultSetStatwinner.getInt("red_card") ;
                subon=resultSetStatwinner.getInt("substituition_on") ;
                suboff= resultSetStatwinner.getInt("substituition_off") ;
                starting11= resultSetStatwinner.getInt("starting_xi") ;
                goals_saved= resultSetStatwinner.getInt("goal_save") ;

                System.out.println(goals);



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
                playerClass_winner=new PlayerClass(playerName,goals,assist,yellow,red,starting11,subon,suboff,goals_saved);
                plaer_list_winner.add(playerClass_winner);


            }
            player_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, String>("player_name"));
            goals_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_scored"));
            assists_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("assist"));
            yellow_card_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("yellow_card"));
            red_card_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("red_card"));
            starting_11_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("starting_11"));
            substitution_on_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_on"));
            substitution_off_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_off"));
            clean_sheet_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_saved"));


            winner_stats_table.setItems(plaer_list_winner);




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

                goalsL = resultSetStatloser.getInt("goal_scored");
                assistL = resultSetStatloser.getInt("assist");
                yellowL = resultSetStatloser.getInt("yellow_card");
                redL = resultSetStatloser.getInt("red_card");
                subonL = resultSetStatloser.getInt("substituition_on");
                suboffL = resultSetStatloser.getInt("substituition_off");
                starting11L = resultSetStatloser.getInt("starting_xi");
                goals_savedL = resultSetStatloser.getInt("goal_save");



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
                playerClass_loser = new PlayerClass(playerNameL,goalsL,assistL,yellowL,redL,starting11L,subonL,suboffL,goals_savedL);
                plaer_list_loser.add(playerClass_loser);


            }
            System.out.println(player_list_loser_player_name);
            player_name_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, String>("player_name"));
            goals_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_scored"));
            assists_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("assist"));
            yellow_card_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("yellow_card"));
            red_card_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("red_card"));
            starting_11_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("starting_11"));
            substitution_on_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_on"));
            substitution_off_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_off"));
            clean_sheet_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_saved"));

            loser_stats_table.setItems(plaer_list_loser);




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
        System.out.println(tournament_id_pass1);
        match_id_pass1=Integer.parseInt(match_id_pass2);
//        winner_id_pass=winner_id_pass1;
//        loser_id_pass=loser_id_pass1;
    }
}
