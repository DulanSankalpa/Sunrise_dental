package UI.Pages;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        ));

        cmbDocter.setItems(FXCollections.observableArrayList(
                "Dr.Kasun",
                "Dr.Niroth",
                "Dr.Perera",
                "Dr.Sudhath",
                "Dr.Kalpani",
                "Dr.Dulan",
                "Dr.Hashara",
                "Dr.Tharuka",
                "Dr.Gamini"
        ));


        cmbPayment.setItems(FXCollections.observableArrayList(
                "Pending",
                "Complete"
        ));
    }

    public void btnEdit(ActionEvent actionEvent) {

        String appoitment_id = txtA_Num.getText();
        String Pationt = txtP_Name.getText();
        String address = txtAddress.getText();
        String number = txtContact_Num.getText();
        String docter = String.valueOf(cmbDocter.getValue());
        String tread = String.valueOf(treatmenttypecmb.getValue());
        String payment = String.valueOf(cmbPayment.getValue());

        try {

            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE appoitment SET patientname=?, address=?, number=?, dentistname=?, treatmenttype=?, states=? WHERE appoitmentnumber=?");


            preparedStatement.setString(1, Pationt);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, number);
            preparedStatement.setString(4, docter);
            preparedStatement.setString(5, tread);
            preparedStatement.setString(6, payment);
            preparedStatement.setString(7, appoitment_id);


            if(preparedStatement.executeUpdate() > 0){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update");
                alert.setContentText("Appointment Updated Successfully");
                alert.show();

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update");
                alert.setContentText("Update Failed");
                alert.show();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSearch(ActionEvent actionEvent) {

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement PStm = connection.prepareStatement("SELECT * FROM appoitment WHERE appoitmentnumber = ? ");
            PStm.setString(1,Find_ID.getText());
            ResultSet resultSet = PStm.executeQuery();

            if (resultSet.next()){
                ViewData viewData = new ViewData(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDate(8),
                        resultSet.getString(10)

                );
                System.out.println(viewData);
                dataFill(viewData);

            }else{
                new Alert(Alert.AlertType.ERROR,"Not Found Appoitment ID").show();
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void dataFill(ViewData data) {
        txtA_Num.setText(String.valueOf(data.getAppoitment()));
        txtP_Name.setText(data.getPation());
        txtAddress.setText(data.getAddress());
        txtContact_Num.setText(data.getNumber());

        cmbDocter.getSelectionModel().select(data.getDocter());
        treatmenttypecmb.getSelectionModel().select(data.getTreet());
        cmbPayment.getSelectionModel().select(data.getPay());
    }

    public void btndelete(ActionEvent actionEvent) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from appoitment where appoitmentnumber = ? ");
            preparedStatement.setString(1,Find_ID.getText());
            if(preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Delete Succesfull").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail Delete").show();
                clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear(){
        txtA_Num.setText("");
        txtP_Name.setText("");
        txtAddress.setText("");
        txtContact_Num.setText("");
        cmbDocter.setValue("");
        cmbPayment.setValue("");
        treatmenttypecmb.setValue("");
    }
}
