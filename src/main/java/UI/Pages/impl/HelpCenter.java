package UI.Pages.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class HelpCenter  {
    public AnchorPane anchepane;


    public void btnRegister(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Pages/Help/RegisterHelp.fxml");
        assert  resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            anchepane.getChildren().clear();
            anchepane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSearch(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Pages/Help/SearchHelp.fxml");
        assert  resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            anchepane.getChildren().clear();
            anchepane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCreateBill(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Pages/Help/CalHelp.fxml");
        assert  resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            anchepane.getChildren().clear();
            anchepane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPartintCharger(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Pages/Help/PartionChargesHelp.fxml");
        assert  resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            anchepane.getChildren().clear();
            anchepane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReport(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Pages/Help/ReportHelp.fxml");
        assert  resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            anchepane.getChildren().clear();
            anchepane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/Pages/Help/ExitHelp.fxml");
        assert  resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            anchepane.getChildren().clear();
            anchepane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
