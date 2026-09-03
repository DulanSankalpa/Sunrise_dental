package UI.Pages.impl;


import Dental.ICBT.Application.Model.Billing.billing;
import Dental.ICBT.Application.Service.Custom.Billing;
import Dental.ICBT.Application.Service.Custom.impl.Billing_impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    Billing billingService = new Billing_impl();
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
        if(appNo.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Enter Appointment Number").show();
            return;
        }
        List<billing> result = billingService.searchBill(appNo);
        if(!result.isEmpty()){
            currentAppointment = appNo;
            ObservableList<billing> list = FXCollections.observableArrayList(result);
            billingTable.setItems(list);
            ShowTotal.setText("Rs. " + result.get(0).getAmount());
        }else{
            new Alert(Alert.AlertType.ERROR, "Appointment Not Found").show();
        }
    }
    public void Btn_Complete_Pay(ActionEvent actionEvent) {
        if(currentAppointment == null){
            new Alert(Alert.AlertType.ERROR, "Search Appointment First").show();
            return;
        }
        boolean result = billingService.completePayment(
                        currentAppointment,
                        payMethod.getValue().toString(),
                        payStates.getValue().toString()
                );
        if(result){
            new Alert(Alert.AlertType.INFORMATION, "Payment Complete").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Payment Failed").show();
        }
    }
    public void Bill_Print(ActionEvent actionEvent) {
        if(currentAppointment == null){
            new Alert(Alert.AlertType.ERROR, "Search Appointment First").show();
            return;
        }
        Map<String,String> data = billingService.getBillDetails(currentAppointment);
        VBox billBox = new VBox(15);

        Text title = new Text("SUNRISE DENTAL CLINIC");
        Text line1 = new Text("Appointment No : " + data.get("appointment"));
        Text line2 = new Text("Patient Name : " + data.get("patient"));
        Text line3 = new Text("Service : " + data.get("service"));
        Text line4 = new Text("Amount : Rs. " + data.get("amount"));
        Text line5 = new Text("Payment Method : " + data.get("method"));
        Text line6 = new Text("Payment Status : " + data.get("status"));
        Text line7 = new Text("Thank You!");

        billBox.getChildren().addAll(
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
                boolean success = job.printPage(billBox);
                if(success){
                    job.endJob();
                    new Alert(Alert.AlertType.INFORMATION, "Bill Printed Successfully").show();
                }
            }
        }
    }
}