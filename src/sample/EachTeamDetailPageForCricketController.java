package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EachTeamDetailPageForCricketController implements Initializable {

    @FXML
    private AnchorPane teamsAnchorPaneEach;

    @FXML
    private Label teamName_Label;

    @FXML
    private Button add_player_button;

    @FXML
    private ImageView back_button_teams;

    @FXML
    private TextField search_textfield;
    public static String teamname1="";

    @FXML
    private ListView<String> goalkeepers_listview;
    ObservableList<String> batsmanlist = FXCollections.observableArrayList();
    ObservableList<String> bowlerlist = FXCollections.observableArrayList();
    ObservableList<String> allrounderlist = FXCollections.observableArrayList();


    @FXML
    private ListView<String> defenders_listview;
    private final Image IMAGE_MEN = new Image("images/football.png");
/*
    private final Image IMAGE_WOMEN = new Image("women.png");
*/
@FXML
private VBox teamVbox;
    @FXML
    private Button squad_button;

    @FXML
    private Button matches_button;

    @FXML
    private Button standings_button;
    private Image[] listOfImages = {IMAGE_MEN};

    @FXML
    private ListView<String> midfielders_listview;

    @FXML
    private ListView<String> attackers_listview;

    @FXML
    private TextField search_name_label;

    @FXML
    private TextField min_age;

    @FXML
    private TextField max_age;
    @FXML
    private Button search_button;
    public static int id_team;
    @FXML
    void back_button_each_team_pressed(MouseEvent event) {

    }


    @FXML
    private ListView<String> batsman_listview;

    @FXML
    private ListView<String> bowler_listview;

    @FXML
    private ListView<String> allrounder_listview;
    @FXML
    void search_pressed(ActionEvent event) {

        batsmanlist.clear();
        bowlerlist.clear();
        allrounderlist.clear();

        int minage,maxage;
        String name1="";

        String name = search_name_label.getText();
        String age_min=min_age.getText();
        String age_max=max_age.getText();
        if(name.isEmpty()){
            name="";
            System.out.println(name);
        }
        else{
            name1="%"+name+"%";
            System.out.println(name1);
        }
        if(age_min.isEmpty()){
             minage=0;
            System.out.println(minage);

        }
        else{
             minage=Integer.parseInt(age_min);
            System.out.println(minage);
        }
        if(age_max.isEmpty()){
             maxage=100;
            System.out.println(maxage);
        }
        else{
             maxage=Integer.parseInt(age_max);
            System.out.println(maxage);
        }



        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            String tyuh = "Team Uno-R2";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatementP = connection.prepareStatement("SELECT players.player_name,players.player_role FROM players WHERE (players.sport_id=2 and team_id=?) and (players.player_name LIKE '%'+?+'%' and players.player_age BETWEEN ? AND ? );");

            preparedStatementP.setInt(1, id_team);
            preparedStatementP.setString(2,name);
            preparedStatementP.setInt(3, minage);
            preparedStatementP.setInt(4, maxage);

            ResultSet resultSetP=preparedStatementP.executeQuery();

            while(resultSetP.next()){

                System.out.println(resultSetP.getString("player_name"));
                System.out.println(resultSetP.getString("player_role"));
                if(resultSetP.getString("player_role").equals("Batsman")){
                    batsmanlist.add(resultSetP.getString("player_name"));
                }
                if(resultSetP.getString("player_role").equals("Bowler")){
                    bowlerlist.add(resultSetP.getString("player_name"));
                }
                if(resultSetP.getString("player_role").equals("All Rounder")){
                    allrounderlist.add(resultSetP.getString("player_name"));
                }


            }










        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Each_Team_Detail_Class each_team_detail_class;
    public void team_name_clicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + batsman_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        TeamNameClass teamNameClass = new TeamNameClass();
        EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.teamname(batsman_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/FootballPlayerProfile.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);
    /*    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/FootballPlayerProfile.fxml"));
            Stage stage = (Stage) btn.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
*/

    }
    private void loadpage(String page) {
        Parent root = null;
        try{
            root=FXMLLoader.load(getClass().getClassLoader().getResource("sample/"+page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        borderpaneCricket.setCenter(root);
    }

    public void team_name_clicked_defender(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + defenders_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        TeamNameClass teamNameClass = new TeamNameClass();
        EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.teamname(goalkeepers_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/FootballPlayerProfile.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);



    }



    public void player_name_clicked_batsman(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + batsman_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        TeamNameClass teamNameClass = new TeamNameClass();
/*
        EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.teamname(goalkeepers_listview.getSelectionModel().getSelectedItem());
*/
        CricketPlayerProfileController footballPlayerProfileController = new CricketPlayerProfileController();
        footballPlayerProfileController.player_name(batsman_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/CricketPlayerProfile.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);


    }

    public void player_name_clicked_bowler(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + bowler_listview.getSelectionModel().getSelectedItem());


        FootballPlayerProfileController footballPlayerProfileController = new FootballPlayerProfileController();
        footballPlayerProfileController.player_name(bowler_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/CricketPlayerProfile.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);
    }



    public void player_name_clicked_allrounder(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + allrounder_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        TeamNameClass teamNameClass = new TeamNameClass();
/*
        EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.teamname(goalkeepers_listview.getSelectionModel().getSelectedItem());
*/
        FootballPlayerProfileController footballPlayerProfileController = new FootballPlayerProfileController();
        footballPlayerProfileController.player_name(allrounder_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/CricketPlayerProfile.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);
    }

    public void teamname(String selectedItem) {
        teamname1 = selectedItem;
    }
    protected void init()
    {
        teamName_Label.setText(teamname1);
    }

    public void ss(MouseEvent mouseEvent) throws IOException {
        System.out.println("clicked on " + allrounder_listview.getSelectionModel().getSelectedItem());
      /*  AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Each_Team_Detail_Page.fxml"));

        teamsAnchorPane.getChildren().setAll(pane);*/
        TeamNameClass teamNameClass = new TeamNameClass();
/*
        EachTeamDetailPageController eachTeamDetailPageController = new EachTeamDetailPageController();
        eachTeamDetailPageController.teamname(goalkeepers_listview.getSelectionModel().getSelectedItem());
*/
        FootballPlayerProfileController footballPlayerProfileController = new FootballPlayerProfileController();
        footballPlayerProfileController.player_name(allrounder_listview.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/CricketPlayerProfile.fxml"));

        teamsAnchorPaneEach.getChildren().setAll(pane);
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
    class PlayerListCell extends ListCell<Player> {
        private HBox content;
        private Text name;
        private Text age;

        public PlayerListCell() {
            super();
            name = new Text();
            age = new Text();
            VBox vBox = new VBox(name, age);
            content = new HBox(new ImageView("images/football.png"), vBox);
            content.setSpacing(10);
        }



    }
    @FXML
    void forAddingPlayerToTeam(MouseEvent event) {
        try {

            FXMLLoader addNewItemLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample/AddCricketPlayerToTeamDialogBox.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(addNewItemLoader.load()));
            AddCricketPlayerToTeamDialogBoxController addNewItemController = addNewItemLoader.getController();
            secondStage.showAndWait();

         /*   Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/AddPlayerToTeamDialogBox.fxml"));

            AddFootballPlayerToTeamDialogBoxController addPlayerToTeamDialogBoxController = new AddFootballPlayerToTeamDialogBoxController();
//            addPlayerToTeamDialogBoxController.passTeamName(teamname1);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
*/
            Optional<String> result = addNewItemController.getNewItem();
            Optional<String> result1 = addNewItemController.getNewItem1();
            if (result.isPresent() && result1.isPresent()) {
                if(result1.get().equals("Batsman"))
                {
                    System.out.println("Your name: " + result.get());
                    batsmanlist.add(result.get());
                    batsman_listview.setItems(batsmanlist);
                    System.out.println("--------------"+result.get());


/*                Label l = new Label(result.get());
                l.setGraphic(new ImageView(new Image("images/football.png")));
                playerlist.add(String.valueOf(l));*/

//                goalkeepers_listview.setItems(playerlist);
                    batsman_listview.setCellFactory(listView -> new ListCell<String>() {
                        private ImageView imageView = new ImageView();
                        @Override
                        public void updateItem(String friend, boolean empty) {
                            super.updateItem(friend, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                Image image = new Image("images/soccerjersey.png");
                                imageView.setImage(image);
                                imageView.setFitWidth(40);
                                imageView.setFitHeight(40);
                                setText(friend);
                                setGraphic(imageView);                            }

                        }
                    });
                }
               else if(result1.get().equals("Bowler"))
                {
                    System.out.println("Your name: " + result.get());
                    bowlerlist.add(result.get());
                    bowler_listview.setItems(bowlerlist);
                    System.out.println("--------------"+result.get());


/*                Label l = new Label(result.get());
                l.setGraphic(new ImageView(new Image("images/football.png")));
                playerlist.add(String.valueOf(l));*/

//                goalkeepers_listview.setItems(playerlist);
                    bowler_listview.setCellFactory(listView -> new ListCell<String>() {
                        private ImageView imageView = new ImageView();
                        @Override
                        public void updateItem(String friend, boolean empty) {
                            super.updateItem(friend, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                Image image = new Image("images/soccerjersey.png");
                                imageView.setImage(image);
                                imageView.setFitWidth(40);
                                imageView.setFitHeight(40);
                                setText(friend);
                                setGraphic(imageView);                            }

                        }
                    });
                }
               else if(result1.get().equals("All Rounder"))
                {
                    System.out.println("Your name: " + result.get());
                    allrounderlist.add(result.get());
                    allrounder_listview.setItems(allrounderlist);
                    System.out.println("--------------"+result.get());


/*                Label l = new Label(result.get());
                l.setGraphic(new ImageView(new Image("images/football.png")));
                playerlist.add(String.valueOf(l));*/

//                goalkeepers_listview.setItems(playerlist);
                    allrounder_listview.setCellFactory(listView -> new ListCell<String>() {
                        private ImageView imageView = new ImageView();
                        @Override
                        public void updateItem(String friend, boolean empty) {
                            super.updateItem(friend, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                Image image = new Image("images/soccerjersey.png");
                                imageView.setImage(image);
                                imageView.setFitWidth(40);
                                imageView.setFitHeight(40);
                                setText(friend);
                                setGraphic(imageView);                            }

                        }
                    });
                }



            }
        }catch (IOException ex) {
            Logger.getLogger(TeamsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        batsman_listview.setItems(batsmanlist);
        bowler_listview.setItems(bowlerlist);
        allrounder_listview.setItems(allrounderlist);


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=sportalDB;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());



            PreparedStatement preparedStatement = connection.prepareStatement("select team_id FROM team where team_name= ? ");
            System.out.println("Loading correctly " + teamname1);
            preparedStatement.setString(1, teamname1);
            ResultSet resultSet1 = preparedStatement
                    .executeQuery();
            while (resultSet1.next()) {
                id_team= Integer.parseInt(resultSet1.getString("team_id"));

                System.out.println("team_id:" +
                        resultSet1.getString("team_id"));
                AddCricketPlayerToTeamDialogBoxController addPlayerToTeamDialogBoxController = new AddCricketPlayerToTeamDialogBoxController();
                addPlayerToTeamDialogBoxController.passing_clicked_team_id(resultSet1.getString("team_id"));


                PreparedStatement preparedStatement1 =
                        connection.prepareStatement("select player_name,player_age,player_role,player_gender,player_address,instituition_id,contact,player_height,player_weight,player_code FROM players where team_id=?");
                preparedStatement1.setString(1, resultSet1.getString("team_id"));
                ResultSet resultSet = preparedStatement1
                        .executeQuery();
                while (resultSet.next()) {

                    System.out.println("playerName:" +
                            resultSet.getString("player_age"));
                    if(resultSet.getString("player_role").equals("Batsman"))
                    {
                        batsmanlist.add(resultSet.getString("player_name"));
                        System.out.println(resultSet.getString("player_role"));
                        batsman_listview.setCellFactory(listView -> new ListCell<String>() {
                            private ImageView imageView = new ImageView();
                            @Override
                            public void updateItem(String friend, boolean empty) {
                                super.updateItem(friend, empty);
                                if (empty) {
                                    setText(null);
                                    setGraphic(null);
                                } else {
                                    Image image = new Image("images/soccerjersey.png");
                                    imageView.setImage(image);
                                    imageView.setFitWidth(40);
                                    imageView.setFitHeight(40);
                                    setText(friend);
                                    setGraphic(imageView);                            }

                            }
                        });


                    }
                    else if(resultSet.getString("player_role").equals("Bowler"))
                    {
                        bowlerlist.add(resultSet.getString("player_name"));
                        System.out.println(resultSet.getString("player_role"));
                        bowler_listview.setCellFactory(listView -> new ListCell<String>() {
                            private ImageView imageView = new ImageView();
                            @Override
                            public void updateItem(String friend, boolean empty) {
                                super.updateItem(friend, empty);
                                if (empty) {
                                    setText(null);
                                    setGraphic(null);
                                } else {
                                    Image image = new Image("images/soccerjersey.png");
                                    imageView.setImage(image);
                                    imageView.setFitWidth(40);
                                    imageView.setFitHeight(40);
                                    setText(friend);
                                    setGraphic(imageView);                            }

                            }
                        });


                    }


                    else if(resultSet.getString("player_role").equals("All Rounder"))
                    {
                        allrounderlist.add(resultSet.getString("player_name"));
                        System.out.println(resultSet.getString("player_role"));
                        allrounder_listview.setCellFactory(listView -> new ListCell<String>() {
                            private ImageView imageView = new ImageView();
                            @Override
                            public void updateItem(String friend, boolean empty) {
                                super.updateItem(friend, empty);
                                if (empty) {
                                    setText(null);
                                    setGraphic(null);
                                } else {
                                    Image image = new Image("images/soccerjersey.png");
                                    imageView.setImage(image);
                                    imageView.setFitWidth(40);
                                    imageView.setFitHeight(40);
                                    setText(friend);
                                    setGraphic(imageView);                            }

                            }
                        });


                    }


                }
//                detail_list.addAll(each_team_detail_class);


            }

            init();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
