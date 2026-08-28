package UI.Pages;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
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
import java.util.ArrayList;
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
        cal_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cal_a_id.setCellValueFactory(new PropertyValueFactory<>("Appoitment"));
        cal_p_name.setCellValueFactory(new PropertyValueFactory<>("pation"));
        cal_dental.setCellValueFactory(new PropertyValueFactory<>("docter"));
        cal_treat.setCellValueFactory(new PropertyValueFactory<>("treet"));
        cal_time.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM appoitment WHERE createdata BETWEEN ? AND ?"
            );

            preparedStatement.setDate(1, Date.valueOf(from_date.getValue()));
            preparedStatement.setDate(2, Date.valueOf(to_date.getValue()));

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<ViewData> dataList = new ArrayList<>();

            while(resultSet.next()){

                ViewData viewData = new ViewData(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDate(8)
                );

                dataList.add(viewData);   // මේ line එක අමතක වෙලා

                System.out.println(viewData);
            }


            ObservableList<ViewData> viewData1 =
                    FXCollections.observableArrayList(dataList);

            tbl_Table.setItems(viewData1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
