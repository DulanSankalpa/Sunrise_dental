package UI.Pages;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Model.register.Register;
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

        System.out.println(id);
        System.out.println(patient);
        System.out.println(Custaddress);
        System.out.println(number);
        System.out.println(docter);
        System.out.println(treet);

        Register datasheet = new Register(id,patient,Custaddress,number,docter,treet);
        System.out.println(datasheet);
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appoitment (appoitmentnumber,patientname,address,NUMBER,dentistname,treatmenttype) values (?,?,?,?,?,?)");

            preparedStatement.setString(1,datasheet.getAppoitmenrt());
            preparedStatement.setString(2,datasheet.getPation());
            preparedStatement.setString(3,datasheet.getAddress());
            preparedStatement.setString(4,datasheet.getNumber());
            preparedStatement.setString(5,datasheet.getDentis());
            preparedStatement.setString(6,datasheet.getType());



            if(preparedStatement.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Register Complete").show();
                appoitment_no.setText("");
                Patient_Name.setText("");
                address.setText("");
                contactNumber.setText("");
                cmbDocter.setValue(null);
                treatmenttypecmb.setValue(null);
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail Register").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
