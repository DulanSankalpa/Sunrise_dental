package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Report_impl implements Report {
    @Override
    public List<ViewData> getAllData(LocalDate from, LocalDate to) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM appoitment WHERE createdata BETWEEN ? AND ?"
            );

            preparedStatement.setDate(1, Date.valueOf(from));
            preparedStatement.setDate(2, Date.valueOf(to));

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

                dataList.add(viewData);

            }
            return dataList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
