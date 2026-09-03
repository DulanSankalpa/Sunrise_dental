package UI.Pages.impl;


import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class exit  {
    public Text alert;

    public void yesExit(ActionEvent actionEvent) {

        alert.setText("Good Bye ! Have a Nice Day");
        PauseTransition time = new PauseTransition(Duration.seconds(1));

        time.setOnFinished(e ->{
            Platform.exit();
        });
        time.play();
    }

    public void backtoPage(ActionEvent actionEvent) {

    }
}
