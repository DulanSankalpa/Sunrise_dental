package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.MainDashBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainDashBoard_impl implements MainDashBoard {
    @Override
    public List<ViewData> getAll() {

        try {
            Connection connection1 = dbConnection.getInstance().getConnection();
            Statement statement = connection1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM appoitment ORDER BY id DESC");

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

            return dataList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int TotalAppoitment() {
        try {
            Connection connection1 = dbConnection.getInstance().getConnection();
            Statement statement = connection1.createStatement();
            ResultSet resultSet1 = statement.executeQuery("select COUNT(*) as total from appoitment");
            if(resultSet1.next()){
                int count = resultSet1.getInt("total");
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    @Override
    public int PendingPayment() {

        try {
            Connection connection1 = dbConnection.getInstance().getConnection();
            Statement statement = connection1.createStatement();
            ResultSet resultSet3 = statement.executeQuery("SELECT COUNT(*) * 1000 AS Pending_total FROM appoitment WHERE states = 'pending'");

            if (resultSet3.next()){
                int pending = resultSet3.getInt("Pending_total");
                return pending;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int TodayAppoitmentCount() {
        try {
            Connection connection1 = dbConnection.getInstance().getConnection();
            Statement statement = connection1.createStatement();
            ResultSet resultSet2 = statement.executeQuery("select COUNT(*) as total from appoitment WHERE createdata = CURRENT_DATE");
            if(resultSet2.next()){
                int count = resultSet2.getInt("total");
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

}
