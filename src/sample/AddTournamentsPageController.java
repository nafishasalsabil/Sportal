package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTournamentsPageController {
    @FXML
    private TextField tournament_title_field;
    @FXML
    private AnchorPane addtournamentanchorpane;
    @FXML
    private DatePicker tournament_datepicker;

    @FXML
    private RadioButton league_radio;

    @FXML
    private ToggleGroup tournament_type_group;

    @FXML
    private RadioButton qualifier_radiobutton;

    @FXML
    private ComboBox selectteamcombobox;

    @FXML
    private Button cancel_button;
    @FXML
    private Button available_teams_button;
    @FXML
    private Button create_tournament_button;
    @FXML
    private Button add_match_button;
    public static String tournament_date  ="";
    public static String tournament_title  ="";
    public static String tournament_type  ="";
    public static int a=1,b=2;
    public static int number_of_team;
    int fact=1,comb;
   static ObservableList<String> selected_list_recieve = FXCollections.observableArrayList();



    public void cancel_clicked(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournaments_Page.fxml"));

        addtournamentanchorpane.getChildren().setAll(pane);

    }

    public void tournament_create_clicked(ActionEvent actionEvent) {
        LocalDate localDate = tournament_datepicker.getValue();
        tournament_date=localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(tournament_date);
        tournament_title=tournament_title_field.getText();
        System.out.println(tournament_title);
        if(league_radio.isSelected())
        {
            tournament_type = "league";
        }
       /* else if(qualifier_radiobutton.isSelected())
        {
            tournament_type = "qualifier";
        }*/

        for(int i=1;i<=number_of_team;i++){
            fact=fact*i;
        }
        System.out.println(fact);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement("insert into tournament (tournament_name,tournament_type_id, sport_id, number_of_team, tournament_date, game_type_id) values (?, ?,?,?,?,?)");

            stmt.setString(1, tournament_title);
            if(tournament_type.equals("league"))
            {
                stmt.setInt(2,a);
            }
            else if(tournament_type.equals("qualifier"))
            {
                stmt.setInt(2,b);

            }


            comb = factorial(number_of_team) / (factorial(2) * factorial(number_of_team-2));
            System.out.println("Combination: " + comb);


            stmt.setInt(3,a);
            stmt.setInt(4,number_of_team);
            stmt.setString(5,tournament_date);
            stmt.setInt(6,a);
            stmt.executeUpdate();


            PreparedStatement preparedStatement = connection.prepareStatement("select tournament_id FROM tournament where tournament_name= ? ");
            System.out.println("Loading correctly " + tournament_title);
            preparedStatement.setString(1, tournament_title);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next())
            {
                TournamentMatchScheduleController tournamentMatchScheduleController = new TournamentMatchScheduleController();
                tournamentMatchScheduleController.tournament_name(tournament_title);
                tournamentMatchScheduleController.tournament_type_pass(tournament_type);
                tournamentMatchScheduleController.match_number(comb);
                tournamentMatchScheduleController.selected_team_list_pass(selected_list_recieve);
            }

            AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Tournament_Match_Schedule.fxml"));

            addtournamentanchorpane.getChildren().setAll(pane);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    static int factorial(int number_of_team) {
        int fact = 1;
        int i = 1;
        while(i <= number_of_team) {
            fact *= i;
            i++;
        }
        return fact;
    }

    public void seeavailableteams(ActionEvent actionEvent) throws IOException {
        FXMLLoader addNewItemLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample/AvailableTeamForTournament.fxml"));
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(addNewItemLoader.load()));
        secondStage.showAndWait();

    }

    public void passlist(ObservableList<String> selected_list) {
        System.out.println(selected_list);
        selected_list_recieve=selected_list;
        number_of_team=selected_list.size();
        System.out.println(number_of_team);
    }
}
