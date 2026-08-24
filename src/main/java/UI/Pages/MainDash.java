package UI.Pages;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
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

        try {
            Connection connection1 = dbConnection.getInstance().getConnection();
            Statement statement = connection1.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from appoitment");

            ArrayList<ViewData> dataList = new ArrayList<>();

            while (resultSet.next()){
                dataList.add(
                        new ViewData(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getDate(8)
                        )
                );
            }

            ObservableList<ViewData> viewData = FXCollections.observableArrayList(dataList);
            tbl_data.setItems(viewData);

            ResultSet resultSet1 = statement.executeQuery("select COUNT(*) as total from appoitment");
            if(resultSet1.next()){
                int count = resultSet1.getInt("total");
                totalPation.setText(String.valueOf(count));
            }

            ResultSet resultSet2 = statement.executeQuery("select COUNT(*) as total from appoitment WHERE createdata = CURRENT_DATE");
            if(resultSet2.next()){
                int count = resultSet2.getInt("total");
                appoitmentCount.setText(String.valueOf(count));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
