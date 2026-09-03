package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.Billing.billing;
import Dental.ICBT.Application.Service.Custom.Billing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Billing_impl implements Billing {


    @Override
    public List<billing> searchBill(String appointment) {

        List<billing> list = new ArrayList<>();
        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT service,treatmenttype,payment " + "FROM appoitment WHERE appoitmentnumber=?");
            ps.setString(1, appointment);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String service = rs.getString("service");
                if(service == null || service.trim().isEmpty()) {
                    service = rs.getString("treatmenttype");
                }

                list.add(new billing(
                                1,
                                service,
                                rs.getDouble("payment")
                        )
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    @Override
    public boolean completePayment(String appointment, String method, String status) {

        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE appoitment SET paymethod=?, paystatus=? WHERE appoitmentnumber=?");

            ps.setString(1, method);
            ps.setString(2, status);
            ps.setString(3, appointment);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String,String> getBillDetails(String appointment) {

        Map<String,String> data = new HashMap<>();
        try {
            Connection con = dbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM appoitment " + "WHERE appoitmentnumber=?");
            ps.setString(1, appointment);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                String service = rs.getString("service");

                if(service == null || service.trim().isEmpty()) {
                    service = rs.getString("treatmenttype");
                }
                data.put("appointment", rs.getString("appoitmentnumber"));
                data.put("patient", rs.getString("patientname"));
                data.put("service", service);

                data.put("amount", String.valueOf(rs.getDouble("payment")));

                data.put("method", rs.getString("paymethod"));
                data.put("status", rs.getString("paystatus"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}