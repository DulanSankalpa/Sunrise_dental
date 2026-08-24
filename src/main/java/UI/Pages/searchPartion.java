package UI.Pages;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class searchPartion implements Initializable {
    public JFXComboBox treatmenttypecmb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treatmenttypecmb.setItems(FXCollections.observableArrayList(
                Arrays.asList(
                       "Teeth Cleaning",
                        "Teeth Whitening",
                        "Tooth Filling",
                        "Tooth Extraction",
                        "Root Canal Treatment",
                        "Dental Checkup",
                        "Dental X-Ray",
                        "Dental Crown",
                        "Dental Bridge",
                        "Dental Implant",
                        "Denture",
                        "Braces",
                        "Orthodontic Treatment",
                        "Scaling",
                        "Polishing",
                        "Fluoride Treatment",
                        "Child Dental Care",
                        "Emergency Dental Care",
                        "Toothache Treatment",
                        "Oral Examination"
                )
        ));
    }
}
