package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InsideHockeyFirstpageController {
    @FXML
    private AnchorPane hockey_anchorpane;
    public void obNavigationBackClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/menu_page.fxml"));

        hockey_anchorpane.getChildren().setAll(pane);


    }
}
