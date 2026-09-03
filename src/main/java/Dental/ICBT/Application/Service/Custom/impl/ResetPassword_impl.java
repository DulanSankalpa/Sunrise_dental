package Dental.ICBT.Application.Service.Custom.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Service.Custom.ResetPassword;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPassword_impl implements ResetPassword {
    @Override
    public boolean ChangePassword(String name, String number, String newPassword) {

        try {

            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement check = connection.prepareStatement("SELECT * FROM login WHERE username=? AND Phone=?");
            check.setString(1, name);
            check.setString(2, number);

            ResultSet resultSet = check.executeQuery();
            if(resultSet.next()) {
                PreparedStatement update = connection.prepareStatement("UPDATE login SET pw=? WHERE username=? AND Phone=?");
                update.setString(1, newPassword);
                update.setString(2, name);
                update.setString(3, number);

                return update.executeUpdate() > 0;

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
