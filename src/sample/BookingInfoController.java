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

public class BookingInfoController implements Initializable {

    @FXML
    private TableView<BookingClass> book_table;

    @FXML
    private TableColumn<BookingClass, String> name_column;

    @FXML
    private TableColumn<BookingClass, String> no_of_teams_column;

    @FXML
    private TableColumn<BookingClass, String> inst;

    @FXML
    private TableColumn<BookingClass, Integer> booked;

    @FXML
    private TableColumn<BookingClass,Integer> yellow;

    @FXML
    private TableColumn<BookingClass,Integer> red;
    static ObservableList<BookingClass> venue_list = FXCollections.observableArrayList();
    public static String place="",q="",v="";
    public static int match,tournament_play,g,h;
    BookingClass institutionClass;

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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT players.player_name, team.team_name,instituition.instituition_name ,(SUM(stats_football.yellow_card)  + SUM(stats_football.red_card)) AS total_no_booked,SUM(stats_football.yellow_card) as Yellow_card, SUM(stats_football.red_card) as Red_card\n" +
                    "FROM stats_football\n" +
                    "INNER JOIN players ON players.player_id = stats_football.player_id\n" +
                    "INNER JOIN instituition ON players.instituition_id = instituition.instituition_id\n" +
                    "INNER JOIN team ON players.team_id = team.team_id\n" +
                    "GROUP BY players.player_name,team.team_name,instituition.instituition_name\n" +
                    "ORDER BY  (SUM(stats_football.yellow_card)  + SUM(stats_football.red_card)) desc");
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                place=resultSet1.getString("player_name");
                q=resultSet1.getString("team_name");
                v=resultSet1.getString("instituition_name");
                tournament_play=resultSet1.getInt("total_no_booked");
                g=resultSet1.getInt("Yellow_card");
                h=resultSet1.getInt("Red_card");

                System.out.println(place+""+q+""+v+""+tournament_play+""+g+""+h);



/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/

//                detail_list.addAll(each_team_detail_class);
                institutionClass=new BookingClass(place,q,v,tournament_play,g,h);
                venue_list.add(institutionClass);


            }
            name_column.setCellValueFactory(new PropertyValueFactory<BookingClass, String>("a"));
            no_of_teams_column.setCellValueFactory(new PropertyValueFactory<BookingClass, String>("b"));
            inst.setCellValueFactory(new PropertyValueFactory<BookingClass, String>("c"));
            booked.setCellValueFactory(new PropertyValueFactory<BookingClass, Integer>("d"));
            yellow.setCellValueFactory(new PropertyValueFactory<BookingClass, Integer>("e"));
            red.setCellValueFactory(new PropertyValueFactory<BookingClass, Integer>("f"));






            book_table.setItems(venue_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
