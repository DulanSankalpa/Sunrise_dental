package UI.Pages.impl;

import Dental.ICBT.Application.DB.dbConnection;
import Dental.ICBT.Application.Service.Custom.ResetPassword;
import Dental.ICBT.Application.Service.Custom.impl.ResetPassword_impl;
import Dental.ICBT.Application.Service.ServiceFactory;
import Dental.ICBT.Application.Util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPW {
    public AnchorPane pane02;
    public TextField uid;
    public TextField phonenumber;
    public TextField new_pw;

    ResetPassword reset = ServiceFactory.getInstance().getServiceType(ServiceType.RESETPW);

    public void Createbtn(ActionEvent actionEvent) {
        String user_name = uid.getText();
        String phone_number = phonenumber.getText();
        String newpw = new_pw.getText();

       if(user_name.length() >= 6 && phone_number.length() == 10 && newpw.length() >= 4){
           ResetPassword_impl resetPasswordImpl = new ResetPassword_impl();
           if(reset.ChangePassword(user_name,phone_number,newpw)){
               new Alert(Alert.AlertType.INFORMATION,"Update Successfully...").show();
               CD();
           }else{
               new Alert(Alert.AlertType.ERROR,"Fail Update Please Try Again....").show();
           }
       }

    }


    private void CD(){
        URL resource = getClass().getResource("/View/Main_Page.fxml");
        assert resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            pane02.  getChildren().clear();
            pane02.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
