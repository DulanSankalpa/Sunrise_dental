package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.SearchAppoitment;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchAppoitment_impl implements SearchAppoitment {
    @Override
    public List<ViewData> SearchAppoitmentNumber(String data) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement PStm = connection.prepareStatement("SELECT * FROM appoitment WHERE appoitmentnumber = ? ");
            PStm.setString(1,data);
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

                return Collections.singletonList(viewData);

            }else{
                new Alert(Alert.AlertType.ERROR,"Not Found Appoitment ID").show();
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean delete(String id) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from appoitment where appoitmentnumber = ? ");
            preparedStatement.setString(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean Update(String appoitment_id,String Pationt,String address,String number,String docter, String tread , String payment) {
        List<ViewData> list = new ArrayList<>();
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


            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
