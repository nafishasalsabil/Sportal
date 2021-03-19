package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InsideBasketballFirstpageController {
    @FXML
    private AnchorPane basketball_anchorpane;
    public void obNavigationBackClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("sample/menu_page.fxml"));

        basketball_anchorpane.getChildren().setAll(pane);

    }
}
