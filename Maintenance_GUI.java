/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mco_gui;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author rdpun
 */
public class Maintenance_GUI extends javax.swing.JFrame {
    private ArrayList<items> itemSlots;
    private MoneyBank moneyBank = new MoneyBank();
    private boolean isSpecial = false;
    private ArrayList<SpecialItems> specialItemSlots;
    
    /**
     * Creates new form CreateNVend_GUI
     */
    public Maintenance_GUI(ArrayList<items> itemSlots, MoneyBank moneyBank) {
        this.itemSlots = itemSlots;
        this.moneyBank = moneyBank;
        this.isSpecial = false;
        initComponents();
        populateTableWithItems();
    }
    
    public Maintenance_GUI(ArrayList<items> itemSlots, MoneyBank moneyBank, ArrayList<SpecialItems> specialItemSlots) {
        this.itemSlots = itemSlots;
        this.moneyBank = moneyBank;
        this.specialItemSlots = specialItemSlots;
        this.isSpecial = true;
        initComponents();
        populateTableWithItems();
    }

public void maintenanceRestock() {
    int itemInd, restockQuantity, nChoice;

    if (!isSpecial) {
        //printItems(0);
        itemInd = Integer.parseInt(JOptionPane.showInputDialog("Which Item do you want to restock?\n(Enter Item no.)")) - 1;
        if (itemInd <= 7) {
            restockQuantity = Integer.parseInt(JOptionPane.showInputDialog("How many [" + itemSlots.get(itemInd).getItemName() + "] would you want to restock?"));
            if (checkQuantity(restockQuantity, itemSlots.get(itemInd).getItemQuantity())) {
                itemSlots.get(itemInd).setItemQuantity(restockQuantity + itemSlots.get(itemInd).getItemQuantity());
                JOptionPane.showMessageDialog(null, "Updated quantity for [" + itemSlots.get(itemInd).getItemName() + "] is " + itemSlots.get(itemInd).getItemQuantity());
            } else {
                JOptionPane.showMessageDialog(null, "Input is too large.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no such item.");
        }

    } else if (isSpecial) {

        nChoice = Integer.parseInt(JOptionPane.showInputDialog("Which inventory are you editing?\n[1] Regular Inventory\n[2] Halo-halo Inventory"));

        switch (nChoice) {
            case 1:
                //printItems(0);
                itemInd = Integer.parseInt(JOptionPane.showInputDialog("Which Item do you want to restock?\n(Enter Item no.)")) - 1;
                if (itemInd <= 7) {
                    restockQuantity = Integer.parseInt(JOptionPane.showInputDialog("How many [" + itemSlots.get(itemInd).getItemName() + "] would you want to restock?"));
                    if (checkQuantity(restockQuantity, itemSlots.get(itemInd).getItemQuantity())) {
                        itemSlots.get(itemInd).setItemQuantity(restockQuantity + itemSlots.get(itemInd).getItemQuantity());
                        JOptionPane.showMessageDialog(null, "Updated quantity for [" + itemSlots.get(itemInd).getItemName() + "] is " + itemSlots.get(itemInd).getItemQuantity());
                    } else {
                        JOptionPane.showMessageDialog(null, "Input is too large.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "There is no such item.");
                }
                break;

            case 2:
                //printItems(1);
                itemInd = Integer.parseInt(JOptionPane.showInputDialog("Which Item do you want to restock?\n(Enter Item no.)")) - 1;
                if (itemInd <= 7) {
                    restockQuantity = Integer.parseInt(JOptionPane.showInputDialog("How many [" + specialItemSlots.get(itemInd).getItemName() + "] would you want to restock?"));
                    if (checkQuantity(restockQuantity, specialItemSlots.get(itemInd).getItemQuantity())) {
                        specialItemSlots.get(itemInd).setItemQuantity(restockQuantity + specialItemSlots.get(itemInd).getItemQuantity());
                        JOptionPane.showMessageDialog(null, "Updated quantity for [" + specialItemSlots.get(itemInd).getItemName() + "] is " + specialItemSlots.get(itemInd).getItemQuantity());
                    } else {
                        JOptionPane.showMessageDialog(null, "Input is too large.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "There is no such item.");
                }
                break;
        }
    }
    populateTableWithItems();
}

public void setPrices() {
    int itemInd, newPrice, nChoice;

    if (!isSpecial) {
        String[] itemNames = new String[8];
        for (int i = 0; i < 8; i++) {
            itemNames[i] = itemSlots.get(i).getItemName();
        }

        nChoice = JOptionPane.showOptionDialog(
                null,
                "Which item do you want to modify the price?",
                "Set Prices",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                itemNames,
                itemNames[0]);

        if (nChoice != JOptionPane.CLOSED_OPTION) {
            itemInd = nChoice;

            String inputPrice = JOptionPane.showInputDialog(
                    null,
                    "Input new price for [" + itemSlots.get(itemInd).getItemName() + "]:",
                    "Set Prices",
                    JOptionPane.QUESTION_MESSAGE);

            try {
                newPrice = Integer.parseInt(inputPrice);
                if (newPrice >= 0) {
                    itemSlots.get(itemInd).setItemAmount(newPrice);
                    JOptionPane.showMessageDialog(
                            null,
                            "Updated Price for [" + itemSlots.get(itemInd).getItemName() + "] is: " + itemSlots.get(itemInd).getItemAmount(),
                            "Set Prices",
                            JOptionPane.INFORMATION_MESSAGE);
                    populateTableWithItems();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Price. Please enter a non-negative number.",
                            "Set Prices",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid price (a non-negative number).",
                        "Set Prices",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    } else if (isSpecial) {
        String[] inventoryOptions = {"Regular Inventory", "Halo-halo Inventory"};
        nChoice = JOptionPane.showOptionDialog(
                null,
                "Which inventory are you editing?",
                "Set Prices",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                inventoryOptions,
                inventoryOptions[0]);

        if (nChoice != JOptionPane.CLOSED_OPTION) {
            switch (nChoice) {
                case 0:
                    String[] regularItemNames = new String[8];
                    for (int i = 0; i < 8; i++) {
                        regularItemNames[i] = itemSlots.get(i).getItemName();
                    }

                    itemInd = JOptionPane.showOptionDialog(
                            null,
                            "Which item do you want to modify the price?",
                            "Set Prices",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            regularItemNames,
                            regularItemNames[0]);

                    if (itemInd != JOptionPane.CLOSED_OPTION) {
                        String inputPrice = JOptionPane.showInputDialog(
                                null,
                                "Input new price for [" + itemSlots.get(itemInd).getItemName() + "]:",
                                "Set Prices",
                                JOptionPane.QUESTION_MESSAGE);

                        try {
                            newPrice = Integer.parseInt(inputPrice);
                            if (newPrice >= 0) {
                                itemSlots.get(itemInd).setItemAmount(newPrice);
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Updated Price for [" + itemSlots.get(itemInd).getItemName() + "] is: " + itemSlots.get(itemInd).getItemAmount(),
                                        "Set Prices",
                                        JOptionPane.INFORMATION_MESSAGE);
                                populateTableWithItems();
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Invalid Price. Please enter a non-negative number.",
                                        "Set Prices",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Please enter a valid price (a non-negative number).",
                                    "Set Prices",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 1:
                    String[] haloHaloItemNames = new String[8];
                    for (int i = 0; i < 8; i++) {
                        haloHaloItemNames[i] = specialItemSlots.get(i).getItemName();
                    }

                    itemInd = JOptionPane.showOptionDialog(
                            null,
                            "Which item do you want to modify the price?",
                            "Set Prices",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            haloHaloItemNames,
                            haloHaloItemNames[0]);

                    if (itemInd != JOptionPane.CLOSED_OPTION) {
                        String inputPrice = JOptionPane.showInputDialog(
                                null,
                                "Input new price for [" + specialItemSlots.get(itemInd).getItemName() + "]:",
                                "Set Prices",
                                JOptionPane.QUESTION_MESSAGE);

                        try {
                            newPrice = Integer.parseInt(inputPrice);
                            if (newPrice >= 0) {
                                specialItemSlots.get(itemInd).setItemAmount(newPrice);
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Updated Price for [" + specialItemSlots.get(itemInd).getItemName() + "] is: " + specialItemSlots.get(itemInd).getItemAmount(),
                                        "Set Prices",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Invalid Price. Please enter a non-negative number.",
                                        "Set Prices",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Please enter a valid price (a non-negative number).",
                                    "Set Prices",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
            }
        }
    }
    populateTableWithItems();
}

public boolean checkQuantity(int quanQuery, int itemQuantity){
        if(quanQuery+itemQuantity<=15)
            return true;
            
        return false;
    }

public void refillChangeMenu() {
    String[] denominationOptions = {
            "P1", "P5", "P10", "P20", "P50", "P100", "P200", "P500", "Exit"
    };

    System.out.println("\nRefill Menu");
    System.out.println("Current Change Money: P" + moneyBank.getTotalChange());
    
    moneyBank.showChangeStock();
    // Create a pop-up dialog for selecting the denomination
    int nChoice = JOptionPane.showOptionDialog(
            null,
            "Select Denomination:",
            "Refill Change",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            denominationOptions,
            denominationOptions[0]);

    if (nChoice != 8) {
        // The user selected a denomination (not "Exit")
        String inputQuantity = JOptionPane.showInputDialog(
                null,
                "Input Quantity of Chosen Denomination:",
                "Refill Change",
                JOptionPane.QUESTION_MESSAGE);

        // Check if the input is valid (a positive integer)
        try {
            int nQuantity = Integer.parseInt(inputQuantity);
            if (nQuantity > 0) {
                // Perform the refill operation based on the selected denomination and quantity
                float denominationValue = Float.parseFloat(denominationOptions[nChoice].substring(1));
                moneyBank.refillChange(denominationValue, nQuantity);

                // Display a message to confirm the refill
                JOptionPane.showMessageDialog(
                        null,
                        "Refilled " + nQuantity + " " + denominationOptions[nChoice],
                        "Refill Change",
                        JOptionPane.INFORMATION_MESSAGE);
                populateTableWithItems();
            } else {
                // The quantity input is invalid (not a positive integer)
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid quantity (a positive integer).",
                        "Refill Change",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            // The quantity input is not a valid integer
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a valid quantity (a positive integer).",
                    "Refill Change",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void populateTableWithItems() {
    int i;
    int count;
    // Create the table model with the column names
    DefaultTableModel tableModel = new DefaultTableModel(
        new Object[][]{},
        new String[]{"Item ID", "Name", "Price", "Quantity", "Calories"}
    );

    // Add data to the table model for regular items
    for (i = 0; i < 8; i++) {
        count = i + 1;
        Object[] rowData = {
            count,
            itemSlots.get(i).getItemName(),
            itemSlots.get(i).getItemAmount(),
            itemSlots.get(i).getItemQuantity(),
            itemSlots.get(i).getItemCal()
        };
        tableModel.addRow(rowData);
    }

    // Add data to the table model for special items
    if(isSpecial){
    for (i = 0; i < 8; i++) {
        Object[] rowData = {
            i + 1,
            specialItemSlots.get(i).getItemName(),
            specialItemSlots.get(i).getItemAmount(),
            specialItemSlots.get(i).getItemQuantity(),
            specialItemSlots.get(i).getItemCal()
        };
        tableModel.addRow(rowData);
    }
    }
    // Set the table model to the JTable
    jTable1.setModel(tableModel);
}

public void transactions() {
    StringBuilder transactionDetails = new StringBuilder();

    for (int i = 0; i < 8; i++) {
        if (itemSlots.get(i).getItemQuantity() < itemSlots.get(i).getInitialQuantity()) {
            int transactionQuantity = itemSlots.get(i).getInitialQuantity() - itemSlots.get(i).getItemQuantity();
            transactionDetails.append("[")
                    .append(itemSlots.get(i).getItemName())
                    .append("] Sold = ")
                    .append(transactionQuantity)
                    .append("\n");
        }
    }

    if (isSpecial) {
        for (int i = 0; i < 8; i++) {
            if (specialItemSlots.get(i).getItemQuantity() < specialItemSlots.get(i).getInitialQuantity()) {
                int transactionQuantity = specialItemSlots.get(i).getInitialQuantity() - specialItemSlots.get(i).getItemQuantity();
                transactionDetails.append("[")
                        .append(specialItemSlots.get(i).getItemName())
                        .append("] Sold = ")
                        .append(transactionQuantity)
                        .append("\n");
            }
        }
    }

    if (transactionDetails.length() > 0) {
        // Remove the last newline character from the StringBuilder
        transactionDetails.deleteCharAt(transactionDetails.length() - 1);

        // Display the transaction details in a pop-up dialog
        JOptionPane.showMessageDialog(
                null,
                transactionDetails.toString(),
                "Transaction Details",
                JOptionPane.INFORMATION_MESSAGE);
    } else {
        // No transactions to display
        JOptionPane.showMessageDialog(
                null,
                "No transactions to display.",
                "Transaction Details",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

public void collectPayment() {
    String[] options = {"Withdraw", "Back to Maintenance Menu"};

    int result = JOptionPane.showOptionDialog(
            null,
            "Current Amount: P" + moneyBank.getTotalPayment(),
            "Collect Payment",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

    if (result == 0) {
        if (moneyBank.getTotalPayment() <= 0) {
            // Display a pop-up dialog if there is no balance to withdraw
            JOptionPane.showMessageDialog(
                    null,
                    "No Balance to Withdraw!",
                    "Collect Payment",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Display a pop-up dialog to confirm the withdrawal
            int confirmChoice = JOptionPane.showConfirmDialog(
                    null,
                    "Withdrawing: P" + moneyBank.getTotalPayment(),
                    "Collect Payment",
                    JOptionPane.YES_NO_OPTION);

            if (confirmChoice == JOptionPane.YES_OPTION) {
                // Perform the withdrawal operation
                moneyBank.collectPayment();

                // Display a pop-up dialog with the successful withdrawal message
                JOptionPane.showMessageDialog(
                        null,
                        "Withdrawal Successful!",
                        "Collect Payment",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    // No need for else condition here as selecting the second option automatically cancels the operation.
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        restockItemsBtn = new javax.swing.JButton();
        BackBtn = new javax.swing.JToggleButton();
        addChangeBtn = new javax.swing.JToggleButton();
        repriceItemsBtn = new javax.swing.JToggleButton();
        collectMoneyBtn = new javax.swing.JToggleButton();
        printTransactionsBtn = new javax.swing.JToggleButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jToggleButton2.setText("jToggleButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Vending Machine Maintenance");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Name", "Price", "Quantity", "Calories"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        restockItemsBtn.setText("Restock Items");
        restockItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockItemsBtnActionPerformed(evt);
            }
        });

        BackBtn.setText("Go Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        addChangeBtn.setText("Add Change");
        addChangeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChangeBtnActionPerformed(evt);
            }
        });

        repriceItemsBtn.setText("Reprice Items");
        repriceItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repriceItemsBtnActionPerformed(evt);
            }
        });

        collectMoneyBtn.setText("Collect Money");
        collectMoneyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectMoneyBtnActionPerformed(evt);
            }
        });

        printTransactionsBtn.setText("Print Transactions");
        printTransactionsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTransactionsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(repriceItemsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(restockItemsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(collectMoneyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addChangeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(printTransactionsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BackBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(restockItemsBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addChangeBtn)
                            .addComponent(printTransactionsBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(collectMoneyBtn)
                            .addComponent(repriceItemsBtn)
                            .addComponent(BackBtn))
                        .addGap(12, 12, 12))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void restockItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockItemsBtnActionPerformed
        maintenanceRestock();
    }//GEN-LAST:event_restockItemsBtnActionPerformed

    private void addChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChangeBtnActionPerformed
        refillChangeMenu();
    }//GEN-LAST:event_addChangeBtnActionPerformed

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        dispose();
    }//GEN-LAST:event_BackBtnActionPerformed

    private void repriceItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repriceItemsBtnActionPerformed
        setPrices();
    }//GEN-LAST:event_repriceItemsBtnActionPerformed

    private void collectMoneyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectMoneyBtnActionPerformed
        collectPayment();
    }//GEN-LAST:event_collectMoneyBtnActionPerformed

    private void printTransactionsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTransactionsBtnActionPerformed
    transactions();
    }//GEN-LAST:event_printTransactionsBtnActionPerformed

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BackBtn;
    private javax.swing.JToggleButton addChangeBtn;
    private javax.swing.JToggleButton collectMoneyBtn;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton printTransactionsBtn;
    private javax.swing.JToggleButton repriceItemsBtn;
    private javax.swing.JButton restockItemsBtn;
    // End of variables declaration//GEN-END:variables
}
