package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TournamentMatchScheduleSeeFinishedMatchesController implements Initializable {

    @FXML
    private AnchorPane match_schedule_anchorpane;

    @FXML
    private ListView<MatchScheduleClass> schedule_listview_finished_match;

    @FXML
    private Button back_button;
    public static String status_of_match="";
    public static int match_id_retrieve;

    ObservableList<MatchScheduleClass> data = FXCollections.observableArrayList();

    static ObservableList<String> match_id_list = FXCollections.observableArrayList();
    public static int number_of_matches2,tournament_id1,teamA_id1,teamB_id1;
    public static String tournament_name2="";
    public static String team_nameA="";
    public static String team_nameB="";
    static ObservableList<String> all_match_A = FXCollections.observableArrayList();
    static ObservableList<String> all_match_B = FXCollections.observableArrayList();

    public static String tournament_type2="";

    public static String tournament_date1="";
    public static int venue_id1;
    public static int facilitator_id1;
    public static int referee_id1;
    public static String match_date=""; public static String match_time="";
    public static int added_match_no1;
    public static int remaining_matches;
    public static int id_of_match;
    int count=0;
    static ObservableList<String> selected_list_recieve1 = FXCollections.observableArrayList();
    public static int sport_id_pass1,tournament_id_pass1,match_id_pass1,winner_id_pass1,loser_id_pass1;



    @FXML
    void back_button_pressed(ActionEvent event) throws IOException {

        TournamentMatchScheduleController tournamentMatchScheduleController=new TournamentMatchScheduleController();
        tournamentMatchScheduleController.tournament_name(tournament_name2);
        tournamentMatchScheduleController.tournament_type_pass(tournament_type2);
        tournamentMatchScheduleController.match_number(number_of_matches2);
        tournamentMatchScheduleController.selected_team_list_pass(selected_list_recieve1);
        tournamentMatchScheduleController.data_pass(team_nameA,team_nameB,match_time,match_date,venue_id1,tournament_name2);


        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule.fxml"));

        match_schedule_anchorpane.getChildren().setAll(pane);

    }

    @FXML
    void match_clicked(MouseEvent event) throws IOException {

            schedule_listview_finished_match.getSelectionModel().getSelectedIndex();
            CompletedMatchStatsController completedMatchStatsController = new CompletedMatchStatsController();
            completedMatchStatsController.pass_match_id(match_id_list.get( schedule_listview_finished_match.getSelectionModel().getSelectedIndex()));
            FinishedMatchPlayerStatResultController finishedMatchPlayerStatResultController=new FinishedMatchPlayerStatResultController();
            finishedMatchPlayerStatResultController.pass_data(sport_id_pass1,tournament_id1,match_id_list.get( schedule_listview_finished_match.getSelectionModel().getSelectedIndex()),winner_id_pass1,loser_id_pass1);

            AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Finished_Match_Player_Stat_Result.fxml"));

            match_schedule_anchorpane.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schedule_listview_finished_match.setItems(data);
        schedule_listview_finished_match.setCellFactory(new Callback<ListView<MatchScheduleClass>, ListCell<MatchScheduleClass>>() {
            @Override
            public ListCell<MatchScheduleClass> call(ListView<MatchScheduleClass> param) {
                return new TournamentMatchScheduleSeeFinishedMatchesController.CustomListCell();
            }

        });

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();

//            data.add(new MatchScheduleClass(team_nameA,team_nameB,match_date,match_time,Integer.toString(venue_id),Integer.toString(facilitator_id),Integer.toString(referee_id)));

            System.out.println("printing data list   "+data);
            System.out.println(tournament_name2);
            PreparedStatement preparedStatement_tournament = connection.prepareStatement("select tournament_id FROM tournament where tournament_name=? and sport_id=1;");
            preparedStatement_tournament.setString(1,tournament_name2);
            ResultSet resultSet1 = preparedStatement_tournament
                    .executeQuery();

            while(resultSet1.next()){

                System.out.println(resultSet1.getString("tournament_id"));
                tournament_id1=Integer.parseInt(resultSet1.getString("tournament_id"));
                System.out.println(tournament_id1);
//                tournament_id1=Integer.parseInt(resultSet1.getString("tournament_id"));
                PreparedStatement preparedStatement_match = connection.prepareStatement("select match_date,match_time,teamA_id,teamB_id,match_status,match_id FROM match_schedule where tournament_id=? and match_status='Finished';");
                preparedStatement_match.setInt(1,tournament_id1);
                ResultSet resultSetmatch = preparedStatement_match
                        .executeQuery();

                while(resultSetmatch.next()){

                    status_of_match=resultSetmatch.getString("match_status");
                    System.out.println(status_of_match);

                    teamA_id1=Integer.parseInt(resultSetmatch.getString("teamA_id"));

                    System.out.println(resultSetmatch.getString("match_id")+" Retrieving match id");
                    match_id_retrieve=Integer.parseInt(resultSetmatch.getString("match_id"));
                    match_id_list.add(String.valueOf(match_id_retrieve));
                    PreparedStatement preparedStatement2 = connection.prepareStatement("select team_name FROM team where team_id=?;");
                    preparedStatement2.setInt(1,teamA_id1);
                    ResultSet resultSetteamname = preparedStatement2
                            .executeQuery();

                    while (resultSetteamname.next())
                    {
                        team_nameA=resultSetteamname.getString("team_name");
                        System.out.println("Printing team A "+team_nameA);
                    }
                    teamB_id1=Integer.parseInt(resultSetmatch.getString("teamB_id"));

                    PreparedStatement preparedStatement3 = connection.prepareStatement("select team_name FROM team where team_id=?;");
                    preparedStatement3.setInt(1,teamB_id1);
                    ResultSet resultSet2 = preparedStatement3
                            .executeQuery();

                    while (resultSet2.next())
                    {
                        team_nameB=resultSet2.getString("team_name");
                        System.out.println("Printing team B "+team_nameB);
                    }

                    data.add(new MatchScheduleClass(team_nameA,team_nameB,"Match_date: "+resultSetmatch.getString("match_date"),"Match_time: "+resultSetmatch.getString("match_time"),"Venue Name","Facilitator Name","Referee Name",match_id_retrieve));


                }

            }

            System.out.println(match_id_list);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void pass_data(String tournament_name1, String tournament_type1, int number_of_matches, ObservableList<String> selected_list_recieve, String team_nameA1, String team_nameB1, String match_time1, String match_date1, int facilitator_id, int venue_id, int referee_id, int added_match_no) {
                tournament_name2=tournament_name1;
        System.out.println(tournament_name2);
                tournament_type2=tournament_type1;
                number_of_matches2=number_of_matches;
                selected_list_recieve1=selected_list_recieve;
                team_nameA=team_nameA1;
                team_nameB=team_nameB1;
                match_time=match_time1;
                match_date=match_date1;
                facilitator_id1=facilitator_id;
                venue_id1=venue_id;
                referee_id1=referee_id;
                added_match_no1=added_match_no;


    }

    public void for_result_page(int sport_id_pass2, int tournament_id_pass2, int match_id_pass2, int winner_id_pass2, int loser_id_pass2) {
    /*    sport_id_pass1=sport_id_pass2;
        System.out.println(sport_id_pass2+"::::::::::::::::::");
        System.out.println(sport_id_pass1+"::::::::::::::::::");
        tournament_id_pass1=tournament_id_pass2;
        match_id_pass1=match_id_pass2;
        winner_id_pass1=winner_id_pass2;
        loser_id_pass1=loser_id_pass2;*/

    }


    private class CustomListCell extends ListCell<MatchScheduleClass> {
        private HBox content;
        private Text teamA;
        private Text venue;
        private Text date;
        private Text time;
        private Text teamB;
        private Text facilitator;
        private Text match_id_text;


        public CustomListCell() {
            super();
            teamA = new Text();
            venue = new Text();
            date = new Text();
            time = new Text();
            teamB=new Text();
            facilitator = new Text();
            match_id_text=new Text();
//            Button button = new Button(status_of_match);

            VBox vBox = new VBox(teamA, venue);
            VBox vBox2 = new VBox(date, time,match_id_text);
            VBox vBox3 = new VBox(teamB, facilitator);

            teamA.setStyle("-fx-font-size: 40px;");
            teamB.setStyle("-fx-font-size: 40px;");
            vBox.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-font-size: 20px;-fx-padding: 5px;");
            vBox2.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-font-size: 20px;-fx-padding: 17px;");
            vBox3.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-font-size: 20px;-fx-padding: 5px;");
            /*match_id_text.setStyle("");*/
//            button.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-text-alignment: right;-fx-background-color:#ee5c5c;-fx-text-fill: white;");



            content = new HBox(vBox,vBox2,vBox3);
            content.setStyle("-fx-alignment: center;");
            content.setSpacing(20);



        }

        @Override
        protected void updateItem(MatchScheduleClass item, boolean empty) {
            System.out.println(item+" print item");
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
               /* teamA.setText(team_nameA);
                venue.setText(String.valueOf(venue_id));
                teamB.setText( team_nameB);
                facilitator.setText(String.valueOf(facilitator_id));
                date.setText( match_date);
                time.setText( match_time);*/

                teamA.setText(item.getTeamA());
                venue.setText(item.getVenue());
                teamB.setText( item.getTeamB());
                facilitator.setText(item.getFacilitator());
                date.setText( item.getMatch_date());
                time.setText(item.getMatch_time());
                match_id_text.setText(String.valueOf(item.getMatch_id()));
                System.out.println("--------------------"+team_nameA);
                all_match_A.add(item.getTeamA()+"#########");
                System.out.println(all_match_A);
                all_match_B.add(teamB.getText());
                setGraphic(content);





            } else {
                setGraphic(null);
            }
        }
    }

}
