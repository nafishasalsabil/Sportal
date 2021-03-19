package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatePlayerToTeamDialogBoxController implements Initializable {

    @FXML
    private TextField playernamefieldupdate;

    @FXML
    private TextField playeragefieldupdate;

    @FXML
    private TextField playerrollfieldupdate;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton others;

    @FXML
    private TextField playeraddressfieldupdate;

    @FXML
    private TextField playerinstitutionfieldupdate;

    @FXML
    private TextField playercontactfieldupdate;

    @FXML
    private TextField playercoursefieldupdate;

    @FXML
    private Button updateplayerbutton;
    Each_Team_Detail_Class each_team_detail_class_obj = null;
public static String plaey_id_selected="";
    public static String player_name_selected="";
    @FXML
    void addPlayerButtonClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
/*
            each_team_detail_class_obj = table_team.getSelectionModel().getSelectedItem();
            System.out.println(each_team_detail_class_obj.getPlayer_name()+" Try working?");
*/
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection1 = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");
            PreparedStatement preparedStatement = connection1.prepareStatement("select * FROM players WHERE player_id= ?" );
            preparedStatement.setInt(1, Integer.parseInt(plaey_id_selected));

            ResultSet resultSetforplayer = preparedStatement
                    .executeQuery();
            while (resultSetforplayer.next()) {

                playernamefieldupdate.setText(resultSetforplayer.getString("player_name"));
                playeragefieldupdate.setText(Integer.toString(resultSetforplayer.getInt("player_age")));
                playerrollfieldupdate.setText(resultSetforplayer.getString("player_course"));
                playeraddressfieldupdate.setText(resultSetforplayer.getString("player_address"));
//                playerinstitutionfieldupdate.setText(Integer.toString(resultSetforplayer.getInt("instituition_id")));
                playercontactfieldupdate.setText(resultSetforplayer.getString("contact"));
                playercoursefieldupdate.setText(resultSetforplayer.getString("player_course"));
                if(resultSetforplayer.getString("player_gender").equals("male"))
                {
                    male.setSelected(true);
                    female.setSelected(false);
                    others.setSelected(false);

                }
                else if(resultSetforplayer.getString("player_gender").equals("female"))
                {
                    male.setSelected(false);
                    female.setSelected(true);
                    others.setSelected(false);
                }
                if(resultSetforplayer.getString("player_gender").equals("others"))
                {
                    male.setSelected(false);
                    female.setSelected(false);
                    others.setSelected(true);
                }

/*
                                        ResultSet resultSetforplayerdelete = preparedStatementfordelete
                                                .executeQuery();
//                                    refreshTable();
                                        while (resultSetforplayerdelete.next()) {

                                            System.out.println("Deleted");
//                                    refreshTable();

                                        }
*/


            }




        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EachTeamDetailPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void player_id_recieved(String player_id) {
        plaey_id_selected = player_id;
        System.out.println(plaey_id_selected);
    }


    public void updatePlayerButtonClicked(javafx.scene.input.MouseEvent mouseEvent) {
        String name1 = playernamefieldupdate.getText();

        String age = playeragefieldupdate.getText();
        String roll = playerrollfieldupdate.getText();
        String gender_string ="";
        String address = playeraddressfieldupdate.getText();
        String contact = playercontactfieldupdate.getText();
        String course = playercoursefieldupdate.getText();
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

        try {
/*
            each_team_detail_class_obj = table_team.getSelectionModel().getSelectedItem();
            System.out.println(each_team_detail_class_obj.getPlayer_name()+" Try working?");
*/
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection1 = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");
            PreparedStatement preparedStatement = connection1.prepareStatement("select player_id FROM players WHERE player_name= ?" );
            preparedStatement.setString(1, player_name_selected);

            ResultSet resultSetforplayer = preparedStatement
                    .executeQuery();
            while (resultSetforplayer.next()) {

                System.out.println("player_id:" +
                        resultSetforplayer.getString("player_id"));
                PreparedStatement preparedStatementfordelete = connection1.prepareStatement("Update players set player_name=?,player_age=?,player_gender=?,player_course=?,player_address=?,contact=?  WHERE player_id=?");
                preparedStatementfordelete.setString(1, name1);
                preparedStatementfordelete.setInt(2, Integer.parseInt(age));
                preparedStatementfordelete.setString(3, gender_string);
                preparedStatementfordelete.setString(4, course);
                preparedStatementfordelete.setString(5, address);
                preparedStatementfordelete.setString(6, contact);
                preparedStatementfordelete.setInt(7, resultSetforplayer.getInt("player_id"));

                preparedStatementfordelete.execute();
                System.out.println("Updated");
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.close();
                EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
                eachTeamDetailPageController.refreshTable();



/*
                                        ResultSet resultSetforplayerdelete = preparedStatementfordelete
                                                .executeQuery();
//                                    refreshTable();
                                        while (resultSetforplayerdelete.next()) {

                                            System.out.println("Deleted");
//                                    refreshTable();

                                        }
*/


            }




        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EachTeamDetailPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void player_name_recieved(String player_name) {
        player_name_selected = player_name;
    }
}
