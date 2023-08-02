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
public class TestVend_GUI extends javax.swing.JFrame {
    private ArrayList<items> itemSlots;
    private MoneyBank moneyBank = new MoneyBank();
    
    /**
     * Creates new form CreateNVend_GUI
     */
    public TestVend_GUI(ArrayList<items> itemSlots) {
        this.itemSlots = itemSlots;
        this.moneyBank.setDenomination();
        initComponents();
        populateTableWithItems();
    }

    public void buyItem() {
    int choiceIndex, choiceQuantity, choiceOption;
    float transactionCost = 0, inputAmount;
    // Get input amount from user using pop-up input dialog
    /*inputAmount = Float.parseFloat(JOptionPane.showInputDialog(null, 
            "Please insert cash:", 
            "Cash Input", 
            JOptionPane.QUESTION_MESSAGE));
    JOptionPane.showMessageDialog(null, "Cash: " + inputAmount);*/
    inputAmount = moneyBank.partialPaymentMenu();


    populateTableWithItems();                           //updates the table with the items added
    jLabel2.setText(Float.toString(inputAmount)); //updates the cash on the GUI

    // Get chosen item ID from user using pop-up input dialog
    choiceIndex = Integer.parseInt(JOptionPane.showInputDialog(null, 
            "What do you want to buy? (Input the item no.)", 
            "Item Selection", 
            JOptionPane.QUESTION_MESSAGE)) - 1;
    
    // Shows the user's chosen item
    JOptionPane.showMessageDialog(null, 
            "Item selected [" + 
             itemSlots.get(choiceIndex).getItemName() + "]");

    // Get quantity of chosen item ID from user using pop-up input dialog
    choiceQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, 
            "How many would you want to buy?", 
            "Quantity", 
            JOptionPane.QUESTION_MESSAGE));

    if (checkAvailable(choiceQuantity, choiceIndex)) {
        JOptionPane.showMessageDialog(null, "For " + choiceQuantity + " [" + itemSlots.get(choiceIndex).getItemName() + "]");
        
        transactionCost += calculateTransactionCost(choiceQuantity, choiceIndex);
        if (checkPriceMoney(inputAmount, transactionCost)) {
            JOptionPane.showMessageDialog(null, "Total Price: " + transactionCost + "\n");
            Object[] options = { "Check Out", "Cancel Transaction" };
            choiceOption = JOptionPane.showOptionDialog(null, "Choose an option:", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            switch (choiceOption) {
                case JOptionPane.NO_OPTION:
                    jLabel2.setText(Integer.toString(0));
                    break;
                case JOptionPane.YES_OPTION:
                    checkOut(transactionCost, choiceQuantity, choiceIndex, inputAmount);
                    updateQuantity(choiceIndex, choiceQuantity);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                    "Money is too little.", 
                    "Insufficient Funds", 
                    JOptionPane.WARNING_MESSAGE);
            jLabel2.setText(Integer.toString(0));
        }
    } else {
        JOptionPane.showMessageDialog(null, 
                "There are only " + 
               itemSlots.get(choiceIndex).getItemQuantity() + 
                        " in the vending machine right now.", 
                "Out of Stock", 
                JOptionPane.WARNING_MESSAGE);
            jLabel2.setText(Integer.toString(0));
    }
}
    
    public void checkOut(float transactionCost, int choiceQuantity, int choiceIndex, float inputAmount) {
    float totalChange;

    String message=" ";
    message += "You bought [" + choiceQuantity + "] [" + itemSlots.get(choiceIndex).getItemName() + "]";
    message += "\n" + choiceQuantity + " x " + itemSlots.get(choiceIndex).getItemAmount();
    message += "\nTotal: " + transactionCost;
    
    totalChange = inputAmount - transactionCost;
    moneyBank.makeChange(totalChange);
    jLabel2.setText(Integer.toString(0));
    populateTableWithItems();  
    JOptionPane.showMessageDialog(null, message, "Check Out", JOptionPane.INFORMATION_MESSAGE);
}
    private void populateTableWithItems() {
        int i;
        int count;
        jLabel2.setText(Integer.toString(0));
        // Create the table model with the column names
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Item ID", "Name", "Price", "Quantity", "Calories"}
        );

        // Add data to the table model
        for (i=0;i<8;i++) {
            count = i+1;
            Object[] rowData = {
                count,
                itemSlots.get(i).getItemName(),
                itemSlots.get(i).getItemAmount(),
                itemSlots.get(i).getItemQuantity(),
                itemSlots.get(i).getItemCal()
            };
            tableModel.addRow(rowData);
        }

        // Set the table model to the JTable
        jTable1.setModel(tableModel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public boolean checkAvailable(int quantity, int itemInd) {
        if(quantity <= itemSlots.get(itemInd).getItemQuantity()){
            return true;
        }
        else 
            return false;
    }
    
    public boolean checkPriceMoney(float amount, float transactionCost){
        if(amount >= transactionCost){
            return true;
        }
        else 
            return false;
    }

    public void updateQuantity(int index, int inputQuantity){ 
        itemSlots.get(index).setItemQuantity(itemSlots.get(index).getItemQuantity()-inputQuantity);
        populateTableWithItems();
    }
    
    public float calculateTransactionCost(int quantity, int index){
        float price = 0;
        int i;
        for(i=0; i<quantity;i++){
            price += itemSlots.get(index).getItemAmount();
        }

        return price;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        OperationsBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        MaintenanceBtn = new javax.swing.JToggleButton();

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
        jLabel1.setText("Testing Created Vending Machine");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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

        OperationsBtn.setText("Operations");
        OperationsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OperationsBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        BackBtn.setText("Go Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Cash:");

        MaintenanceBtn.setText("Maintenance");
        MaintenanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintenanceBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MaintenanceBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OperationsBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BackBtn))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OperationsBtn)
                    .addComponent(jLabel2)
                    .addComponent(BackBtn)
                    .addComponent(jLabel3)
                    .addComponent(MaintenanceBtn))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OperationsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OperationsBtnActionPerformed
        buyItem();
    }//GEN-LAST:event_OperationsBtnActionPerformed

    private void MaintenanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintenanceBtnActionPerformed
        new Maintenance_GUI(itemSlots).setVisible(true);
    }//GEN-LAST:event_MaintenanceBtnActionPerformed

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        dispose();
    }//GEN-LAST:event_BackBtnActionPerformed

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BackBtn;
    private javax.swing.JToggleButton MaintenanceBtn;
    private javax.swing.JButton OperationsBtn;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
