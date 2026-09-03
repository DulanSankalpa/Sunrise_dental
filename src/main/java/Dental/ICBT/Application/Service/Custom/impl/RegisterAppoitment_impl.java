package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Model.register.Register;
import Dental.ICBT.Application.Service.Custom.RegisterAppoitment;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterAppoitment_impl implements RegisterAppoitment {
    @Override
    public boolean saveAppoitment(Register register) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appoitment (appoitmentnumber,patientname,address,NUMBER,dentistname,treatmenttype) values (?,?,?,?,?,?)");

            preparedStatement.setString(1,register.getAppoitmenrt());
            preparedStatement.setString(2,register.getPation());
            preparedStatement.setString(3,register.getAddress());
            preparedStatement.setString(4,register.getNumber());
            preparedStatement.setString(5,register.getDentis());
            preparedStatement.setString(6,register.getType());



            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
