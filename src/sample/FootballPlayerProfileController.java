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

public class FootballPlayerProfileController implements Initializable {

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
    private TableView<PlayerClass> player_stats_table;

    @FXML
    private TableColumn<PlayerClass, String> competition_column;

    @FXML
    private TableColumn<PlayerClass, Integer> jersey_column;

    @FXML
    private TableColumn<PlayerClass, Integer> goals_column;

    @FXML
    private TableColumn<PlayerClass, Integer> assists_column;

    @FXML
    private TableColumn<PlayerClass, Integer> yellow_card_column;

    @FXML
    private TableColumn<PlayerClass, Integer> red_card_column;

    @FXML
    private TableColumn<PlayerClass, Integer> starting_11_column;

    @FXML
    private TableColumn<PlayerClass, Integer> substitution_on_column;

    @FXML
    private TableColumn<PlayerClass, Integer> substitution_off_column;

    @FXML
    private TableColumn<PlayerClass, Integer> clean_sheet_column;
    public static String playernameclicked="";
    public static String playername="";
    public static String playerposition="";
    public static String playerage="";
    public static String playerhieght="";
    public static String playerweight="";
    public static String jerseynumber="";
    public static String player_id1="";

    public static int goals,assist,yellow,red,starting11,subon,suboff,goals_saved,name_player,playerid;
    public static String  tournament_name="";
    ObservableList<PlayerClass> player_stat_list = FXCollections.observableArrayList();
    PlayerClass playerClass;


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
            PreparedStatement preparedStatement = connection.prepareStatement("select player_id from players where player_name=? and sport_id=1");
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
                        connection.prepareStatement("select player_name,player_age,player_role,player_code,player_height,player_weight FROM players where player_id= ? and sport_id=1 ");
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

            PreparedStatement preparedStatementstats = connection.prepareStatement("SELECT  tournament.tournament_name, SUM(stats_football.goal_scored) AS goal_scored,SUM( stats_football.assist) AS assists, SUM(stats_football.yellow_card) AS yellow_card, SUM(stats_football.red_card) as red_card, SUM(stats_football.starting_xi) as starting_xi,SUM(stats_football.substituition_on) as sub_on, SUM(stats_football.substituition_off) as sub_off,SUM( stats_football.goal_save) as goal_saved\n" +
                    "FROM stats_football\n" +
                    "INNER JOIN tournament ON stats_football.tournament_id = tournament.tournament_id\n" +
                    " WHERE   stats_football.sport_id = 1 AND stats_football.player_id=?\n" +
                    "GROUP BY  tournament.tournament_name\n" +
                    "ORDER BY SUM(stats_football.goal_scored) DESC;");

            preparedStatementstats.setInt(1, playerid);
            System.out.println(playerid);

            ResultSet resultSetStats=preparedStatementstats.executeQuery();
            while(resultSetStats.next()){

                System.out.println( resultSetStats.getString("tournament_name"));
                tournament_name = resultSetStats.getString("tournament_name");
                goals=resultSetStats.getInt("goal_scored");
                assist=resultSetStats.getInt("assists");
                System.out.println(assist);
                yellow=resultSetStats.getInt("yellow_card");
                red=resultSetStats.getInt("red_card");
                starting11=resultSetStats.getInt("starting_xi");
                subon=resultSetStats.getInt("sub_on");
                suboff=resultSetStats.getInt("sub_off");
                goals_saved=resultSetStats.getInt("goal_saved");

                playerClass = new PlayerClass(tournament_name,goals,assist,yellow,red,starting11,subon,suboff,goals_saved);

                player_stat_list.add(playerClass);
                System.out.println(player_stat_list.size());




            }


            competition_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, String>("player_name"));
            goals_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_scored"));
            assists_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("assist"));
            yellow_card_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("yellow_card"));
            red_card_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("red_card"));
            starting_11_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("starting_11"));
            substitution_on_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_on"));
            substitution_off_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("sub_off"));
            clean_sheet_column.setCellValueFactory(new PropertyValueFactory<PlayerClass, Integer>("goals_saved"));

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
