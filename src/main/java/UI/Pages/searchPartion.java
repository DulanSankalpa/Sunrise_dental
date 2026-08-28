package UI.Pages;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class searchPartion implements Initializable {
    public JFXComboBox treatmenttypecmb;
    public JFXTextField Find_ID;
    public  TextField txtA_Num;
    public TextField txtP_Name;
    public TextField txtAddress;
    public TextField txtContact_Num;
    public JFXComboBox cmbDocter;
    public JFXComboBox cmbPayment;

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

    public void btnEdit(ActionEvent actionEvent) {
    }

    public void btnSearch(ActionEvent actionEvent) {

    }
    private void dataFill(ViewData data) {

    }
}
