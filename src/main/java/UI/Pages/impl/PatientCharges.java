package UI.Pages.impl;


import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.PaymentExtra;
import Dental.ICBT.Application.Service.Custom.impl.ExtraPayment_impl;
import Dental.ICBT.Application.Service.ServiceFactory;
import Dental.ICBT.Application.Util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.List;
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

    ExtraPayment_impl extraPaymentImpl = new ExtraPayment_impl();
    PaymentExtra extra = ServiceFactory.getInstance().getServiceType(ServiceType.EXTRAPAY);
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


        if (extra.ExtraPayment(appointment,service,charge)){
            new Alert(Alert.AlertType.INFORMATION,"Added Succesfully").show();
            AppoitmentNoCMB.setValue("");
            dental_Service.setText("");
            Dental_Charges.setText("");


        }else{
            new Alert(Alert.AlertType.ERROR,"Fail Added Try Again.....").show();
        }

    }

    private void LodatTable(){

        List<ViewData> allList = extraPaymentImpl.getAllList();
        tblView.setItems(FXCollections.observableArrayList(allList));

    }

}