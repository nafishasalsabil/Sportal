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

public class InstitutionController implements Initializable {

    @FXML
    private TableView<InstitutionClass> inst_table;

    @FXML
    private TableColumn<InstitutionClass, String> name_column;

    @FXML
    private TableColumn<InstitutionClass, Integer> no_of_teams_column;

    @FXML
    private TableColumn<InstitutionClass, Integer> no_of_players_column;
    static ObservableList<InstitutionClass> venue_list = FXCollections.observableArrayList();
    public static String place="";
    public static int match,tournament_play,g;
    InstitutionClass institutionClass;
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT instituition.instituition_name,\n" +
                    "(SELECT COUNT(team.team_name) AS No_of_teams FROM team WHERE team.instituition_id = instituition.instituition_id) AS No_of_teams,\n" +
                    "(SELECT COUNT(players.instituition_id) AS No_of_players FROM players WHERE players.instituition_id = instituition.instituition_id) AS No_of_players\n" +
                    "FROM instituition");
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                place=resultSet1.getString("instituition_name");
                match=resultSet1.getInt("No_of_teams");
                tournament_play=resultSet1.getInt("No_of_players");



/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/

//                detail_list.addAll(each_team_detail_class);
                institutionClass=new InstitutionClass(place,match,tournament_play);
                venue_list.add(institutionClass);


            }
            name_column.setCellValueFactory(new PropertyValueFactory<InstitutionClass, String>("name"));
            no_of_teams_column.setCellValueFactory(new PropertyValueFactory<InstitutionClass, Integer>("team"));
            no_of_players_column.setCellValueFactory(new PropertyValueFactory<InstitutionClass, Integer>("player"));





            inst_table.setItems(venue_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
