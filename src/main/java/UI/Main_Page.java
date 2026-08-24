package UI;

import Dental.ICBT.Application.DB.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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


    }
}
