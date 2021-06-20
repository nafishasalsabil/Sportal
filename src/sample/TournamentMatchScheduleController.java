package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TournamentMatchScheduleController implements Initializable {

    @FXML
    private ListView<MatchScheduleClass> schedule_listview;
    @FXML
    private AnchorPane match_schedule_anchorpane;
    @FXML
    private Button see_match_standings;
    @FXML
    void see_match_standings_pressed(ActionEvent event) throws IOException {
        TournamentStandingController tournamentMatchScheduleController=new TournamentStandingController();
        tournamentMatchScheduleController.tname(tournament_name1);

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Standing.fxml"));

        match_schedule_anchorpane.getChildren().setAll(pane);

    }
    @FXML
    private Button add_match_button;
    ObservableList<MatchScheduleClass> data = FXCollections.observableArrayList();
    public static String tournament_name1="";
    public static String tournament_type1="";
    public static String team_nameA="";
    public static String team_nameB="";
    public static String tournament_date="";
    public static int venue_id;
    public static int facilitator_id;
    public static int referee_id;
    public static String match_date=""; public static String match_time="";
    public static int added_match_no;
    public static int remaining_matches;
    public static String status_of_match="";
    public static int id_of_match;
    int count=0;
    public static int number_of_matches,tournament_id1,teamA_id1,teamB_id1;
    static ObservableList<String> selected_list_recieve = FXCollections.observableArrayList();
    static ObservableList<String> all_match_A = FXCollections.observableArrayList();
    static ObservableList<String> all_match_B = FXCollections.observableArrayList();
    public static int sport_id_pass1,tournament_id_pass1,match_id_pass1,winner_id_pass1,loser_id_pass1;

    public static int match_id_retrieve;
    public static boolean match_finished=false;
    @FXML
    private Button see_finished_matches;
    @FXML
    private Label remaining_label;
    int count_index;

    static ObservableList<String> match_id_list = FXCollections.observableArrayList();



    @FXML
    void forAddingPlayerToTeam(MouseEvent event) {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schedule_listview.setItems(data);
        count_index=0;
        System.out.println("match list size"+data.size());

        schedule_listview.setCellFactory(new Callback<ListView<MatchScheduleClass>, ListCell<MatchScheduleClass>>() {
            @Override
            public ListCell<MatchScheduleClass> call(ListView<MatchScheduleClass> param) {
                return new CustomListCell();
            }

        });


        remaining_matches=number_of_matches-added_match_no;
        init();

        if( data.size()==number_of_matches){
            add_match_button.setVisible(false);

        }

//        data.add(new MatchScheduleClass(team_nameA,team_nameB,"Match_date: "+resultSet.getString("match_date"),"Match_time: "+resultSet.getString("match_time"),"Venue Name","Facilitator Name","Referee Name"));

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();

//            data.add(new MatchScheduleClass(team_nameA,team_nameB,match_date,match_time,Integer.toString(venue_id),Integer.toString(facilitator_id),Integer.toString(referee_id)));

            System.out.println("printing data list   "+data);
            System.out.println(tournament_name1);
            PreparedStatement preparedStatement_tournament = connection.prepareStatement("select tournament_id FROM tournament where tournament_name=? and sport_id=1;");
            preparedStatement_tournament.setString(1,tournament_name1);
            ResultSet resultSet1 = preparedStatement_tournament
                    .executeQuery();

            while(resultSet1.next()){

                System.out.println(resultSet1.getString("tournament_id"));
                tournament_id1=Integer.parseInt(resultSet1.getString("tournament_id"));
                System.out.println(tournament_id1);
//                tournament_id1=Integer.parseInt(resultSet1.getString("tournament_id"));
                PreparedStatement preparedStatement_match = connection.prepareStatement("select match_date,match_time,teamA_id,teamB_id,match_status,match_id FROM match_schedule where tournament_id=? and match_status='Due';");
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

                    if( data.size()==number_of_matches){
                        add_match_button.setVisible(false);

                    }
                }

            }

            System.out.println(match_id_list);


        } catch (Exception e) {
            e.printStackTrace();
        }

        if( data.size()==number_of_matches){
            add_match_button.setVisible(false);

        }




    }
    protected void init() {

        remaining_label.setText("Remaining Number of Matches to Add: "+remaining_matches);
    }

    public void add_match_pressed(ActionEvent actionEvent) throws IOException {
        AddFootballMatchDialogBoxController addFootballMatchDialogBoxController = new AddFootballMatchDialogBoxController();
        addFootballMatchDialogBoxController.tournament_name_pass(tournament_name1);
        addFootballMatchDialogBoxController.tournament_type_pass(tournament_type1);
        addFootballMatchDialogBoxController.tournament_match_number(number_of_matches);
        System.out.println(selected_list_recieve);
        addFootballMatchDialogBoxController.selected_team_list(selected_list_recieve);

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/AddFootballMatchDialogBox.fxml"));

        match_schedule_anchorpane.getChildren().setAll(pane);

//        secondStage.showAndWait();



    }

    public void tournament_name(String tournament_name) {
        tournament_name1 = tournament_name;
        System.out.println(tournament_name1+"PASSING?");
    }

    public void tournament_type_pass(String tournament_type) {
        tournament_type1 = tournament_type;
    }


    public void match_number(int comb) {
        number_of_matches=comb;

    }

    public void selected_team_list_pass(ObservableList<String> number_of_team) {
        selected_list_recieve=number_of_team;
    }

    public void data_pass(String teamA, String teamB, String mtchtime, String tournament_date, int sport_id, String tournament_name_pass) {

        team_nameA=teamA;
        team_nameB=teamB;
        match_time=mtchtime;
        match_date=tournament_date;
        facilitator_id=sport_id;
        venue_id=sport_id;
        referee_id=sport_id;
        tournament_name1=tournament_name_pass;
        System.out.println(team_nameA+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");





    }

    public void added_match_number(int i) {
        added_match_no=i;
    }


    public void match_clicked(MouseEvent mouseEvent) throws IOException {

        System.out.println(match_finished);
        if(match_finished==true){
            schedule_listview.getSelectionModel().getSelectedIndex();
      CompletedMatchStatsController completedMatchStatsController = new CompletedMatchStatsController();
             completedMatchStatsController.pass_match_id(match_id_list.get( schedule_listview.getSelectionModel().getSelectedIndex()));

            AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Completed_Match_Stats.fxml"));

            match_schedule_anchorpane.getChildren().setAll(pane);


        }
        else{
            System.out.println(match_finished);
        }



    }


    public void pass_tournament_name(String selectedItem) {
        tournament_name1=selectedItem;
    }

    public void pass_match_id(int match_id) {

    }
    @FXML
    void see_finished_matches_clicked(ActionEvent event) throws IOException {

        TournamentMatchScheduleSeeFinishedMatchesController tournamentMatchScheduleSeeFinishedMatchesController=new TournamentMatchScheduleSeeFinishedMatchesController();
        tournamentMatchScheduleSeeFinishedMatchesController.pass_data(tournament_name1,tournament_type1,number_of_matches,selected_list_recieve,team_nameA,team_nameB,match_time,match_date,facilitator_id,venue_id,referee_id,added_match_no);
       tournamentMatchScheduleSeeFinishedMatchesController.for_result_page(sport_id_pass1,tournament_id_pass1,match_id_pass1,winner_id_pass1,loser_id_pass1);

        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule_See_Finished_Matches.fxml"));

        match_schedule_anchorpane.getChildren().setAll(pane);


    }

    public void pass_data(int sport_id_pass, int tournament_id_pass, int match_id_pass2, int winner_id_pass, int loser_id_pass) {

        sport_id_pass1=sport_id_pass;
        System.out.println(sport_id_pass+"|||||||||||||||||");
        System.out.println(sport_id_pass1+"|||||||||||||||||");

        tournament_id_pass1=tournament_id_pass;
        match_id_pass1=match_id_pass2;
        winner_id_pass1=winner_id_pass;
        loser_id_pass1=loser_id_pass;

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
            Button button = new Button(status_of_match);

            VBox vBox = new VBox(teamA, venue);
            VBox vBox2 = new VBox(date, time,match_id_text);
            VBox vBox3 = new VBox(teamB, facilitator);

            teamA.setStyle("-fx-font-size: 40px;");
            teamB.setStyle("-fx-font-size: 40px;");
            vBox.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-font-size: 20px;-fx-padding: 5px;");
            vBox2.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-font-size: 20px;-fx-padding: 17px;");
            vBox3.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-font-size: 20px;-fx-padding: 5px;");
            match_id_text.setStyle("-fx-text-fill: white;");
            button.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-text-alignment: right;-fx-background-color:#ee5c5c;-fx-text-fill: white;");



            content = new HBox(vBox,vBox2,vBox3,button);
            content.setStyle("-fx-alignment: center;");
            content.setSpacing(20);

             count=0;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    count++;
                    if(count%2==0){
                        match_finished=false;
                        button.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-text-alignment: right;-fx-background-color:#ee5c5c;-fx-text-fill: white;");
                        button.setText(status_of_match);

                        try {
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                            Statement statement = connection.createStatement();


                            PreparedStatement preparedStatementmatchid = connection.prepareStatement("select match_id FROM match_schedule where teamA_id=? and teamB_id=?;");
                            preparedStatementmatchid.setInt(1,teamA_id1);
                            preparedStatementmatchid.setInt(2,teamB_id1);

                            ResultSet resultSetmatchid = preparedStatementmatchid
                                    .executeQuery();

                            while (resultSetmatchid.next())
                            {
                                id_of_match=Integer.parseInt(resultSetmatchid.getString("match_id"));
                                System.out.println("Printing match id "+id_of_match);

                                PreparedStatement stmt =
                                        connection.prepareStatement("Update match_schedule set match_status='Due' where match_id=?");

                                stmt.setInt(1, id_of_match);
                                stmt.executeUpdate();
                            }





                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        }
                  else{

                        match_finished=true;
                        button.setStyle("-fx-font-family: 'Segoe UI Semibold';-fx-text-alignment: right;-fx-background-color:#71c171;-fx-text-fill: white;");
                        button.setText("Finished");


                        try {
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                            Statement statement = connection.createStatement();


                            PreparedStatement preparedStatementmatchid = connection.prepareStatement("select match_id FROM match_schedule where teamA_id=? and teamB_id=?;");
                            preparedStatementmatchid.setInt(1,teamA_id1);
                            preparedStatementmatchid.setInt(2,teamB_id1);

                            ResultSet resultSetmatchid = preparedStatementmatchid
                                    .executeQuery();

                            while (resultSetmatchid.next())
                            {
                                id_of_match=Integer.parseInt(resultSetmatchid.getString("match_id"));
                                System.out.println("Printing match id "+id_of_match);

                                PreparedStatement stmt =
                                        connection.prepareStatement("Update match_schedule set match_status='Finished' where match_id=?");

                                stmt.setInt(1, id_of_match);
                                stmt.executeUpdate();
                            }





                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }

                }
            });

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

