package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuPageController implements Initializable {

    public javafx.scene.control.Label gamenamelabel;
    @FXML
Button football_button;
    @FXML
    Button cricket_button;

    @FXML
    private AnchorPane menuAnchorPane;


    @FXML
    private AnchorPane homeAnchorPane;



    @FXML
    private Button homeButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button logoutButton;


    @FXML
    private BorderPane borderpane_menupage;
    @FXML
    private Button basketballButton;

    @FXML
    private Button volleyballButton;

    @FXML
    private Button badmintonButton;

    @FXML
    private Button hockeyButton;

    @FXML
    private Label label;

    public void inside_cricket(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Teams_Page.fxml"));

        menuAnchorPane.getChildren().setAll(pane);
       // loadpage("Teams_Page");
        gamenamelabel.setText("Cricket");
        gamenamelabel.setVisible(true);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     //   cricket_button.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #aaaaaa;");

    }


    public void cricketHover(MouseEvent mouseEvent) {
        cricket_button.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #aaaaaa;");
    }

    public void footballHover(MouseEvent mouseEvent) {
//        football_button.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #aaaaaa;");

    }

    public void onProfileButtonClicked(ActionEvent actionEvent) throws IOException {
/*
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/profile_option.fxml"));

        homeAnchorPane.getChildren().setAll(pane);
*/
        profileButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #ffffff; ");
        homeButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        settingsButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        logoutButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");

    }

    public void onHomeButtonClicked(ActionEvent actionEvent) throws IOException {
/*
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/home_option.fxml"));

        homeAnchorPane.getChildren().setAll(pane);
*/
    //    loadpage("home_option");
        homeButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #ffffff; ");
        profileButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        settingsButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        logoutButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");

    }

    private void loadpage(String page) {
        Parent root = null;
        try{
            root=FXMLLoader.load(getClass().getClassLoader().getResource("sample/"+page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpane_menupage.setCenter(root);
    }

    public void profileClicked(MouseEvent mouseEvent) {
        loadpage("profile_option");
    }

    public void homeClicked(MouseEvent mouseEvent) {
        borderpane_menupage.setCenter(homeAnchorPane);


    }

    public void settingsClicked(MouseEvent mouseEvent) {
        loadpage("settings_option");
        settingsButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #ffffff; ");
        profileButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        homeButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        logoutButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");


    }

    public void logoutclicked(MouseEvent mouseEvent) {
        loadpage("logout_option");
        logoutButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #ffffff; ");
        profileButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        settingsButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");
        homeButton.setStyle("-fx-border-width:   0px 0px 0px 2px; -fx-border-color: #30475E; ");


    }
}
