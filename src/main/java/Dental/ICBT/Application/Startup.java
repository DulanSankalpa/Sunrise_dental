package Dental.ICBT.Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Startup extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Main_Page.fxml"))));
       stage.setTitle("Sunrise Dental Application");
        stage.getIcons().add(
                new Image("img/logo.png")
        );
        stage.setResizable(false);
       stage.show();
    }
}
