package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllPanel implements Initializable {
    public AnchorPane ancerpane;
    public AnchorPane pane01;
    public Text IDdate;

    public void dashboard(ActionEvent actionEvent) {

        try {
            URL resource = getClass().getResource("/View/Pages/MainDash.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void Appointment(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/register.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SearchAppointment(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/searchPartion.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Billing(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/BillReport.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void PatientCharges(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/PatientCharges.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Help(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/HelpCenter.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Report(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/Report.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Logout(ActionEvent actionEvent) {
        try {
            URL resource = getClass().getResource("/View/Pages/exit.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date();
        try {
            URL resource = getClass().getResource("/View/Pages/MainDash.fxml");
            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            pane01.getChildren().clear();
            pane01.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void date(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/d");
        IDdate.setText(simpleDateFormat.format(date));
    }
}
