package OrderSomethingNow;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
        primaryStage.setTitle("OrderSomethingNow");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit(); //stop other running threads uppon exit
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
