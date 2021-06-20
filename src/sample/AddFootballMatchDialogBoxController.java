package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddFootballMatchDialogBoxController implements Initializable {
    @FXML
    private AnchorPane addmatchanchorpane;
    @FXML
    private ComboBox teamAcombo;

    @FXML
    private ComboBox teamBcombo;

    @FXML
    private DatePicker matchdatepicker;

    @FXML
    private TextField venuefield;

    @FXML
    private TextField matchtimefiled;

    @FXML
    private TextField refereefield;

    @FXML
    private Button cancelbutton;

    @FXML
    private Button addmatchbutton;

    @FXML
    private TextField facilitatorfield;
    public static int i=0;
    public static int no_of_matches;
    ObservableList<String> match_list = FXCollections.observableArrayList();
    public static String teamA = "";
    public static String teamB = "";
    public static int teamAid, temBid, tournament_id, sport_id = 1;
    public static String tournament_name_pass = "";
    public static String tournament_type_pass = "";
    public static String match_due = "Due";

    ObservableList<String> teamA_list = FXCollections.observableArrayList();
    ObservableList<String> teamB_list = FXCollections.observableArrayList();

    static ObservableList<String> selected_list_recieved = FXCollections.observableArrayList();
    @FXML
    void addButtonClicked(MouseEvent event) throws IOException {

        i++;
        String mtchtime = matchtimefiled.getText();
        LocalDate localDate = matchdatepicker.getValue();
        String tournament_date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();


            PreparedStatement preparedStatement = connection.prepareStatement("select team_id FROM team where team_name= ? ");
            System.out.println("Loading correctly " + teamA);
            preparedStatement.setString(1, teamA);
            ResultSet resultSet = preparedStatement
                    .executeQuery();


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                System.out.println(resultSet.getString("team_id"));
                teamAid = Integer.parseInt(resultSet.getString("team_id"));

            }
            PreparedStatement preparedStatement1 = connection.prepareStatement("select team_id FROM team where team_name= ? ");
            System.out.println("Loading correctly " + teamB);
            preparedStatement1.setString(1, teamB);
            ResultSet resultSet1 = preparedStatement1
                    .executeQuery();


            while (resultSet1.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                System.out.println(resultSet1.getString("team_id"));
                temBid = Integer.parseInt(resultSet1.getString("team_id"));

            }
            System.out.println(tournament_name_pass+"000000000000");
            PreparedStatement preparedStatement2 = connection.prepareStatement("select tournament_id FROM tournament where tournament_name= ? ");
            System.out.println("Loading correctly " + tournament_name_pass);
            preparedStatement2.setString(1, tournament_name_pass);
            ResultSet resultSet2 = preparedStatement2
                    .executeQuery();


            while (resultSet2.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                System.out.println(resultSet2.getString("tournament_id"));
                tournament_id = Integer.parseInt(resultSet2.getString("tournament_id"));

            }


            PreparedStatement stmt =
                    connection.prepareStatement("insert into match_schedule (teamA_id, teamB_id, sport_id, tournament_id,referee_id, facilitator_id, venue_id, match_date, match_time, time_end, match_type, match_code, match_status) values (?, ?,?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, teamAid);
            stmt.setInt(2, temBid);
            stmt.setInt(3, sport_id);
            stmt.setInt(4, tournament_id);
            stmt.setInt(5, sport_id);
            stmt.setInt(6, sport_id);
            stmt.setInt(7, sport_id);
            stmt.setString(8, tournament_date);
            stmt.setString(9, mtchtime);
            stmt.setString(10, mtchtime);
            stmt.setString(11, tournament_type_pass);
            stmt.setString(12, tournament_type_pass);
            stmt.setString(13, match_due);


            TournamentMatchScheduleController tournamentMatchScheduleController = new TournamentMatchScheduleController();
            tournamentMatchScheduleController.data_pass(teamA, teamB, mtchtime, tournament_date,sport_id,tournament_name_pass);



            stmt.executeUpdate();


            PreparedStatement preparedStatement_matchid_retrieve = connection.prepareStatement("select match_id FROM match_schedule where teamA_id =? and teamB_id=? and sport_id=? and tournament_id=? ; ");
            System.out.println("Loading correctly " + tournament_name_pass);
            preparedStatement_matchid_retrieve.setInt(1, teamAid);
            preparedStatement_matchid_retrieve.setInt(2, temBid);
            preparedStatement_matchid_retrieve.setInt(3, sport_id);
            preparedStatement_matchid_retrieve.setInt(4, tournament_id);

          /*  preparedStatement_matchid_retrieve.setString(5, tournament_date);
            preparedStatement_matchid_retrieve.setString(6, mtchtime);*/


            ResultSet resultSet3_match_retrieve = preparedStatement_matchid_retrieve
                    .executeQuery();


            while (resultSet3_match_retrieve.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                System.out.println(resultSet3_match_retrieve.getString("match_id"));

                TournamentMatchScheduleController tournamentMatchScheduleController1 = new TournamentMatchScheduleController();
                tournamentMatchScheduleController1.pass_match_id(Integer.parseInt(resultSet3_match_retrieve.getString("match_id")));


//                tournament_id = Integer.parseInt(resultSet3_match_retrieve.getString("match_id"));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        if(i==no_of_matches)
        {
            TournamentMatchScheduleController tournamentMatchScheduleController = new TournamentMatchScheduleController();
            tournamentMatchScheduleController.added_match_number(i);
            AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule.fxml"));

            addmatchanchorpane.getChildren().setAll(pane);
            i=0;
        }




    }

    @FXML
    void onCancelClicked(MouseEvent event) throws IOException {

        TournamentMatchScheduleController tournamentMatchScheduleController = new TournamentMatchScheduleController();
        tournamentMatchScheduleController.added_match_number(i);
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule.fxml"));

        addmatchanchorpane.getChildren().setAll(pane);

    }

    public void teamAselected(ActionEvent actionEvent) {
        System.out.println(teamAcombo.getSelectionModel().getSelectedItem().toString());
        teamA = teamAcombo.getSelectionModel().getSelectedItem().toString();

       /* teamB_list.clear();
        for(int i=0;i<teamA_list.size();i++){
            if(!(teamA_list.get(i).equals(teamA))){
                teamB_list.add(teamA_list.get(i));
            }
        }
*/


    }

    public void teamBselected(ActionEvent actionEvent) {
        System.out.println(teamBcombo.getSelectionModel().getSelectedItem().toString());
        teamB = teamBcombo.getSelectionModel().getSelectedItem().toString();

       /* teamA_list.clear();
        for(int i=0;i<teamA_list.size();i++){
            if(!(teamB_list.get(i).equals(teamB))){
                teamA_list.add(teamB_list.get(i));
            }
        }*/

    }

    public void tournament_name_pass(String tournament_name1) {
        tournament_name_pass = tournament_name1;
        System.out.println(tournament_name_pass+"  PASSING TO DIALOG");
    }

    public void tournament_type_pass(String tournament_type1) {
        tournament_type_pass = tournament_type1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(selected_list_recieved+"$$$$$$$$$$");
        teamAcombo.setItems(teamA_list);
        teamBcombo.setItems(teamB_list);
        test();

       /* teamAcombo.getItems().add(teamA_list);
        teamBcombo.getItems().add(teamB_list);
*/
       /* System.out.println(selected_list_recieved);
        System.out.println(teamA_list);
        System.out.println(teamB_list);
        teamB_list = selected_list_recieved;
        teamA_list=selected_list_recieved;*/
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();

            System.out.println(selected_list_recieved);
            PreparedStatement preparedStatement4 = connection.prepareStatement("select team_name FROM team ");
            ResultSet resultSet4 = preparedStatement4
                    .executeQuery();
            while (resultSet4.next()) {
                teamA_list.add(resultSet4.getString("team_name"));
                System.out.println(teamA_list);
            }

            PreparedStatement preparedStatement3 = connection.prepareStatement("select team_name FROM team ");
            ResultSet resultSet3 = preparedStatement3
                    .executeQuery();
            while (resultSet3.next()) {
                teamB_list.add(resultSet3.getString("team_name"));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tournament_match_number(int number_of_matches) {
        no_of_matches=number_of_matches;
        System.out.println("number of matches will be "+no_of_matches);
    }

    public void selected_team_list(ObservableList<String> selected_list_recieve) {
        selected_list_recieved=selected_list_recieve;
        System.out.println(selected_list_recieved+"*******");

        teamB_list = selected_list_recieved;
        teamA_list=selected_list_recieved;

       /* teamAcombo.setItems(teamA_list);
        teamBcombo.setItems(teamB_list);
*/
        System.out.println(teamA_list +"----------------");
        System.out.println(teamB_list+"|||||||||||||");
    }
    public void test(){
        System.out.println(selected_list_recieved+"*******");
        System.out.println(teamA_list +"----------------");
        System.out.println(teamB_list+"|||||||||||||");

    }
}
