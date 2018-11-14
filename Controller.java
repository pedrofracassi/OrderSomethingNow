package OrderSomethingNow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {

    private CashRegister register = new CashRegister();

    @FXML
    private Button closeRegisterButton;

    @FXML Button openRegisterButton;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label finishedOrders;

    @FXML
    private Label incomeDisplay;

    @FXML
    private Label moneyInRegister;

    @FXML
    private Label liveClock;

    @FXML
    private Label currentLoggedInUser;


    @FXML
    public void initialize() {
        fillItemMap();
        updateCurrentLoggedInUser();
        initializeClock();
        closeRegisterButton.setDisable(true);
    }

    private void fillItemMap() {
        register.addItemToMap(0, 350);
        register.addItemToMap(1, 500);
        register.addItemToMap(2, 1390);
        register.addItemToMap(3, 800);
        register.addItemToMap(4, 500);
        register.addItemToMap(5, 700);
    }

    @FXML
    private void updateCurrentLoggedInUser() {
        currentLoggedInUser.setText(System.getProperty("user.name"));
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

    @FXML
    private void handleMenuExitClick() {
        Platform.exit(); //stop other running threads uppon exit
        System.exit(0);
    }

    @FXML
    public void showItemListDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Price list");
        dialog.setHeaderText("Available items");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ItemListDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    @FXML
    private void showWarningWindowDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Warning");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("WarningWindow.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    @FXML
    private void showRegisterIsNowClosedDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Action completed!");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RegisterIsNowClosed.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    @FXML
    private void handleOpenRegister() {
        register.openRegister();
        setPreviousSessionMoney();
        updateMoneyInRegister();
        updateFinishedOrders();
        updateIncomeDisplay();
        openRegisterButton.setDisable(true);
        closeRegisterButton.setDisable(false);
    }

    @FXML
    private void handleCloseRegister() {
        setPreviousSessionMoney();
        updateMoneyInRegister();
        updateFinishedOrders();
        updateIncomeDisplay();
        register.closeRegister();
        showRegisterIsNowClosedDialog();
        closeRegisterButton.setDisable(true);
        openRegisterButton.setDisable(false);
    }

    private void setPreviousSessionMoney() {
        register.setPreviousSessionMoney();
    }

    @FXML
    private void updateMoneyInRegister() {
        moneyInRegister.setText(String.valueOf(register.getMoneyInRegister()));
    }

    @FXML
    private void updateFinishedOrders() {
        finishedOrders.setText(String.valueOf(register.getNumberOfOrders()));
    }

    @FXML
    private void updateIncomeDisplay() {
        incomeDisplay.setText(String.valueOf(register.getIncome()));
    }

    @FXML
    private void handleButtonClickCheeseBurger() {
        if (register.getRegisterState()) {
            register.addNewOrder(0);
            updateDetails();
        } else {
            showWarningWindowDialog();
        }
    }

    @FXML
    private void handleButtonClickChickenBurger() {
        if (register.getRegisterState()) {
            register.addNewOrder(1);
            updateDetails();
        } else {
            showWarningWindowDialog();
        }
    }

    @FXML
    private void handleButtonClickWhopper() {
        if (register.getRegisterState()) {
            register.addNewOrder(2);
            updateDetails();
        } else {
            showWarningWindowDialog();
        }
    }

    @FXML
    private void handleButtonClickWhopperJunior() {
        if (register.getRegisterState()) {
            register.addNewOrder(3);
            updateDetails();
        } else {
            showWarningWindowDialog();
        }
    }

    @FXML
    private void handleButtonClickLonger() {
        if (register.getRegisterState()) {
            register.addNewOrder(4);
            updateDetails();
        } else {
            showWarningWindowDialog();
        }
    }

    @FXML
    private void handleButtonClickLongerExtreme() {
        if (register.getRegisterState()) {
            register.addNewOrder(5);
            updateDetails();
        } else {
            showWarningWindowDialog();
        }
    }



    private void updateDetails() {
        updateFinishedOrders();
        updateIncomeDisplay();
        updateMoneyInRegister();
    }
}