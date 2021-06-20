package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FinishedMatchPlayerStatCricketController implements Initializable {

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
    private VBox vbox_winner;

    @FXML
    private HBox winnerhbox;

    @FXML
    private TextField player_name_label;

    @FXML
    private TextField innings_label_winner;

    @FXML
    private TextField not_out_label_winner;

    @FXML
    private TextField century_winner;

    @FXML
    private TextField fifty_winner;

    @FXML
    private TextField runs_winner;

    @FXML
    private TextField runs_givennnn_winner;

    @FXML
    private TextField sixes_winner;

    @FXML
    private TextField fours_winner;

    @FXML
    private TextField wickets_winner;

    @FXML
    private TextField extras_winner;

    @FXML
    private TextField ball_palyed;

    @FXML
    private TextField balls_delivered;

    @FXML
    private Button save_winner_player;

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

    @FXML
    private VBox vbox_loser;

    @FXML
    private HBox winnerhbox1;

    @FXML
    private TextField player_name_label1;

    @FXML
    private TextField innings_label_winner1;

    @FXML
    private TextField not_out_label_winner1;

    @FXML
    private TextField century_winner1;

    @FXML
    private TextField fifty_winner1;

    @FXML
    private TextField runs_winner1;

    @FXML
    private TextField runs_givennnn_winner1;

    @FXML
    private TextField sixes_winner1;

    @FXML
    private TextField fours_winner1;

    @FXML
    private TextField wickets_winner1;

    @FXML
    private TextField extras_winner1;

    @FXML
    private TextField ball_palyed1;

    @FXML
    private TextField balls_delivered1;

    @FXML
    private Button save_loser_player1;

    @FXML
    private Button done_button;
    int i=0,j=0;
    public static int sport_id_pass,tournament_id_pass,match_id_pass1,winner_id_pass,loser_id_pass,player_id_w,player_id_l;
    ObservableList<CricketerStatClass> plaer_list_winner = FXCollections.observableArrayList();
    ObservableList<String> player_list_winner_player_name = FXCollections.observableArrayList();
    ObservableList<String> player_list_loser_player_name = FXCollections.observableArrayList();
    ObservableList<CricketerStatClass> plaer_list_loser = FXCollections.observableArrayList();

    public static String nameC,playerName="",playerNameL="";
    public static String  innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered;

    public static String nameCL;
    public static String  inningsL, not_outL, centuryL, half_centuryL, total_runL, total_run_givenL, total_sixesL, total_foursL, wicketsL, extra_runL,total_ball_playedL,total_ball_deliveredL;

    CricketerStatClass cricketerStatClass;


    @FXML
    void done_pressed(ActionEvent event) throws IOException {
        TournamentMatchScheduleCricketController tournamentMatchScheduleController = new TournamentMatchScheduleCricketController();
        tournamentMatchScheduleController.pass_data(sport_id_pass,tournament_id_pass,match_id_pass1,winner_id_pass,loser_id_pass);
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule_Cricket.fxml"));

        anchorpane_finish.getChildren().setAll(pane);
    }

    @FXML
    void save_winner_clicked(ActionEvent event) {
        nameC=player_name_label.getText();
        innings= innings_label_winner.getText();
        not_out=not_out_label_winner.getText();
        century=century_winner.getText();
        half_century = fifty_winner.getText();
        total_run=runs_winner.getText();
        total_run_given=runs_givennnn_winner.getText();
        total_sixes=sixes_winner.getText();
        total_fours=fours_winner.getText();
        wickets=wickets_winner.getText();
        extra_run=extras_winner.getText();
        total_ball_played=ball_palyed.getText();
        total_ball_delivered=balls_delivered.getText();

//        System.out.println(goals+""+assist+""+yellow+""+red+""+starting11+""+subon+""+suboff+""+goals_saved);

        if(nameC.isEmpty()||innings.isEmpty()||not_out.isEmpty()||century.isEmpty()||half_century.isEmpty()||total_run.isEmpty()||total_run_given.isEmpty()||total_sixes.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All The Fields");
            alert.showAndWait();
        }
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                String tyuh = "Team Uno-R2";
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("select player_id,player_name FROM players where team_id= ? ");
                System.out.println("Loading correctly " + winner_id_pass);
                preparedStatement.setInt(1, winner_id_pass);
                ResultSet resultSet1 = preparedStatement
                        .executeQuery();
                while (resultSet1.next()) {

                    System.out.println("team_id:" +
                            resultSet1.getInt("player_id"));

                    player_id_w= resultSet1.getInt("player_id");
                    System.out.println("team_id:" +
                            resultSet1.getString("player_name"));

                    nameCL= resultSet1.getString("player_name");
                    cricketerStatClass=new CricketerStatClass(nameC,Integer.parseInt(innings), Integer.parseInt(not_out), Integer.parseInt(century),Integer.parseInt( half_century), Integer.parseInt(total_run),Integer.parseInt( total_run_given), Integer.parseInt(total_sixes),Integer.parseInt( total_fours), Integer.parseInt(wickets),Integer.parseInt( extra_run),Integer.parseInt(total_ball_played),Integer.parseInt(total_ball_delivered));


                    plaer_list_winner.add(cricketerStatClass);


                    PreparedStatement stmt1 = connection.prepareStatement("INSERT INTO stats_cricket(sport_id, tournament_id, match_id, player_id, team_id, innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    stmt1.setInt(1, sport_id_pass);
                    stmt1.setInt(2, tournament_id_pass);
                    stmt1.setInt(3,match_id_pass1);
                    stmt1.setInt(4, player_id_w);
                    stmt1.setInt(5, winner_id_pass);
                    stmt1.setInt(6, Integer.parseInt(innings));
                    stmt1.setInt(7, Integer.parseInt(not_out));
                    stmt1.setInt(8, Integer.parseInt(century));
                    stmt1.setInt(9, Integer.parseInt(half_century));
                    stmt1.setInt(10, Integer.parseInt(total_run));
                    stmt1.setInt(11, Integer.parseInt(total_run_given));
                    stmt1.setInt(12, Integer.parseInt(total_sixes));
                    stmt1.setInt(13, Integer.parseInt(total_fours));
                    stmt1.setInt(14, Integer.parseInt(wickets));
                    stmt1.setInt(15, Integer.parseInt(extra_run));
                    stmt1.setInt(16, Integer.parseInt(total_ball_played));
                    stmt1.setInt(17, Integer.parseInt(total_ball_delivered));


                    stmt1.executeUpdate();


                }





                for (int i = 0; i < plaer_list_winner.size(); i++) {
                    System.out.println("dfgh");
                }

                player_stats_table.getItems().add(cricketerStatClass);


            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
            if(j<player_list_winner_player_name.size()){
                player_name_label.setText(player_list_winner_player_name.get(i));

            }
            else{
                vbox_winner.setVisible(false);
            }

        }

    }
    @FXML
    void save_loser_clicked(ActionEvent event) {
        nameCL=player_name_label1.getText();
        inningsL= innings_label_winner1.getText();
        not_outL=not_out_label_winner1.getText();
        centuryL=century_winner1.getText();
        half_centuryL = fifty_winner1.getText();
        total_runL=runs_winner1.getText();
        total_run_givenL=runs_givennnn_winner1.getText();
        total_sixesL=sixes_winner1.getText();
        total_foursL=fours_winner1.getText();
        wicketsL=wickets_winner1.getText();
        extra_runL=extras_winner1.getText();
        total_ball_playedL=ball_palyed1.getText();
        total_ball_deliveredL=balls_delivered1.getText();

//        System.out.println(goalsL+""+assistL+""+yellowL+""+redL+""+starting11L+""+subonL+""+suboffL+""+goals_savedL);

        if(nameCL.isEmpty()||inningsL.isEmpty()||not_outL.isEmpty()||centuryL.isEmpty()||half_centuryL.isEmpty()||total_runL.isEmpty()||total_run_givenL.isEmpty()||total_sixesL.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All The Fields");
            alert.showAndWait();
        }
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                String tyuh = "Team Uno-R2";
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("select player_id,player_name FROM players where team_id= ? ");
                System.out.println("Loading correctly " + loser_id_pass);
                preparedStatement.setInt(1, loser_id_pass);
                ResultSet resultSet1 = preparedStatement
                        .executeQuery();
                while (resultSet1.next()) {

                    System.out.println("team_id:" +
                            resultSet1.getInt("player_id"));

                    player_id_l= resultSet1.getInt("player_id");
                    System.out.println("team_id:" +
                            resultSet1.getString("player_name"));

                    nameCL= resultSet1.getString("player_name");
                    cricketerStatClass=new CricketerStatClass(nameCL,Integer.parseInt(inningsL), Integer.parseInt(not_outL), Integer.parseInt(centuryL),Integer.parseInt( half_centuryL), Integer.parseInt(total_runL),Integer.parseInt( total_run_givenL), Integer.parseInt(total_sixesL),Integer.parseInt( total_foursL), Integer.parseInt(wicketsL),Integer.parseInt( extra_runL),Integer.parseInt(total_ball_playedL),Integer.parseInt(total_ball_deliveredL));


                    plaer_list_loser.add(cricketerStatClass);


                    PreparedStatement stmt1 = connection.prepareStatement("INSERT INTO stats_cricket(sport_id, tournament_id, match_id, player_id, team_id, innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    stmt1.setInt(1, sport_id_pass);
                    stmt1.setInt(2, tournament_id_pass);
                    stmt1.setInt(3,match_id_pass1);
                    stmt1.setInt(4, player_id_l);
                    stmt1.setInt(5, loser_id_pass);
                    stmt1.setInt(6, Integer.parseInt(inningsL));
                    stmt1.setInt(7, Integer.parseInt(not_outL));
                    stmt1.setInt(8, Integer.parseInt(centuryL));
                    stmt1.setInt(9, Integer.parseInt(half_centuryL));
                    stmt1.setInt(10, Integer.parseInt(total_runL));
                    stmt1.setInt(11, Integer.parseInt(total_run_givenL));
                    stmt1.setInt(12, Integer.parseInt(total_sixesL));
                    stmt1.setInt(13, Integer.parseInt(total_foursL));
                    stmt1.setInt(14, Integer.parseInt(wicketsL));
                    stmt1.setInt(15, Integer.parseInt(extra_runL));
                    stmt1.setInt(16, Integer.parseInt(total_ball_playedL));
                    stmt1.setInt(17, Integer.parseInt(total_ball_deliveredL));


                    stmt1.executeUpdate();


                }





                for (int i = 0; i < plaer_list_loser.size(); i++) {
                    System.out.println("dfgh");
                }

                player_stats_table1.getItems().add(cricketerStatClass);


            } catch (Exception e) {
                e.printStackTrace();
            }
            j++;
            if(j<player_list_loser_player_name.size()){
                player_name_label1.setText(player_list_loser_player_name.get(j));

            }
            else{
                vbox_loser.setVisible(false);
            }

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select player_id,player_name FROM players where team_id= ? ");
            System.out.println("Loading correctly " + winner_id_pass);
            preparedStatement.setInt(1, winner_id_pass);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                System.out.println("team_id:" +
                        resultSet1.getInt("player_id"));

                System.out.println("team_id:" +
                        resultSet1.getString("player_name"));

                playerName= resultSet1.getString("player_name");
                cricketerStatClass=new CricketerStatClass(playerName);
                player_list_winner_player_name.add(playerName);


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





            }
            System.out.println(player_list_winner_player_name);
            player_name_label.setText(player_list_winner_player_name.get(i));




            for (int i = 0; i < plaer_list_winner.size(); i++) {
                System.out.println("dfgh");
            }


            PreparedStatement preparedStatement1 = connection.prepareStatement("select player_id,player_name FROM players where team_id= ? ");
            System.out.println("Loading correctly " + loser_id_pass);
            preparedStatement1.setInt(1, loser_id_pass);
            ResultSet resultSet2 = preparedStatement1
                    .executeQuery();
            while (resultSet2.next()) {

                System.out.println("team_id:" +
                        resultSet2.getInt("player_id"));

                System.out.println("team_id:" +
                        resultSet2.getString("player_name"));

                playerNameL= resultSet2.getString("player_name");
                cricketerStatClass=new CricketerStatClass(playerNameL);
                player_list_loser_player_name.add(playerNameL);


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



            }
            System.out.println(player_list_loser_player_name);
            player_name_label1.setText(player_list_loser_player_name.get(j));





            for (int i = 0; i < plaer_list_loser.size(); i++) {
                System.out.println("dfgh");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void pass_info(int sportid, int tournament_id, int match_id_pass, int winner_id, int loser_id) {
        sport_id_pass=sportid;
        tournament_id_pass=tournament_id;
        match_id_pass1=match_id_pass;
        winner_id_pass=winner_id;
        loser_id_pass=loser_id;
        System.out.println(sport_id_pass+""+tournament_id_pass+""+match_id_pass1+""+winner_id_pass+""+loser_id_pass);
    }
}
