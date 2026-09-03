package UI.Pages.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.register.Register;
import Dental.ICBT.Application.Service.Custom.RegisterAppoitment;
import Dental.ICBT.Application.Service.Custom.impl.RegisterAppoitment_impl;
import Dental.ICBT.Application.Service.ServiceFactory;
import Dental.ICBT.Application.Util.ServiceType;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class register implements Initializable {
    public JFXComboBox treatmenttypecmb;
    public JFXComboBox cmbDocter;
    public TextField appoitment_no;
    public TextField Patient_Name;
    public TextField address;
    public TextField contactNumber;


   RegisterAppoitment registerAppoitment =  ServiceFactory.getInstance().getServiceType(ServiceType.REGISTER);

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

        cmbDocter.setItems(FXCollections.observableArrayList(
                Arrays.asList(
                        "Dr.Kasun",
                        "Dr.Niroth",
                        "Dr.Perera",
                        "Dr.Sudhath",
                        "Dr.Kalpani",
                        "Dr.Dulan",
                        "Dr.Hashara",
                        "Dr.Tharuka",
                        "Dr.Gamini"
                )
        ));

    }

    public void saveAppoitment(ActionEvent actionEvent) {
        String id = appoitment_no.getText();
        String patient = Patient_Name.getText();
        String Custaddress = address.getText();
        String number = contactNumber.getText();
        String docter = cmbDocter.getValue().toString();
        String treet = treatmenttypecmb.getValue().toString();

        Register datasheet = new Register(id,patient,Custaddress,number,docter,treet);

       if(registerAppoitment.saveAppoitment(datasheet)){
           new Alert(Alert.AlertType.INFORMATION,"Complete Added").show();
       }else{
           new Alert(Alert.AlertType.ERROR,"Fiil Added").show();
       }


    }

    public void clean(ActionEvent actionEvent) {
        appoitment_no.setText("");
        Patient_Name.setText("");
        address.setText("");
        contactNumber.setText("");
        cmbDocter.setValue(null);
        treatmenttypecmb.setValue(null);
    }


}