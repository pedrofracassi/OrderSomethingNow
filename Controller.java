package OrderSomethingNow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {

    @FXML
    private Label liveClock;

    @FXML
    private Label currentLoggedInUser;

    @FXML
    public void initialize() {
        updateCurrentLoggedInUser();
        initializeClock();
    }

    private void initializeClock() {
        Thread clock = new Thread(() -> {
            while (true) {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Calendar cal = Calendar.getInstance();

                //clock on new thread
                Platform.runLater(
                        () -> liveClock.setText(dateFormat.format(cal.getTime()))
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException occurred");
                }
            }
        });
        clock.start();
    }

    private void updateCurrentLoggedInUser() {
        currentLoggedInUser.setText(System.getProperty("user.name"));
    }
}