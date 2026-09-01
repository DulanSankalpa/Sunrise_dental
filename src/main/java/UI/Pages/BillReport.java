package UI.Pages;


import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.Billing.billing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.layout.VBox;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;


public class BillReport implements Initializable {


    public ComboBox payMethod;
    public ComboBox payStates;
    public TextField FindAppoitment;
    public TableView<billing> billingTable;
    public TableColumn<billing,Integer> item;
    public TableColumn<billing,String> description;
    public TableColumn<billing,Double> amount;
    public Text ShowTotal;
    private String currentAppointment = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        payMethod.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList(
                                "Cash",
                                "Card",
                                "Bank transfer"
                        )
                )
        );

        payMethod.getSelectionModel().select(0);

        payStates.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList(
                                "Pending",
                                "Complete"
                        )
                )
        );

        payStates.getSelectionModel().select(0);

        item.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("service"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    public void searchBTN(ActionEvent actionEvent) {

        String appNo = FindAppoitment.getText().trim();

        if (appNo.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter Appointment Number").show();
            return;
        }
        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT appoitmentnumber,service,treatmenttype,payment " + "FROM appoitment WHERE appoitmentnumber=?");

            ps.setString(1, appNo);
            ResultSet rs = ps.executeQuery();

            ObservableList<billing> list = FXCollections.observableArrayList();
            if (rs.next()) {
                currentAppointment = rs.getString("appoitmentnumber");
                String serviceName = rs.getString("service");
                if (serviceName == null || serviceName.isEmpty()) {
                    serviceName = rs.getString("treatmenttype");
                }

                list.add(new billing(1, serviceName, rs.getDouble("payment")));
                billingTable.setItems(list);
                ShowTotal.setText("Rs. " + rs.getDouble("payment"));
            } else {
                new Alert(Alert.AlertType.ERROR, "Appointment Not Found").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Btn_Complete_Pay(ActionEvent actionEvent){
        if(currentAppointment == null){
            new Alert(Alert.AlertType.ERROR, "Search Appointment First").show();
            return;
        }
        try{
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE appoitment SET paymethod=?,paystatus=? " + "WHERE appoitmentnumber=?");

            ps.setString(1, payMethod.getValue().toString());
            ps.setString(2, payStates.getValue().toString());

            ps.setString(3, currentAppointment);
            if(ps.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION, "Payment Complete").show();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Bill_Print(ActionEvent actionEvent){
        if(currentAppointment == null){
            new Alert(Alert.AlertType.ERROR, "Search Appointment First").show();
            return;
        }
        try{
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM appoitment WHERE appoitmentnumber=?");
            ps.setString(1,currentAppointment);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                VBox bill = new VBox(15);
                Text title = new Text("SUNRISE DENTAL CLINIC");
                Text line1 = new Text("Appointment No : " + rs.getString("appoitmentnumber"));
                Text line2 = new Text("Patient Name : " + rs.getString("patientname"));

                String service = rs.getString("service");
                if(service == null || service.trim().isEmpty()){
                    service = rs.getString("treatmenttype");
                }

                Text line3 = new Text("Service : " + service);

                Text line4 =
                        new Text("Amount : Rs. " + rs.getDouble("payment"));

                Text line5 =
                        new Text("Payment Method : " + rs.getString("paymethod"));

                Text line6 =
                        new Text("Payment Status : " + rs.getString("paystatus"));

                Text line7 =
                        new Text("Thank You!");


                bill.getChildren().addAll(
                        title,
                        line1,
                        line2,
                        line3,
                        line4,
                        line5,
                        line6,
                        line7
                );




                PrinterJob job = PrinterJob.createPrinterJob();

                if(job != null){
                    boolean showDialog = job.showPrintDialog(null);
                    if(showDialog){
                        boolean success = job.printPage(bill);
                        if(success){
                            job.endJob();

                            new Alert(Alert.AlertType.INFORMATION, "Bill Printed Successfully").show();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}