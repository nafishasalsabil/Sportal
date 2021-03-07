import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class sampleController {
    @FXML
    private void printHelloWorld(ActionEvent event) {
        event.consume();
        System.out.println("Hello, World!");
    }
}
