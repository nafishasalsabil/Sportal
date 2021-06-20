package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCricketPlayerToTeamDialogBoxController implements Initializable {

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
    private ComboBox institution_combobox;

    @FXML
    private TextField playercontactfield;

    @FXML
    private TextField playerheightfield;

    @FXML
    private TextField playerweightfield;

    @FXML
    private TextField jerseynofield;

    @FXML
    private Button cancelplayerbutton;

    @FXML
    private Button addplayerbutton;

    public static String teamid="";
    Optional<String> returnValue;
    Optional<String> returnValue1;
    ObservableList<String> institution_list = FXCollections.observableArrayList();
    public static String inst="";
    public static int team_id_conv;

    @FXML
    void addPlayerButtonClicked(MouseEvent event) {
        String name1 = playernamefield.getText();
        returnValue = Optional.of(playernamefield.getText());
        String age = playeragefield.getText();
        int age_conv=Integer.parseInt(age);
        String role = playerrollfield.getText();
        returnValue1=Optional.of(playerrollfield.getText());
        String gender_string ="";
        String address = playeraddressfield.getText();
        String contact = playercontactfield.getText();
        String height = playerheightfield.getText();
        String weight = playerweightfield.getText();
        String jersey = jerseynofield.getText();
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


        int sports_id = 2;
        System.out.println(name1);
        if (name1.isEmpty() || age.isEmpty()||role.isEmpty()||gender_string.isEmpty()||address.isEmpty()||contact.isEmpty()||height.isEmpty()||weight.isEmpty()||jersey.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All the Details");
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
                Statement statement = connection.createStatement();

                PreparedStatement preparedStatement = connection.prepareStatement("select instituition_id FROM instituition where instituition_name=?;");
                System.out.println("Loading correctly " + inst);
                preparedStatement.setString(1, inst);
                ResultSet resultSet = preparedStatement
                        .executeQuery();

                while (resultSet.next()) {


                    institution_list.add(resultSet.getString("instituition_id"));
                    int inst_id=Integer.parseInt(resultSet.getString("instituition_id"));
                    System.out.println(resultSet.getString("instituition_id"));

                    PreparedStatement stmt =
                            connection.prepareStatement("insert into players (player_name, player_age, player_role, player_address, player_gender, instituition_id,  sport_id, team_id,player_height, player_weight,contact, player_code) values (?,?,?,?,?,?,?,?,?,?,?,?)");
                    stmt.setString(1, name1);
                    stmt.setInt(2,age_conv );
                    stmt.setString(3,role );
                    stmt.setString(4, address);
                    stmt.setString(5, gender_string);
                    stmt.setInt(6, inst_id);
                    stmt.setInt(7, sports_id);
                    stmt.setInt(8,team_id_conv);
                    stmt.setString(9,height );
                    stmt.setString(10, weight);
                    stmt.setString(11, contact);
                    stmt.setString(12, jersey);
                    stmt.executeUpdate();                }






/*
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO team VALUES ( '%s' ,'%d','%d', '%s','%s','%s','%s')",name1,sports_id,Integer.parseInt(coach),instid,instname,status,code);
*/

            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());

            }
//            clean();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
//            reload();
        }

    }

    @FXML
    void onInstitutionselected(ActionEvent event) {
        System.out.println(institution_combobox.getSelectionModel().getSelectedItem().toString());
        inst = institution_combobox.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void onPlayerCancelClicked(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        institution_combobox.setItems(institution_list);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select instituition_name FROM instituition;");


            while (resultSet.next()) {

              /*  System.out.println("teamName:" +
                        resultSet.getString("player_age"));
*/

                institution_list.add(resultSet.getString(1));
                System.out.println(resultSet.getString(1));

            }




        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectMSSQL cnObj = new ConnectMSSQL();

    }

    public void passing_clicked_team_id(String team_id) {
        teamid=team_id;
        System.out.println("Is team_id passing?"+teamid);
        team_id_conv =  Integer.parseInt(teamid);
        System.out.println(team_id_conv);
    }
    private static class Player {
        private String player_name;
        private int player_age;

        public String getPlayer_name() {
            return player_name;
        }

        public int getPlayer_age() {
            return player_age;
        }


        public Player(String name, int age) {
            super();
            this.player_name = name;
            this.player_age = age;
        }
    }
    public Optional<String> getNewItem()
    {
        return returnValue;
    }

    public Optional<String> getNewItem1()
    {
        return returnValue1;
    }
}
