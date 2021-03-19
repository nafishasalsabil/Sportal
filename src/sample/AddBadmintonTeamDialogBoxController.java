package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;

public class AddBadmintonTeamDialogBoxController {

    @FXML
    private TextField teamnamefield;

    @FXML
    private TextField coachidfield;

    @FXML
    private TextField institutionidfiled;

    @FXML
    private TextField institionnamefield;

    @FXML
    private TextField teamstatusfield;

    @FXML
    private TextField teamcodefield;

    @FXML
    private Button cancelbutton;

    @FXML
    private Button addbutton;
    public static String name1="";
    public static String code="";
    public static String status="";
    public static String coach="";
    public static String instname="";
    public static String instid="";
    Optional<String> returnValue;

    @FXML
    void addButtonClicked(MouseEvent event) {

        name1 = teamnamefield.getText().toString();
        returnValue = Optional.of(teamnamefield.getText());
        TeamNameClass teamNameClass = new TeamNameClass();

        teamNameClass.setTeam_name(name1);
        System.out.println("-------"+teamNameClass.getTeam_name());

        code = teamcodefield.getText();
        status = teamstatusfield.getText();
        coach = coachidfield.getText();
        instname = institionnamefield.getText();
        instid = institutionidfiled.getText();
        System.out.println(coach);
        System.out.println(code);
        System.out.println(status);
        System.out.println(instname);
        System.out.println(instid);
        final int sports_id=3;
        System.out.println(name1);
        if(name1.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill Team Name");
            alert.showAndWait();
        }
        else {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                System.out.println("DATABASE NAME IS-------------:" + connection.getMetaData().getDatabaseProductName());

                PreparedStatement stmt = connection.prepareStatement("insert into team (team_name, sport_id,coach_id,instituition_id,instituition_name,team_status,team_code) values (?,?,?,?,?,?,?)");
                stmt.setString(1, name1);
                stmt.setInt(2, sports_id);
                stmt.setInt(3, Integer.parseInt(coach));
                stmt.setInt(4, Integer.parseInt(instid));
                stmt.setString(5, instname);
                stmt.setString(6, status);
                stmt.setString(7, code);
                stmt.executeUpdate();
            /*    TeamsPageController teamsPageController = new TeamsPageController();
                teamsPageController.getAllinfo(name1,code,status,coach,instname,instid);
            */    System.out.println("-------"+teamNameClass.getTeam_name());

/*
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO team VALUES ( '%s' ,'%d','%d', '%s','%s','%s','%s')",name1,sports_id,Integer.parseInt(coach),instid,instname,status,code);
*/


            } catch (Exception e) {
                System.err.println("Got an exception! ????????????");
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);

            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
          /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/TeamsPageController.fxml"));
            TeamsPageController teamsPageController = loader.getController();
            teamsPageController.name_method(name1);*/
        }
    }

    @FXML
    void onCancelClicked(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
    public Optional<String> getNewItem()
    {
        return returnValue;
    }


}
