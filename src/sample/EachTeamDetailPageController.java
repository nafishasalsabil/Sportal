package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EachTeamDetailPageController implements Initializable {
    @FXML
    private AnchorPane teamsAnchorPaneEach;
    private static String teamname1 = "abc";
    @FXML
    private Label teamName_Label;
    @FXML
    private Button add_player_button;

    @FXML
    private ImageView back_button_teams;
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
    @FXML
    private TableColumn<Each_Team_Detail_Class, String> player_course;
    @FXML
    private TextField search_textfield;

    ObservableList<Each_Team_Detail_Class> detail_list = FXCollections.observableArrayList();
    Each_Team_Detail_Class each_team_detail_class_obj;


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


    public void teamname(String selectedItem) {
        System.out.println("is it loading?");
        System.out.println(selectedItem);
        teamname1 = selectedItem;
        System.out.println("teamname function " + teamname1);
        setTeamname1(teamname1);

    }

    public String getTeamname1() {
        return teamname1;
    }

    public void setTeamname1(String teamname1) {
        this.teamname1 = teamname1;
    }

    public String test() {
        System.out.println("test" + teamname1.toString());
        return teamname1;
    }

    protected void init() {
        // this class is the HelloworldController class because init() is not private method
        // do init staff if you want
        // now FML fields are not null
        //  teamName_Label.setText(teamname1);
        System.out.println("working?");
        teamName_Label.setText(teamname1);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(teamName_Label.getText());
//        teamName_Label = new Label();
        String work = getTeamname1();
        System.out.println("work" + work);
      /*  EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.teamname(String test)
      */
        System.out.println("--------------------------------" + teamname1);
        init();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select team_id FROM team where team_name= ? ");
            System.out.println("Loading correctly " + teamname1);
            preparedStatement.setString(1, teamname1);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                System.out.println("team_id:" +
                        resultSet1.getString("team_id"));
                AddPlayerToTeamDialogBoxController addPlayerToTeamDialogBoxController = new AddPlayerToTeamDialogBoxController();
                addPlayerToTeamDialogBoxController.passing_clicked_team_id(resultSet1.getString("team_id"));

/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/
                PreparedStatement preparedStatement1 =
                        connection.prepareStatement("select player_name,player_age,player_course,player_gender,player_address,instituition_id,contact FROM players where team_id=?");
                preparedStatement1.setString(1, resultSet1.getString("team_id"));
                ResultSet resultSet = preparedStatement1
                        .executeQuery();
                while (resultSet.next()) {

                    System.out.println("playerName:" +
                            resultSet.getString("player_age"));

                    each_team_detail_class = new Each_Team_Detail_Class(Integer.parseInt(resultSet.getString("player_age")), resultSet.getString("player_name"), resultSet.getString("player_course"), resultSet.getString("player_gender"), resultSet.getString("player_address"), resultSet.getString("instituition_id"), resultSet.getString("contact"), resultSet.getString("player_course"));
                    detail_list.add(each_team_detail_class);

                }
//                detail_list.addAll(each_team_detail_class);


            }



            /*   (name = '" + s + "')*/

            /*while(resultSet1.next()){
                System.out.println("hi");
            }*/
            //  detail_list.addAll(each_team_detail_class);
            player_name.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_name"));
            player_age.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, Integer>("player_age"));
            player_roll.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_roll"));
            player_gender.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_gender"));
            player_address.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_address"));
            player_institution.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_institution"));
            player_contact.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_contact"));
            player_course.setCellValueFactory(new PropertyValueFactory<Each_Team_Detail_Class, String>("player_course"));

            for (int i = 0; i < detail_list.size(); i++) {
                System.out.println("dfgh");
            }

            table_team.setItems(detail_list);

            FilteredList <Each_Team_Detail_Class> filteredList= new FilteredList(detail_list,b->true);
            search_textfield.textProperty().addListener((observable,oldValue,newValue )->{
                filteredList.setPredicate(each_team_detail_class1 -> {
                    if(newValue==null || newValue.isEmpty())
                    {
                        return true;
                    }
                    String Lowercasefilter = newValue.toLowerCase();
                    if(each_team_detail_class1.getPlayer_name().toLowerCase().indexOf(Lowercasefilter)!=-1)
                        return true;
                    else if(each_team_detail_class1.getPlayer_roll().toLowerCase().indexOf(Lowercasefilter)!=-1)
                        return true;
                    else if(String.valueOf(player_age).indexOf(Lowercasefilter)!=-1)
                        return true;
                    else
                        return false;
                });
            });
            SortedList<Each_Team_Detail_Class> sortedList = new SortedList<Each_Team_Detail_Class>(filteredList);
            sortedList.comparatorProperty().bind(table_team.comparatorProperty());
            table_team.setItems(sortedList);
            addButtonToTable();


        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectMSSQL cnObj = new ConnectMSSQL();


    }

    private void addButtonToTable() {
        TableColumn<Each_Team_Detail_Class, String> colBtn = new TableColumn("Delete/Update");

        Callback<TableColumn<Each_Team_Detail_Class, String>, TableCell<Each_Team_Detail_Class, String>> cellFactory = new Callback<TableColumn<Each_Team_Detail_Class, String>, TableCell<Each_Team_Detail_Class, String>>() {


            @Override
            public TableCell<Each_Team_Detail_Class, String> call(final TableColumn<Each_Team_Detail_Class, String> param) {
                final TableCell<Each_Team_Detail_Class, String> cell = new TableCell<Each_Team_Detail_Class, String>() {
                    Image img = new Image("images/remove.PNG");
                    ImageView view1 = new ImageView();
                    Image img1 = new Image("images/updated.PNG");
                    ImageView view2 = new ImageView();


                    private final Button delete = new Button();
                    private final Button update = new Button();


                    {
                        view1.setFitHeight(30);
                        view1.setPreserveRatio(true);

                        view1.setImage(img);
                        delete.setGraphic(view1);
                        delete.setStyle("-fx-background-color: transparent");

                        view2.setFitHeight(30);
                        view2.setPreserveRatio(true);

                        view2.setImage(img1);
                        update.setGraphic(view2);
                        update.setStyle("-fx-background-color: transparent");


                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(delete, update);
                            setGraphic(pane);
                            delete.setOnAction((ActionEvent event) -> {
                                try {
                                    each_team_detail_class_obj = table_team.getSelectionModel().getSelectedItem();
                                    System.out.println(each_team_detail_class_obj.getPlayer_name()+" Try working?");
                                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                    Connection connection1 = DriverManager.getConnection(
                                            "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");
                                    PreparedStatement preparedStatement = connection1.prepareStatement("select player_id FROM players WHERE player_name= ?" );
                                    preparedStatement.setString(1, each_team_detail_class_obj.getPlayer_name());

                                    ResultSet resultSetforplayer = preparedStatement
                                            .executeQuery();
                                    while (resultSetforplayer.next()) {

                                        System.out.println("player_id:" +
                                                resultSetforplayer.getString("player_id"));

                                        UpdatePlayerToTeamDialogBoxController updatePlayerToTeamDialogBoxController = new UpdatePlayerToTeamDialogBoxController();
                                        updatePlayerToTeamDialogBoxController.player_id_recieved(resultSetforplayer.getString("player_id"));

                                        PreparedStatement preparedStatementfordelete = connection1.prepareStatement("DELETE FROM players WHERE player_id=?");
                                        preparedStatementfordelete.setInt(1, resultSetforplayer.getInt("player_id"));
                                        preparedStatementfordelete.execute();
                                        System.out.println("Deleted");
                                        refreshTable();


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
                            });
                            update.setOnAction((ActionEvent event) -> {

                                try {
                                    Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/UpdatePlayerToTeamDialogBox.fxml"));
                                    Scene scene = new Scene(parent);
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.initStyle(StageStyle.UTILITY);
                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(TeamsPageController.class.getName()).log(Level.SEVERE, null, ex);
                                }
/*
                                try {
                                    each_team_detail_class_obj = table_team.getSelectionModel().getSelectedItem();
                                    System.out.println(each_team_detail_class_obj.getPlayer_name()+" Try working?");
                                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                    Connection connection1 = DriverManager.getConnection(
                                            "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");
                                    PreparedStatement preparedStatement = connection1.prepareStatement("select player_id FROM players WHERE player_name= ?" );
                                    preparedStatement.setString(1, each_team_detail_class_obj.getPlayer_name());

                                    ResultSet resultSetforplayer = preparedStatement
                                            .executeQuery();
                                    while (resultSetforplayer.next()) {

                                        System.out.println("player_id:" +
                                                resultSetforplayer.getString("player_id"));
                                        String query = "DELETE FROM `players` WHERE player_id  =''";
                                        PreparedStatement preparedStatementfordelete = connection1.prepareStatement("Update players WHERE player_id=?");
                                        preparedStatementfordelete.setInt(1, resultSetforplayer.getInt("player_id"));
                                        preparedStatementfordelete.execute();
                                        System.out.println("Updated");
                                        refreshTable();


*/
/*
                                        ResultSet resultSetforplayerdelete = preparedStatementfordelete
                                                .executeQuery();
//                                    refreshTable();
                                        while (resultSetforplayerdelete.next()) {

                                            System.out.println("Deleted");
//                                    refreshTable();

                                        }
*//*



                                    }




                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(EachTeamDetailPageController.class.getName()).log(Level.SEVERE, null, ex);
                                }
*/
                            });
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table_team.getColumns().add(colBtn);

    }

    public void refreshTable() {
        System.out.println("Am i being called?");
        try{
            detail_list.clear();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select team_id FROM team where team_name= ? ");
            System.out.println("Loading correctly " + teamname1);
            preparedStatement.setString(1, teamname1);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {

                System.out.println("team_id:" +
                        resultSet1.getString("team_id"));
                AddPlayerToTeamDialogBoxController addPlayerToTeamDialogBoxController = new AddPlayerToTeamDialogBoxController();
                addPlayerToTeamDialogBoxController.passing_clicked_team_id(resultSet1.getString("team_id"));

                AddFootballPlayerToTeamDialogBoxController addFootballPlayerToTeamDialogBoxController = new AddFootballPlayerToTeamDialogBoxController();
                addFootballPlayerToTeamDialogBoxController.passing_clicked_team_id_to_football(resultSet1.getString("team_id"));
/*
                each_team_detail_class =new Each_Team_Detail_Class(Integer.parseInt( resultSet.getString("player_age")),resultSet.getString("player_name"),resultSet.getString("player_course"),resultSet.getString("player_gender"),resultSet.getString("player_address"),resultSet.getString("instituition_id"),resultSet.getString("contact"),resultSet.getString("player_course"));
                detail_list.add(each_team_detail_class);
*/
                PreparedStatement preparedStatement1 =
                        connection.prepareStatement("select player_name,player_age,player_course,player_gender,player_address,instituition_id,contact FROM players where team_id=?");
                preparedStatement1.setString(1, resultSet1.getString("team_id"));
                ResultSet resultSet = preparedStatement1
                        .executeQuery();
                while (resultSet.next()) {

                    System.out.println("playerName:" +
                            resultSet.getString("player_age"));

                    each_team_detail_class = new Each_Team_Detail_Class(Integer.parseInt(resultSet.getString("player_age")), resultSet.getString("player_name"), resultSet.getString("player_course"), resultSet.getString("player_gender"), resultSet.getString("player_address"), resultSet.getString("instituition_id"), resultSet.getString("contact"), resultSet.getString("player_course"));
                    detail_list.add(each_team_detail_class);


                }

            }
            table_team.setItems(detail_list);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void forAddingPlayerToTeam(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("sample/AddPlayerToTeamDialogBox.fxml"));

        try {

            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/AddPlayerToTeamDialogBox.fxml"));

/*
            AddPlayerToTeamDialogBoxController admissionController = fxmlLoader.getController();
            admissionController.setNewPlayer(table_team.getItems());
*/

            AddPlayerToTeamDialogBoxController addPlayerToTeamDialogBoxController = new AddPlayerToTeamDialogBoxController();
            addPlayerToTeamDialogBoxController.passTeamName(teamname1);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

/*
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/AddPlayerToTeamDialogBox.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            table_team.refresh();
*/
        } catch (IOException ex) {
            Logger.getLogger(TeamsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void back_button_each_team_pressed(MouseEvent mouseEvent) {

    }

    public void onTableRowclicked(MouseEvent mouseEvent) {
        each_team_detail_class_obj = table_team.getSelectionModel().getSelectedItem();
        each_team_detail_class_obj.getPlayer_name();
        System.out.println(each_team_detail_class_obj.getPlayer_name());
        try {
            each_team_detail_class_obj = table_team.getSelectionModel().getSelectedItem();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection1 = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            String query = "select player_id FROM players WHERE player_name= " + each_team_detail_class_obj.getPlayer_name();
            PreparedStatement preparedStatement = connection1.prepareStatement("select player_id FROM players WHERE player_name= ?" );
            preparedStatement.setString(1, each_team_detail_class_obj.getPlayer_name());

/*
            System.out.println("Loading correctly " + teamname1);
            preparedStatement.setString(1, teamname1);
*/
            ResultSet resultSetforplayer = preparedStatement
                    .executeQuery();
            while (resultSetforplayer.next()) {

                System.out.println("player_id:" +
                        resultSetforplayer.getString("player_id"));
//                                    refreshTable();
                UpdatePlayerToTeamDialogBoxController updatePlayerToTeamDialogBoxController = new UpdatePlayerToTeamDialogBoxController();
                updatePlayerToTeamDialogBoxController.player_id_recieved(resultSetforplayer.getString("player_id"));
                updatePlayerToTeamDialogBoxController.player_name_recieved(each_team_detail_class_obj.getPlayer_name());



            }


        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EachTeamDetailPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isadded(boolean b) {
        if(b=true)
        {
            table_team.getItems().clear();
            table_team.getItems().addAll(detail_list);        }
    }


    public void teamnamefromanotherwindow(String teamnamepassed, Each_Team_Detail_Class each_team_detail_class) {
        teamname1 = teamnamepassed;
        detail_list.addAll(each_team_detail_class);
        refreshTable();
    }
}
