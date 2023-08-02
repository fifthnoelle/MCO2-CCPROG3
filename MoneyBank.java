/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mco_gui;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MoneyBank {
    Money money;
    public ArrayList<Money> changeMoney = new ArrayList<>();
    private ArrayList<Money> paymentMoney = new ArrayList<>();
    private ArrayList<Money> partialPayment = new ArrayList<>();

    MoneyBank() {
       
    }

    /**
     * Makes Money objects and assigns them on a specific denomination value for each money array
     */
    public void setDenomination() {
    int i;
    float[] n = { 1, 5, 10, 20, 50, 100, 200, 500 };
    for (i = 0; i < 8; i++) {
        Money denomination = new Money(n[i]);
        changeMoney.add(new Money(denomination.getValue()));
        paymentMoney.add(new Money(denomination.getValue()));
        partialPayment.add(new Money(denomination.getValue()));
    }
}

    
    /** 
     * Prompts the user to choose denominations they will use for purchasing in the vending machine. It chooses
     * the chosen value from all denominations and increments their quantities respectively in partialPayment array
     * @return float
     */



public float partialPaymentMenu() {
    float totalCash = 0;
    int nChoice;

    String[] denominationNames = new String[partialPayment.size() + 1];
    for (int i = 0; i < partialPayment.size(); i++) {
        denominationNames[i] = "P" + partialPayment.get(i).getValue();
    }
    denominationNames[partialPayment.size()] = "Buy";

    do {
        String choicesMessage = "\nChoose Denominations:\n";
        /*for (int i = 0; i < partialPayment.size(); i++) {
            Money currMoney = partialPayment.get(i);
            choicesMessage += "[" + (i + 1) + "] P" + currMoney.getValue() + "\n";
        }
        choicesMessage += "[0] Done";*/

        // Create a pop-up dialog for selecting the denomination
        nChoice = JOptionPane.showOptionDialog(
                null,
                choicesMessage,
                "Partial Payment",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                denominationNames,
                denominationNames[0]);

        if (nChoice >= 0 && nChoice < partialPayment.size()) {
            int index = nChoice;
            partialPayment.get(index).setQuantity(partialPayment.get(index).getQuantity() + 1);
            totalCash += partialPayment.get(index).getValue();
            JOptionPane.showMessageDialog(null, "\nAdded P" + partialPayment.get(index).getValue(), "Added Payment", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Subtotal: " + totalCash + "\n");
        } else if (nChoice == partialPayment.size()) {
            // User clicked "Back" button, break the loop
            break;
        }
    } while (nChoice != JOptionPane.CLOSED_OPTION && nChoice != 0);

    if (nChoice != partialPayment.size()) {
        JOptionPane.showMessageDialog(null, "Subtotal: " + totalCash + "\n", "Subtotal", JOptionPane.INFORMATION_MESSAGE);
    }

    return totalCash;
}

    /**
     * Migrates quantities per denomination from partialPayment to paymentMoney array. It also empties partialPayment
     * in preparation for the next transaction
     */
    public void addPayment(){
        int i;
        for (i = 0; i < partialPayment.size(); i++) {
            Money currMoney = partialPayment.get(i);
            Money currPayMoney = paymentMoney.get(i);
            if(currMoney.getValue() == currPayMoney.getValue())
            {
                currPayMoney.setQuantity(currMoney.getQuantity() + currPayMoney.getQuantity());
                currMoney.setQuantity(0);
            }
        }
    }

    
    /** 
     * Looks for passed amount from the changeMoney array to look for a Money object that matches the 
     * value. Then it adds the quantity desired to add for that specific denomination. Then it prints
     * the process out so the user is updated.
     * @param amount the amount of the denomination
     * @param quantity the quantity of denomination to refill
     */
    public void refillChange(float amount, int quantity) {
        float prod;
        float total;
        int i;

        for (i = 0; i < changeMoney.size(); i++) {
            Money currMoney = changeMoney.get(i);
            if (currMoney.getValue() == amount) {
                currMoney.setQuantity(currMoney.getQuantity() + quantity);
                System.out.println("Added " + quantity + " * P" + amount);
            }
        }


        prod = mulDenominations(amount, quantity);
        total = getTotalChange();
        
        System.out.println("Subtotal: P" + prod);
        System.out.println("Total: P" + total);
    }

    /**
     * Gets amount and looks for the Money object with its matching value in changeMoney array. When found, 
     * quantity is subtracted from the initial quantity
     * @param amount the amount of the denomination
     * @param quantity the quantity of denomination to refill
     */
    public void subtractMoney(float amount, int quantity) {
        int i;
        for (i = 0; i < changeMoney.size(); i++) {
            Money currMoney = changeMoney.get(i);
            if (currMoney.getValue() == amount) {
                currMoney.setQuantity(currMoney.getQuantity() - quantity);
                changeMoney.set(i, currMoney);
            }

        }
    }

    /**
     * From the inputted totalChange(amt), it looks for a denomination that is lower than
     * amt from highest to lowest. The highest amount that can be divided from the amt
     * and has a quantity over 0 will be divided from the amt. Its quantity will be tracked. 
     * If there are remainders, it will be looped to look for the next highest denomination
     * to be divided from the remaining amt until amt reaches zero. They will be printed for
     * the user to be updated of the change given to them. If there is not enough change, a
     * warning will be printed urging for maintenance and addPayment will not execute.
     * @param totalChange the total change from the transaction
     */
public boolean makeChange(float totalChange) {
        int i;
        float amt = totalChange;
        StringBuilder message = new StringBuilder();
        boolean changeSuccessful = true;

        if (amt > 0) {
            message.append("\nChange:\n");
        }
        
        if(amt == 0){
                message.append("Exact amount paid! \n\nP0");
            }

        for (i = changeMoney.size() - 1; i >= 0; i--) {
            Money currMoney = changeMoney.get(i);
            float currVal = currMoney.getValue();
            int currQuantity = currMoney.getQuantity();

            while (amt >= currVal && currMoney.getQuantity() > 0) {
                float fQuantity;
                int quantity;

                fQuantity = amt / currVal;
                quantity = (int) fQuantity;

                if (quantity <= currMoney.getQuantity() && quantity != 0) {
                    amt = amt - (currVal * quantity);

                    if (currMoney.getQuantity() != 0) {
                        message.append((int) quantity).append(" * P").append(currVal).append("\n");
                    }
                    subtractMoney(currVal, (int) quantity);
                } else if (quantity > currMoney.getQuantity() && quantity != 0) {
                    if (currMoney.getQuantity() != 0) {
                        message.append(currQuantity).append(" * P").append(currVal).append("\n");
                    }
                    subtractMoney(currVal, currQuantity);
                }
            }
        }

        if (amt > 0) {
            message.append("Not enough change! Maintenance Required. (Change Error)\n");
            resetPartialPayment();
            changeSuccessful = false;
        } else {
            addPayment();
        }

        // Show the change result in a pop-up dialog
        JOptionPane.showMessageDialog(
                null, message.toString(),
                "Change Result", JOptionPane.PLAIN_MESSAGE
        );

        return changeSuccessful;
    }

    /**
     * Shows the quantity of denominations to be used for giving out change to the user.
     */
    public void showChangeStock() {
    int i;
    String stockMessage = "Current Change Stock\n";

    for (i = 0; i < changeMoney.size(); i++) {
        Money currMoney = changeMoney.get(i);

        if (currMoney.getQuantity() != 0) {
            stockMessage += currMoney.getQuantity() + "\tP" + currMoney.getValue() + "\n";
        }
    }

    // Show the change stock information in a popup dialog
    JOptionPane.showMessageDialog(null, stockMessage, "Change Stock", JOptionPane.INFORMATION_MESSAGE);
}
    public void resetPartialPayment(){
        for (int i = 0; i < partialPayment.size(); i++) {
            partialPayment.get(i).setQuantity(0);
            //System.out.println("Quantity of" + partialPayment.get(i).getValue() + " is " + partialPayment.get(i).getQuantity());
            }
    }

    /**
     * Looks through all Money objects in paymentMoney array and each denomination that 
     * isn't empty, will be printed for the user to collect. With every object, it is
     * emptied by setting the quantity back to zero. It them prints "Collected!" for the
     * user.
     */
    public void collectPayment() {
        int i;
        for (i = 0; i < paymentMoney.size(); i++) {
            Money currMoney = paymentMoney.get(i);
            if(currMoney.getQuantity() != 0){
            System.out.println(currMoney.getQuantity() + " * P" + currMoney.getValue());
            }
            currMoney.setQuantity(0);
            }
        System.out.println("Collected!");
    }

    /**
     * Multiplies inputted amount to the quantity
     * @param amount the amount of the denomination
     * @param quantity the quantity of the denomination
     * @return float of the product
     */
    public float mulDenominations(float amount, int quantity) {
        return amount * quantity;
    }

    /**
     * Scans through all Money objects in paymentMoney and multiplies all quantities with their 
     * respective values and adds them all to get total payment.
     * @return float of total payment
     */
    public float getTotalPayment() {
        int i;
        float totalPayment = 0;
        for (i = 0; i < paymentMoney.size(); i++) {
            Money currMoney = paymentMoney.get(i);
            totalPayment += currMoney.getValue() * currMoney.getQuantity();
            }
        return totalPayment;
    }

    /**
     * Scans through all Money objects in changeMoney and multiplies all quantities with their 
     * respective values and adds them all to get total change.
     * @return float of total change
     */
    public float getTotalChange() {
        int i;
        float totalChange = 0;
        for (i = 0; i < changeMoney.size(); i++) {
            Money currMoney = changeMoney.get(i);
            totalChange += currMoney.getValue() * currMoney.getQuantity();
            }
        return totalChange;
    }
    
    
}
