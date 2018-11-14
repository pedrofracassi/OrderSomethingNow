package OrderSomethingNow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CashRegister {

    private Map<Integer, Integer> itemMap;
    private int moneyInRegister;
    private int previousSessionMoney;
    private int income;
    private List<Integer> orders;
    private boolean isRegisterOpen;

    CashRegister() {
        this.itemMap = new HashMap<>();
        this.moneyInRegister = 0;
        this.income = 0;
        this.orders = new ArrayList<>();
        this.isRegisterOpen = false;
    }

    void addNewOrder(int itemNumber) {
        if (this.isRegisterOpen) {
            orders.add(itemMap.get(itemNumber));

            setMoneyInRegister(getMoneyInRegister() + itemMap.get(itemNumber));
            calculateIncome();
            System.out.println("Item " + itemNumber + ", with price " + itemMap.get(itemNumber) + " added!");
        } else {
            System.out.println("Register is not open!");
        }
    }

    void addItemToMap(int itemNumber, int itemPrice) {
        itemMap.put(itemNumber, itemPrice);
    }

    private void calculateIncome() {
        this.income = getMoneyInRegister() - previousSessionMoney;
    }

    private void calculateMoneyInRegister() {
        int sum = 0;
        for (Integer order : orders) {
            sum += order;
        }
        setMoneyInRegister(sum);
    }

    int getMoneyInRegister() {
        calculateMoneyInRegister();
        return moneyInRegister;
    }

    private void setMoneyInRegister(int moneyInRegister) {
        if (this.isRegisterOpen) {
            this.moneyInRegister = moneyInRegister;
        } else {
            System.out.println("Register is not open!");
        }
    }

    void setPreviousSessionMoney() {
        this.previousSessionMoney = moneyInRegister;
    }

    int getIncome() {
        return income;
    }

    int getNumberOfOrders() {
        return orders.size();
    }

    void openRegister() {
        this.isRegisterOpen = true;
    }

    void closeRegister() {
        this.isRegisterOpen = false;
    }

    boolean getRegisterState() {
        return this.isRegisterOpen;
    }
}
