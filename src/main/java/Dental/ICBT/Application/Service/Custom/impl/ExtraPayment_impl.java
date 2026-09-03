package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.MainTable.ViewData;
import Dental.ICBT.Application.Service.Custom.PaymentExtra;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExtraPayment_impl implements PaymentExtra {
    @Override
    public boolean ExtraPayment(String appointment, String service, double charge) {

        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE appoitment SET service=?, payment=? WHERE appoitmentnumber=?");

            ps.setString(1,service);
            ps.setDouble(2,charge);
            ps.setString(3,appointment);

            return ps.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ViewData> getAllList() {
        ArrayList<ViewData> list = new ArrayList<>();
        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id,appoitmentnumber,patientname,service,payment " + "FROM appoitment");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new ViewData(
                                rs.getInt("id"),
                                rs.getString("appoitmentnumber"),
                                rs.getString("patientname"),
                                rs.getString("service"),
                                rs.getDouble("payment")
                        )
                );
            }
            return list;

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
