package UI.Pages.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.impl.Report_impl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Report implements Initializable {
    public DatePicker from_date;
    public DatePicker to_date;
    public TableView tbl_Table;
    public TableColumn cal_id;
    public TableColumn cal_a_id;
    public TableColumn cal_p_name;
    public TableColumn cal_dental;
    public TableColumn cal_treat;
    public TableColumn cal_time;

    public void Genarate_Report(ActionEvent actionEvent)  {
       table_Load();

    }

    private void table_Load(){
        if(from_date.getValue() == null || to_date.getValue() == null){
            return;
        }
        LocalDate from = from_date.getValue();
        LocalDate to = to_date.getValue();
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_a_id.setCellValueFactory(new PropertyValueFactory<>("Appoitment"));
        cal_p_name.setCellValueFactory(new PropertyValueFactory<>("pation"));
        cal_dental.setCellValueFactory(new PropertyValueFactory<>("docter"));
        cal_treat.setCellValueFactory(new PropertyValueFactory<>("treet"));
        cal_time.setCellValueFactory(new PropertyValueFactory<>("date"));
        Report_impl reportImpl = new Report_impl();
        List<ViewData> allData = reportImpl.getAllData(from, to);
        tbl_Table.setItems(FXCollections.observableArrayList(allData));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table_Load();
    }

    private void lead(ViewData viewData){
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_a_id.setCellValueFactory(new PropertyValueFactory<>("Appoitment"));
        cal_p_name.setCellValueFactory(new PropertyValueFactory<>("pation"));
        cal_dental.setCellValueFactory(new PropertyValueFactory<>("docter"));
        cal_treat.setCellValueFactory(new PropertyValueFactory<>("treet"));
        cal_time.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
