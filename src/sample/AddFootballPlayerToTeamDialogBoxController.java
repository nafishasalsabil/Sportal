package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddFootballPlayerToTeamDialogBoxController {

    @FXML
    private TextField playernamefield;

    @FXML
    private TextField playeragefield;

    @FXML
    private TextField playerrollfield;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton others;

    @FXML
    private TextField playeraddressfield;

    @FXML
    private TextField playerinstitutionfield;

    @FXML
    private TextField playercontactfield;

    @FXML
    private TextField playercoursefield;

    @FXML
    private Button cancelplayerbutton;

    @FXML
    private Button addplayerbutton;
    public static String teamid="";
    @FXML
    void addPlayerButtonClicked(MouseEvent event) throws IOException {
        String name1 = playernamefield.getText();

        String age = playeragefield.getText();
        String roll = playerrollfield.getText();
        String gender_string ="";
        String address = playeraddressfield.getText();
        String inst = playerinstitutionfield.getText();
        String contact = playercontactfield.getText();
        String course = playercoursefield.getText();
        if(male.isSelected())
        {
            gender_string = "male";
        }
        else if(female.isSelected())
        {
            gender_string = "female";

        }
        else
        {
            gender_string = "others";

        }

/*
        System.out.println(coach);
        System.out.println(code);
        System.out.println(status);
        System.out.println(instname);
        System.out.println(instid);
*/
        int sports_id = 1;
        System.out.println(name1);
        if (name1.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill Team Name");
            alert.showAndWait();
        } else {

           /* getQuery();
            insert();
          */
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

                System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                PreparedStatement stmt = connection.prepareStatement("insert into players (player_name, player_age, player_course, player_address, player_gender, instituition_id, coach_id, sport_id, team_id,contact, player_code) values (?, ?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, name1);
                stmt.setInt(2, Integer.parseInt(age));
                stmt.setString(3,course );
                stmt.setString(4, address);
                stmt.setString(5, gender_string);
                stmt.setInt(6, sports_id);
                stmt.setInt(7, sports_id);
                stmt.setInt(8, sports_id);
                stmt.setInt(9, Integer.parseInt(teamid));
                stmt.setString(10,contact );
                stmt.setString(11, "cri");




                stmt.executeUpdate();
/*
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO team VALUES ( '%s' ,'%d','%d', '%s','%s','%s','%s')",name1,sports_id,Integer.parseInt(coach),instid,instname,status,code);
*/

            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());

            }
            clean();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            reload();
        }

    }

    @FXML
    void onPlayerCancelClicked(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private void reload() throws IOException {
        System.out.println("ooooooooooooooooooooooooooooooooooooooooo");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/EachTeamDetailPageController.fxml"));
        EachTeamDetailPageController controller = loader.getController();


        EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.refreshTable();

    }

    private void clean() {
        playeraddressfield.setText(null);
        gender.getSelectedToggle().setSelected(false);
        playeragefield.setText(null);
        playerinstitutionfield.setText(null);
        playercoursefield.setText(null);
        playercontactfield.setText(null);
        playernamefield.setText(null);
        playerrollfield.setText(null);



    }


    public void passing_clicked_team_id_to_football(String team_id) {
        teamid=team_id;
        System.out.println("Is team_id passing?"+teamid);
    }
}
