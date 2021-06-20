package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FinishedMatchPlayerStatController implements Initializable {

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
    private TextField goals_label_winner;

    @FXML
    private TextField assist_label_winner;

    @FXML
    private TextField yellow_card_winner;

    @FXML
    private TextField red_card_winner;

    @FXML
    private TextField starting_11_winner;

    @FXML
    private TextField sub_on_winner;

    @FXML
    private TextField sub_off_winner;

    @FXML
    private TextField goal_saved_winner;

    @FXML
    private Button save_winner_player;

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
    private TextField goals_label_loser;

    @FXML
    private TextField assist_label_loser;

    @FXML
    private TextField yellow_card_loser;

    @FXML
    private TextField red_card_loser;

    @FXML
    private TextField starting_11_loser;

    @FXML
    private TextField sub_on_loser;

    @FXML
    private TextField sub_off_loser;

    @FXML
    private TextField goal_saved_loser;

    @FXML
    private Button save_loser_player;

    @FXML
    private Button done_button;
    @FXML
    private TextField player_name_label;

    @FXML
    private HBox winnerhbox;
    int sportid=1;
    @FXML
    private TextField playerName_loser;

    @FXML
    private HBox loserhbox;


    ObservableList<PlayerClass> plaer_list_winner = FXCollections.observableArrayList();
    ObservableList<String> player_list_winner_player_name = FXCollections.observableArrayList();
    ObservableList<String> player_list_loser_player_name = FXCollections.observableArrayList();



    ObservableList<PlayerClass> plaer_list_loser = FXCollections.observableArrayList();

    public static int sport_id_pass,tournament_id_pass,match_id_pass1,winner_id_pass,loser_id_pass,player_id_w,player_id_l;
    public static String playerName="";
    public static String goals="",assist="",yellow="",red="",starting11="",subon="",suboff="",goals_saved="",name_player="";

    public static String playerNameL="";
    public static String goalsL="",assistL="",yellowL="",redL="",starting11L="",subonL="",suboffL="",goals_savedL="",name_playerL="";

    PlayerClass playerClass;
    int i=0,j=0;


    @FXML
    void done_pressed(ActionEvent event) throws IOException {
        TournamentMatchScheduleController tournamentMatchScheduleController = new TournamentMatchScheduleController();
        tournamentMatchScheduleController.pass_data(sport_id_pass,tournament_id_pass,match_id_pass1,winner_id_pass,loser_id_pass);
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule.fxml"));

        anchorpane_finish.getChildren().setAll(pane);

    }
    @FXML
    void save_loser_clicked(ActionEvent event) {
        name_playerL=playerName_loser.getText();
        goalsL= goals_label_loser.getText();
        assistL=assist_label_loser.getText();
        yellowL=yellow_card_loser.getText();
        redL = red_card_loser.getText();
        starting11L=starting_11_loser.getText();
        subonL=sub_on_loser.getText();
        suboffL=sub_off_loser.getText();
        goals_savedL=goal_saved_loser.getText();
        System.out.println(goalsL+""+assistL+""+yellowL+""+redL+""+starting11L+""+subonL+""+suboffL+""+goals_savedL);

        if(goalsL.isEmpty()||assistL.isEmpty()||yellowL.isEmpty()||redL.isEmpty()||starting11L.isEmpty()||subonL.isEmpty()||suboffL.isEmpty()||goals_savedL.isEmpty()){
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

                    playerName= resultSet1.getString("player_name");
                    playerClass=new PlayerClass(name_playerL,Integer.parseInt(goalsL),Integer.parseInt(assistL),Integer.parseInt(yellowL),Integer.parseInt(redL),Integer.parseInt(starting11L),Integer.parseInt(subonL),Integer.parseInt(suboffL),Integer.parseInt(goals_savedL));


                    plaer_list_loser.add(playerClass);


                    PreparedStatement stmt1 = connection.prepareStatement("INSERT INTO stats_football(sport_id, tournament_id, match_id, player_id, team_id, goal_scored, assist, yellow_card, red_card, substituition_on, substituition_off, starting_xi, goal_save) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    stmt1.setInt(1, sport_id_pass);
                    stmt1.setInt(2, tournament_id_pass);
                    stmt1.setInt(3,match_id_pass1);
                    stmt1.setInt(4, player_id_l);
                    stmt1.setInt(5, loser_id_pass);
                    stmt1.setInt(6, Integer.parseInt(goalsL));
                    stmt1.setInt(7, Integer.parseInt(assistL));
                    stmt1.setInt(8, Integer.parseInt(yellowL));
                    stmt1.setInt(9, Integer.parseInt(redL));
                    stmt1.setInt(10, Integer.parseInt(subonL));
                    stmt1.setInt(11, Integer.parseInt(suboffL));
                    stmt1.setInt(12, Integer.parseInt(starting11L));
                    stmt1.setInt(13, Integer.parseInt(goals_savedL));

                    stmt1.executeUpdate();


                }





                for (int i = 0; i < plaer_list_loser.size(); i++) {
                    System.out.println("dfgh");
                }

                loser_stats_table.getItems().add(playerClass);


            } catch (Exception e) {
                e.printStackTrace();
            }
            j++;
            if(j<player_list_loser_player_name.size()){
                playerName_loser.setText(player_list_loser_player_name.get(j));

            }
            else{
                loserhbox.setVisible(false);
            }

        }

    }
    @FXML
    void save_winner_clicked(ActionEvent event) {


        name_player=player_name_label.getText();
       goals= goals_label_winner.getText();
       assist=assist_label_winner.getText();
       yellow=yellow_card_winner.getText();
       red = red_card_winner.getText();
       starting11=starting_11_winner.getText();
       subon=sub_on_winner.getText();
       suboff=sub_off_winner.getText();
       goals_saved=goal_saved_winner.getText();
        System.out.println(goals+""+assist+""+yellow+""+red+""+starting11+""+subon+""+suboff+""+goals_saved);

       if(goals.isEmpty()||assist.isEmpty()||yellow.isEmpty()||red.isEmpty()||starting11.isEmpty()||subon.isEmpty()||suboff.isEmpty()||goals_saved.isEmpty()){
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

                   playerName= resultSet1.getString("player_name");
                   playerClass=new PlayerClass(name_player,Integer.parseInt(goals),Integer.parseInt(assist),Integer.parseInt(yellow),Integer.parseInt(red),Integer.parseInt(starting11),Integer.parseInt(subon),Integer.parseInt(suboff),Integer.parseInt(goals_saved));


                   plaer_list_winner.add(playerClass);


                   PreparedStatement stmt = connection.prepareStatement("INSERT INTO stats_football(sport_id, tournament_id, match_id, player_id, team_id, goal_scored, assist, yellow_card, red_card, substituition_on, substituition_off, starting_xi, goal_save) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                   stmt.setInt(1, sport_id_pass);
                   stmt.setInt(2, tournament_id_pass);
                   stmt.setInt(3,match_id_pass1);
                   stmt.setInt(4, player_id_w);
                   stmt.setInt(5, winner_id_pass);
                   stmt.setInt(6, Integer.parseInt(goals));
                   stmt.setInt(7, Integer.parseInt(assist));
                   stmt.setInt(8, Integer.parseInt(yellow));
                   stmt.setInt(9, Integer.parseInt(red));
                   stmt.setInt(10, Integer.parseInt(subon));
                   stmt.setInt(11, Integer.parseInt(suboff));
                   stmt.setInt(12, Integer.parseInt(starting11));
                   stmt.setInt(13, Integer.parseInt(goals_saved));

                   stmt.executeUpdate();


               }





               for (int i = 0; i < plaer_list_winner.size(); i++) {
                   System.out.println("dfgh");
               }

               winner_stats_table.getItems().add(playerClass);


           } catch (Exception e) {
               e.printStackTrace();
           }
           i++;
           if(i<player_list_winner_player_name.size()){
               player_name_label.setText(player_list_winner_player_name.get(i));

           }
           else{
               winnerhbox.setVisible(false);
           }

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
                playerClass=new PlayerClass(playerName);
                player_list_winner_player_name.add(playerName);
                player_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, String>("player_name"));
                goals_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_scored"));
                assists_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("assist"));
                yellow_card_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("yellow_card"));
                red_card_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("red_card"));
                starting_11_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("starting_11"));
                substitution_on_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_on"));
                substitution_off_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_off"));
                clean_sheet_column_winner.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_saved"));



            }
            System.out.println(player_list_winner_player_name);
            player_name_label.setText(player_list_winner_player_name.get(i));




            for (int i = 0; i < plaer_list_winner.size(); i++) {
                System.out.println("dfgh");
            }

//           winner_stats_table.setItems(player_list_winner_player_name);

//            winner_stats_table.getItems().add(playerClass);


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
                playerClass=new PlayerClass(playerNameL);
                player_list_loser_player_name.add(playerNameL);
                player_name_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, String>("player_name"));
                goals_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_scored"));
                assists_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("assist"));
                yellow_card_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("yellow_card"));
                red_card_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("red_card"));
                starting_11_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("starting_11"));
                substitution_on_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_on"));
                substitution_off_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_off"));
                clean_sheet_column_loser.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_saved"));




            }
            System.out.println(player_list_loser_player_name);
            playerName_loser.setText(player_list_loser_player_name.get(j));





            for (int i = 0; i < plaer_list_loser.size(); i++) {
                System.out.println("dfgh");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
