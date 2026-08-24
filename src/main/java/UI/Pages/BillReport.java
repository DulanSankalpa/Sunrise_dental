package UI.Pages;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BillReport implements Initializable {
    public ComboBox payMethod;
    public ComboBox payStates;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        payMethod.setItems(FXCollections.observableArrayList(
                Arrays.asList("Cash","Card","Bank transfer")
        ));
        payMethod.getSelectionModel().select(0);

        payStates.setItems(FXCollections.observableArrayList(
                Arrays.asList("Pending","Complete")
        ));
        payStates.getSelectionModel().select(0);
    }
}
