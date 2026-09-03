package UI.Pages.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.MainDashBoard;
import Dental.ICBT.Application.Service.Custom.impl.MainDashBoard_impl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainDash implements Initializable {
    public TableColumn coll_id;
    public TableColumn A_Num;
    public TableColumn P_Num;
    public TableColumn address;
    public TableColumn contactNumber;
    public TableColumn date;
    public TableView tbl_data;
    public Text appoitmentCount;
    public Text totalPation;
    public Text totalDentis;
    public Text pendingPayment;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coll_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        A_Num.setCellValueFactory(new PropertyValueFactory<>("Appoitment"));
        P_Num.setCellValueFactory(new PropertyValueFactory<>("pation"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        MainDashBoard_impl mainDashBoardImpl = new MainDashBoard_impl();
        List<ViewData> all = mainDashBoardImpl.getAll();
        tbl_data.setItems(FXCollections.observableArrayList(all));

        int PendingPayment = mainDashBoardImpl.PendingPayment();
        int TodayAppoitment = mainDashBoardImpl.TodayAppoitmentCount();
        int TotalAppoitment = mainDashBoardImpl.TotalAppoitment();

        pendingPayment.setText(String.valueOf(PendingPayment));
        totalPation.setText(String.valueOf(TotalAppoitment));
        appoitmentCount.setText(String.valueOf(TodayAppoitment));


        totalDentis.setText("09");


    }
}
