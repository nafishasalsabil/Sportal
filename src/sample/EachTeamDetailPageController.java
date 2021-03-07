package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EachTeamDetailPageController implements Initializable {
    @FXML
    Button tournaments_button_teams_each;
    @FXML
    Button venues_button_teams_each;
    @FXML
    Button schedule_button_teams_each;
    @FXML
    private AnchorPane teamsAnchorPaneEach;


    @FXML
    private Label teamName_Label;
    @FXML
    private TableView<Each_Team_Detail_Class> table_team;
    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_name;

    @FXML
    private TableColumn<Each_Team_Detail_Class, Integer> player_age;

    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_roll;

    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_gender;

    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_address;

    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_institution;

    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_contact;

    ObservableList<Each_Team_Detail_Class> detail_list = FXCollections.observableArrayList();

    public void tournaments_button_action_teams_each(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournaments_Page.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);

    }

    public void venues_button_action_teams_each(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Venues_Page.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);

    }

    public void schedule_button_teams_each(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Schedule_Page.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);

    }
    Each_Team_Detail_Class each_team_detail_class;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select player_name,player_age,player_course,player_gender,player_address,instituition_id,contact FROM players;");


            while (resultSet.next()) {

                System.out.println("playerName:" +
                        resultSet.getString("player_age"));

                    each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"));
                    detail_list.add(each_team_detail_class);

            }
          //  detail_list.addAll(each_team_detail_class);
            player_name.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_name"));
            player_age.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, Integer>("player_age"));
            player_roll.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_roll"));
            player_gender.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_gender"));
            player_address.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_address"));
            player_institution.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_institution"));
            player_contact.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_contact"));
            for(int i=0;i<detail_list.size();i++)
            {
                System.out.println("dfgh");
            }

            table_team.setItems(detail_list);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectMSSQL cnObj = new ConnectMSSQL();


    }
}
