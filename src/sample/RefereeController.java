package sample;

import com.sun.imageio.plugins.common.I18NImpl;
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

public class RefereeController implements Initializable {

    @FXML
    private TableView<RefereeClass> referee_table;

    @FXML
    private TableColumn<RefereeClass, String> n_column;

    @FXML
    private TableColumn<RefereeClass, Integer> matches_column;

    @FXML
    private TableColumn<RefereeClass, Integer> bookng_column;

    @FXML
    private TableColumn<RefereeClass, Integer> ellow_column;

    @FXML
    private TableColumn<RefereeClass, Integer> red_column;

    static ObservableList<RefereeClass> venue_list = FXCollections.observableArrayList();
    public static String place="";
    public static int match,tournament_play,g,w,q;
    RefereeClass refereeClass;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venue_list.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT referee.referee_name, COUNT(match_schedule.match_id) as Matches,  ISNULL(SUM(stats_football.yellow_card),0)+ISNULL(SUM(stats_football.red_card),0) AS no_of_booking, ISNULL(SUM(stats_football.yellow_card),0) as Yellow_Card , ISNULL(SUM(stats_football.red_card),0) as Red_card\n" +
                    "FROM ((stats_football\n" +
                    "INNER JOIN match_schedule ON match_schedule.match_id = stats_football.match_id)\n" +
                    "INNER JOIN referee ON referee.referee_id = match_schedule.referee_id)\n" +
                    "WHERE match_schedule.sport_id = 1\n" +
                    "GROUP BY referee.referee_name");
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                place=resultSet1.getString("referee_name");
                match=resultSet1.getInt("Matches");
                tournament_play=resultSet1.getInt("no_of_booking");
                g=resultSet1.getInt("Yellow_Card");
                w=resultSet1.getInt("Red_card");


/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/

//                detail_list.addAll(each_team_detail_class);
                refereeClass=new RefereeClass(place,match,tournament_play,g,w);
                venue_list.add(refereeClass);


            }
            n_column.setCellValueFactory(new PropertyValueFactory<RefereeClass, String>("name"));
            matches_column.setCellValueFactory(new PropertyValueFactory<RefereeClass, Integer>("matches"));
            bookng_column.setCellValueFactory(new PropertyValueFactory<RefereeClass, Integer>("booking"));
            ellow_column.setCellValueFactory(new PropertyValueFactory<RefereeClass, Integer>("yellow"));
            red_column.setCellValueFactory(new PropertyValueFactory<RefereeClass, Integer>("red"));




            referee_table.setItems(venue_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
