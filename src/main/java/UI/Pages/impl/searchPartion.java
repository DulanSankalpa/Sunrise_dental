package UI.Pages.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.SearchAppoitment;
import Dental.ICBT.Application.Service.Custom.impl.SearchAppoitment_impl;
import Dental.ICBT.Application.Service.ServiceFactory;
import Dental.ICBT.Application.Util.ServiceType;
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
import java.util.List;
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

    SearchAppoitment search = ServiceFactory.getInstance().getServiceType(ServiceType.SEARCH);

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

        txtA_Num.setEditable(false);
    }

    public void btnEdit(ActionEvent actionEvent) {

        String appoitment_id = txtA_Num.getText();
        String Pationt = txtP_Name.getText();
        String address = txtAddress.getText();
        String number = txtContact_Num.getText();
        String docter = String.valueOf(cmbDocter.getValue());
        String tread = String.valueOf(treatmenttypecmb.getValue());
        String payment = String.valueOf(cmbPayment.getValue());



        SearchAppoitment_impl searchAppoitmentImpl = new SearchAppoitment_impl();
        if (search.Update(appoitment_id,Pationt,address,number,docter,tread,payment)){
            new Alert(Alert.AlertType.INFORMATION,"Update Successfully.. Thanks for You").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail Update Please Try Again..").show();
        }


    }

    public void btnSearch(ActionEvent actionEvent) {

        String findID = Find_ID.getText();
        SearchAppoitment_impl searchAppoitmentImpl = new SearchAppoitment_impl();
        List<ViewData> viewData = searchAppoitmentImpl.SearchAppoitmentNumber(findID);
        if(!viewData.isEmpty()){
            dataFill(viewData.get(0));
        }else{
            new Alert(Alert.AlertType.ERROR, "Appointment Not Found").show();
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
        String id = Find_ID.getText();
        SearchAppoitment_impl searchAppoitmentImpl = new SearchAppoitment_impl();
        if (search.delete(id)){
            new Alert(Alert.AlertType.INFORMATION,"Delete Success....").show();
            clear();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail Delete Please Tru Again.....").show();
            clear();
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
