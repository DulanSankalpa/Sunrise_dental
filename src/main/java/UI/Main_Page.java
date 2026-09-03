package UI;

import Dental.ICBT.Application.DB.dbConnection;
import UI.Pages.impl.MainDash;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main_Page {
    public TextField uid;
    public TextField pw;
    public AnchorPane pane;
    public Text altermassage;
    public Text lbllockMassage;

    private int loginAttempts = 0;
    private boolean accountLocked = false;


    public void ForgetPassword(ActionEvent actionEvent) {
        URL resource = getClass().getResource("/View/ForgetPW/ResetPW.fxml");
        assert resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            pane.  getChildren().clear();
            pane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void Login(ActionEvent actionEvent) {

        if(uid.getText().isEmpty() || pw.getText().isEmpty()){
            altermassage.setText("Please Enter Username And Password");
            return;
        }
        loginAttempts++;
       if(uid.getText().length() >=6 || pw.getText().length() >=4 ) {
            if(loginAttempts >= 3 ){
                lbllockMassage.setText("Tempery suspension Acoount");
                uid.setEditable(false);
                pw.setEditable(false);
                new Alert(Alert.AlertType.ERROR,"Account Locked Temprty").show();
            }


               String user = uid.getText();
               String password = pw.getText();
               try {
                   String username = user;
                   String passcode = password;
                   Connection connection = dbConnection.getInstance().getConnection();
                   PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM login where username  = ? AND pw = ?");

                   preparedStatement.setString(1,user);
                   preparedStatement.setString(2,passcode);

                   ResultSet resultSet = preparedStatement.executeQuery();

                   if (resultSet.next()){
                       URL resource = getClass().getResource("/View/DashBoard.fxml");
                       assert resource != null;
                       try {
                           Parent load = FXMLLoader.load(resource);
                           pane.  getChildren().clear();
                           pane.getChildren().add(load);
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }
                   }else{

                       altermassage.setText("Wrong UserName And Password");
                   }


               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }else{
           new Alert(Alert.AlertType.ERROR,"Account tempory lock").show();
       }
    }
}
