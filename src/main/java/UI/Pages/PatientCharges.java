package UI.Pages;


import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class PatientCharges implements Initializable {


    public ComboBox AppoitmentNoCMB;
    public TableView tblView;
    public TableColumn cal_ID;
    public TableColumn call_AppoitmentNu;
    public TableColumn cal_Pation;
    public TableColumn Cal_Service;
    public TableColumn cal_Charges;
    public TextField dental_Service;
    public TextField Dental_Charges;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAppointmentNo();
        LodatTable();

        cal_ID.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        call_AppoitmentNu.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("Appoitment"));
        cal_Pation.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("pation"));
        Cal_Service.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("service"));
        cal_Charges.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("charges"));


    }

    private void loadAppointmentNo(){
        try {
            Connection con = dbConnection.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT appoitmentnumber FROM appoitment");
            while(rs.next()){
                AppoitmentNoCMB.getItems().add(rs.getString("appoitmentnumber"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Add(ActionEvent actionEvent) {

        String appointment = AppoitmentNoCMB.getValue().toString();
        String service = dental_Service.getText();
        double charge = Double.parseDouble(Dental_Charges.getText());

        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE appoitment SET service=?, payment=? WHERE appoitmentnumber=?");

            ps.setString(1,service);
            ps.setDouble(2,charge);
            ps.setString(3,appointment);

            if(ps.executeUpdate()>0){

                new Alert(Alert.AlertType.INFORMATION, "Charge Added").show();
                LodatTable();
                dental_Service.clear();
                Dental_Charges.clear();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void LodatTable(){

        ObservableList<ViewData> list = FXCollections.observableArrayList();
        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id,appoitmentnumber,patientname,service,payment " + "FROM appoitment");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new ViewData(
                                rs.getInt("id"),
                                rs.getString("appoitmentnumber"),
                                rs.getString("patientname"),
                                rs.getString("service"),
                                rs.getDouble("payment")
                        )
                );
            }
            tblView.setItems(list);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}