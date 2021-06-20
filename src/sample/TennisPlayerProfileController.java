package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class TennisPlayerProfileController implements Initializable {
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
    private TableView<TournamentPlayerClass> player_stats_table;

    @FXML
    private TableColumn<TournamentPlayerClass, Integer> competition_column;

    @FXML
    private TableColumn<TournamentPlayerClass, Integer> clean_sheet_column;
    public static String playernameclicked="";
    public static String playername="";
    public static String playerposition="";
    public static String playerage="";
    public static String playerhieght="";
    public static String playerweight="";
    public static String jerseynumber="";
    public static int player_id1,a,b;;
    TournamentPlayerClass  tournamentPlayerClass;

    ObservableList<TournamentPlayerClass> player_stat_list = FXCollections.observableArrayList();

    public void teamname(String selectedItem) {
        playernameclicked=selectedItem;

    }

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
            System.out.println(player_id1);

/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/



            PreparedStatement preparedStatement = connection.prepareStatement("select player_id from players where player_name=? and sport_id=4");
            System.out.println("Loading correctly " + playernameclicked);
            preparedStatement.setString(1, playernameclicked);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                player_id1 = resultSet1.getInt("player_id");

                System.out.println("player_id:" +
                        resultSet1.getString("player_id"));

                PreparedStatement preparedStatement1 =
                        connection.prepareStatement("select player_name,player_age,player_role,player_code,player_height,player_weight FROM players where player_id= ? and sport_id=4 ");
                preparedStatement1.setInt(1, player_id1);
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
            }
                init();
//                detail_list.addAll(each_team_detail_class);


            PreparedStatement preparedStatementstats = connection.prepareStatement("SELECT players.player_name, SUM(stats_tennis.total_point) AS point_scored,SUM(stats_tennis.playing_set) AS set_won\n" +
                    "FROM stats_tennis\n" +
                    "INNER JOIN tournament ON stats_tennis.tournament_id = tournament.tournament_id\n" +
                    "INNER JOIN players ON stats_tennis.player_id = players.player_id\n" +
                    " WHERE   stats_tennis.sport_id = 4 AND stats_tennis.player_id=?\n" +
                    "GROUP BY players.player_name;");

            preparedStatementstats.setInt(1, player_id1);
            System.out.println(player_id1);

            ResultSet resultSetStats=preparedStatementstats.executeQuery();
            while(resultSetStats.next()){

//                System.out.println( resultSetStats.getString("point_scored"));
                a = resultSetStats.getInt("point_scored");
                b=resultSetStats.getInt("set_won");


                tournamentPlayerClass = new TournamentPlayerClass(a,b);

                player_stat_list.add(tournamentPlayerClass);
                System.out.println(player_stat_list.size());




            }


            competition_column.setCellValueFactory(new PropertyValueFactory<TournamentPlayerClass, Integer>("points"));

            clean_sheet_column.setCellValueFactory(new PropertyValueFactory<TournamentPlayerClass, Integer>("set_won"));

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
}
