package UI.ForgetPw;

import Dental.ICBT.Application.DB.dbConnection;
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

    public void Createbtn(ActionEvent actionEvent) {
        String user_name = uid.getText();
        String phone_number = phonenumber.getText();
        String newpw = new_pw.getText();

        try {

            Connection connection = dbConnection.getInstance().getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM login WHERE username = ? AND Phone = ?"
            );


            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, phone_number);


            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){


                PreparedStatement preparedStatement1 = connection.prepareStatement(
                        "UPDATE login SET pw = ? WHERE username = ? AND Phone = ?"
                );


                preparedStatement1.setString(1, newpw);
                preparedStatement1.setString(2, user_name);
                preparedStatement1.setString(3, phone_number);


                if(preparedStatement1.executeUpdate() > 0){

                    System.out.println("Password Reset Successfully");

                    CD();

                }


            }else{

                new Alert(Alert.AlertType.ERROR,"Username or Phone Number Incorrect");

            }


        } catch (SQLException e) {
            e.printStackTrace();
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
