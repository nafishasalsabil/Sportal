package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AvailableTeamForTournamentController implements Initializable {

    @FXML
    private ListView<String> available_team_list;
    @FXML
    private Button done_adding;
    ObservableList<String> team_list = FXCollections.observableArrayList();
    ObservableList<String> selected_list = FXCollections.observableArrayList();
    public static int i;


    public void selectedteams(MouseEvent mouseEvent) {

    }

    public void onDonePressed(ActionEvent actionEvent) {
       /* try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement("insert into players (player_name, player_age, player_course, player_address, player_gender, instituition_id, coach_id, sport_id, team_id,contact, player_code) values (?, ?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, name1);
            stmt.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        AddTournamentsPageController addTournamentsPageController = new AddTournamentsPageController();
        addTournamentsPageController.passlist(selected_list);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        available_team_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        available_team_list.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
//            team_list.add(String.valueOf(available_team_list.getSelectionModel().getSelectedItems()));
            System.out.println(available_team_list.getSelectionModel().getSelectedItems());
            selected_list.add(String.valueOf(available_team_list.getSelectionModel().getSelectedItems()));
            for( i=0;i<selected_list.size();i++)
            {
                System.out.println(i);
            }
        });

        available_team_list.setItems(team_list);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select team_name FROM team where sport_id=1;");


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                team_list.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));

            }
            //  detail_list.addAll(each_team_detail_class);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
