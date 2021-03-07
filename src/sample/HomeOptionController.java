package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeOptionController {

    @FXML
    private AnchorPane homeAnchorPane1;

    @FXML
    private Button football_button;

    @FXML
    private Button cricket_button;

    @FXML
    private Button basketballButton;

    @FXML
    private Button volleyballButton;

    @FXML
    private Button badmintonButton;

    @FXML
    private Button hockeyButton;


    public void inside_cricket(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Cricket_MenuPage.fxml"));

        homeAnchorPane1.getChildren().setAll(pane);
    }
}
