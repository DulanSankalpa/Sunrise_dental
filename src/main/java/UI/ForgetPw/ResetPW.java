package UI.ForgetPw;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ResetPW {
    public AnchorPane pane02;

    public void Createbtn(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Main_Page.fxml");
        assert resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            pane02.  getChildren().clear();
            pane02.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
