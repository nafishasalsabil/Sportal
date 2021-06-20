package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CricketPlayerProfileController implements Initializable {

    @FXML
    private HBox profile_hbox;
    @FXML
    private Label player_name_label;

    @FXML
    private Label player_position_label;

    @FXML
    private Label age_label;

    @FXML
    private Label height_label;

    @FXML
    private Label weight_label;

    @FXML
    private Label jersey_number;

    @FXML
    private TableView<CricketerClass> player_stats_table;

    @FXML
    private TableColumn<CricketerClass, String> competition_column;

    @FXML
    private TableColumn<CricketerClass, Integer> innings_column;

    @FXML
    private TableColumn<CricketerClass, Integer> runs_column;

    @FXML
    private TableColumn<CricketerClass, Integer> batting_avg_column;

    @FXML
    private TableColumn<CricketerClass, Integer> strike_rate_column;

    @FXML
    private TableColumn<CricketerClass, Integer> fifty_column;

    @FXML
    private TableColumn<CricketerClass, Integer> century_column;

    @FXML
    private TableColumn<CricketerClass, Integer> sixes_column;

    @FXML
    private TableColumn<CricketerClass, Integer> fours_column;

    @FXML
    private TableColumn<CricketerClass, Integer> wicket_column;

    @FXML
    private TableColumn<CricketerClass, Integer> bowl_avg_col;

    @FXML
    private TableColumn<CricketerClass, Integer> economy_col;
    public static String playernameclicked="";
    public static String playername="";
    public static String playerposition="";
    public static String playerage="";
    public static String playerhieght="";
    public static String playerweight="";
    public static String jerseynumber="";
    public static String player_id1="";

    public static int innings, runs, batting_average,strike_rate,fifty,century,sixes,fours,wickets,bowling_average ,economy,playerid;
    public static String competition="";
    public static String  tournament_name="";
    ObservableList<CricketerClass> player_stat_list = FXCollections.observableArrayList();
    CricketerClass playerClass;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profile_hbox.setStyle("-fx-background-image: url('images/bg.jpg');"+
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1280 270;" +
                "-fx-background-position: center center;");

        player_stat_list.clear();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select player_id from players where player_name=? and sport_id=2");
            System.out.println("Loading correctly " + playernameclicked);
            preparedStatement.setString(1, playernameclicked);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                player_id1=resultSet1.getString("player_id");
                playerid=Integer.parseInt(player_id1);
                System.out.println("player_id:" +
                        resultSet1.getString("player_id"));

/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/
                PreparedStatement preparedStatement1 =
                        connection.prepareStatement("select player_name,player_age,player_role,player_code,player_height,player_weight FROM players where player_id= ? and sport_id=2 ");
                preparedStatement1.setString(1, resultSet1.getString("player_id"));
                ResultSet resultSet = preparedStatement1
                        .executeQuery();
                while (resultSet.next()) {

                    System.out.println("playerName:" +
                            resultSet.getString("player_age"));
                    playername = resultSet.getString("player_name");
                    playerposition = resultSet.getString("player_role");
                    playerhieght = resultSet.getString("player_height");
                    playerweight = resultSet.getString("player_weight");
                    jerseynumber = resultSet.getString("player_code");
                    playerage = resultSet.getString("player_age");

/*
                    each_team_detail_class = new Each_Team_Detail_Class(Integer.parseInt(resultSet.getString("player_age")), resultSet.getString("player_name"), resultSet.getString("player_course"), resultSet.getString("player_gender"), resultSet.getString("player_address"), resultSet.getString("instituition_id"), resultSet.getString("contact"), resultSet.getString("player_course"));
                    detail_list.add(each_team_detail_class);
*/

                }
                  init();
//                detail_list.addAll(each_team_detail_class);


            }

            PreparedStatement preparedStatementstats = connection.prepareStatement("SELECT  tournament.tournament_name, \n" +
                    "SUM(stats_cricket.innings) AS Innings,\n" +
                    "SUM(stats_cricket.total_run) AS Runs, \n" +
                    "CAST(SUM(stats_cricket.total_run) AS FLOAT) / ISNULL(NULLIF(CAST(SUM(stats_cricket.not_out) AS FLOAT),0),1) as Batting_avg,\n" +
                    "CAST(SUM(stats_cricket.total_run) AS FLOAT) / ISNULL(NULLIF(CAST(SUM(stats_cricket.total_ball_played) AS FLOAT),0),1) * 100 as strikeRate,\n" +
                    "SUM(stats_cricket.half_century) AS Fifty, \n" +
                    "SUM(stats_cricket.century) as Century, \n" +
                    "SUM(stats_cricket.total_sixes) as Sixes,\n" +
                    "SUM(stats_cricket.total_fours) as Fours, \n" +
                    "SUM(stats_cricket.wickets) as Wickets,\n" +
                    "CAST(SUM(stats_cricket.total_run_given) AS FLOAT) / ISNULL(NULLIF(CAST(SUM(stats_cricket.wickets) AS FLOAT),0),1) as Bowling_avg,\n" +
                    "CAST(SUM(stats_cricket.total_run_given) AS FLOAT)/ (ISNULL(NULLIF(CAST(SUM(stats_cricket.total_ball_delivered) AS FLOAT),0),1)/6.0) AS Economy\n" +
                    "FROM stats_cricket\n" +
                    "INNER JOIN tournament ON stats_cricket.tournament_id = tournament.tournament_id\n" +
                    " WHERE   stats_cricket.sport_id = 2 AND stats_cricket.player_id=?\n" +
                    "GROUP BY  tournament.tournament_name\n" +
                    "ORDER BY SUM(stats_cricket.total_run) DESC;");

            preparedStatementstats.setInt(1, playerid);

            ResultSet resultSetStats=preparedStatementstats.executeQuery();
            while(resultSetStats.next()){

                System.out.println( resultSetStats.getString("tournament_name"));
                tournament_name = resultSetStats.getString("tournament_name");
                innings=resultSetStats.getInt("Innings");
                runs=resultSetStats.getInt("Runs");
                System.out.println(runs);
                batting_average=resultSetStats.getInt("Batting_avg");
                strike_rate=resultSetStats.getInt("strikeRate");
                fifty=resultSetStats.getInt("Fifty");
                century=resultSetStats.getInt("Century");
                sixes=resultSetStats.getInt("Sixes");
                fours=resultSetStats.getInt("Fours");
                wickets=resultSetStats.getInt("Wickets");
                bowling_average=resultSetStats.getInt("Bowling_avg");
                economy=resultSetStats.getInt("Economy");

                playerClass = new CricketerClass(tournament_name,innings, runs, batting_average,strike_rate,fifty,century,sixes,fours,wickets,bowling_average ,economy);

                player_stat_list.add(playerClass);
                System.out.println(player_stat_list.size());




            }


            competition_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, String>("competition"));
            innings_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("innings"));
            runs_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("runs"));
            batting_avg_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("batting_average"));
            strike_rate_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("strike_rate"));
            fifty_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("fifty"));
            century_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("century"));
            sixes_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("sixes"));
            fours_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("fours"));
            wicket_column.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("wickets"));
            bowl_avg_col.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("bowling_average"));
            economy_col.setCellValueFactory(new PropertyValueFactory<CricketerClass, Integer>("economy"));


            player_stats_table.setItems(player_stat_list);





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    protected void init() {
        // this class is the HelloworldController class because init() is not private method
        // do init staff if you want
        // now FML fields are not null
        //  teamName_Label.setText(teamname1);
        System.out.println("working?");
        player_name_label.setText(playername);
        player_position_label.setText("Position: "+playerposition);
        age_label.setText("Age: "+playerage);
        weight_label.setText("Weight: "+playerweight);
        height_label.setText("Height: "+playerhieght);
        jersey_number.setText(jerseynumber);
    }

    public void player_name(String selectedItem) {
        playernameclicked=selectedItem;

    }
}
