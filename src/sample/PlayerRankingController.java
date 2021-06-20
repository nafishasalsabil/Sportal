package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PlayerRankingController implements Initializable {

    @FXML
    private TableView<RankClass> goal_table;

    @FXML
    private TableColumn<RankClass, String> player_name_column;

    @FXML
    private TableColumn<RankClass, Integer> goal_scored_column;

    @FXML
    private TableColumn<RankClass, String> team_column;

    @FXML
    private TableView<RankClass> assist_table;

    @FXML
    private TableColumn<RankClass, String> playername_assist_column;

    @FXML
    private TableColumn<RankClass, Integer> assist_column;

    @FXML
    private TableColumn<RankClass, String> teamcolumn_assist;

    static ObservableList<RankClass> top_goals = FXCollections.observableArrayList();
    static ObservableList<RankClass> top_assist = FXCollections.observableArrayList();
    public static String pNameGoal="",pNameAssist="",teamGoal="",teamAssist="";
    public static int goal,assist1;
    RankClass rankClass1;
    RankClass rankClass2;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        top_goals.clear();
        top_assist.clear();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT players.player_name,SUM(stats_football.goal_scored) as Goal_scored, team.team_name\n" +
                    "FROM ((stats_football\n" +
                    "INNER JOIN players ON stats_football.player_id = players.player_id)\n" +
                    "INNER JOIN team ON team.team_id = players.team_id)\n" +
                    "GROUP BY players.player_name, team.team_name\n" +
                    "ORDER BY SUM(stats_football.goal_scored) DESC");
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                pNameGoal=resultSet1.getString("player_name");
                goal=resultSet1.getInt("Goal_scored");
                teamGoal=resultSet1.getString("team_name");
                System.out.println("player_id:" +
                        resultSet1.getString("player_name"));

/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/

//                detail_list.addAll(each_team_detail_class);
                rankClass1=new RankClass(pNameGoal,teamGoal,goal);
                top_goals.add(rankClass1);


            }
            player_name_column.setCellValueFactory(new PropertyValueFactory<RankClass, String>("player_name"));
            goal_scored_column.setCellValueFactory(new PropertyValueFactory<RankClass, Integer>("goal"));
            team_column.setCellValueFactory(new PropertyValueFactory<RankClass, String>("team"));



            goal_table.setItems(top_goals);

            PreparedStatement preparedStatementstats = connection.prepareStatement("SELECT players.player_name,SUM(stats_football.assist) as Assist_scored, team.team_name\n" +
                    "FROM ((stats_football\n" +
                    "INNER JOIN players ON stats_football.player_id = players.player_id)\n" +
                    "INNER JOIN team ON team.team_id = players.team_id)\n" +
                    "GROUP BY players.player_name, team.team_name\n" +
                    "ORDER BY SUM(stats_football.goal_scored) DESC");


            ResultSet resultSetStats=preparedStatementstats.executeQuery();
            while(resultSetStats.next()){

                pNameAssist=resultSetStats.getString("player_name");
                assist1=resultSetStats.getInt("Assist_scored");
                teamAssist=resultSetStats.getString("team_name");

                rankClass2=new RankClass(pNameAssist,teamAssist,assist1);
                top_assist.add(rankClass2);




            }


            playername_assist_column.setCellValueFactory(new PropertyValueFactory<RankClass, String>("player_name"));
            assist_column.setCellValueFactory(new PropertyValueFactory<RankClass, Integer>("goal"));
            teamcolumn_assist.setCellValueFactory(new PropertyValueFactory<RankClass, String>("team"));

            assist_table.setItems(top_assist);





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
