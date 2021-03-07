package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamsPageDuplicateController implements Initializable {
    @FXML
    private ListView<String> teams_listview;
    ObservableList<String> list = FXCollections.observableArrayList("TeamA","TeamB","TeamC");

    public void team_name_clicked(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
