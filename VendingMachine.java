package com.mycompany.mco_gui;

import java.util.*;

import javax.swing.JOptionPane;

public class VendingMachine{
    private ArrayList<items> itemSlots;
    private MoneyBank moneyBank = new MoneyBank();

    public VendingMachine(){
        this.itemSlots = new ArrayList<>();
    }
    
    /** 
     * Checks if the inputted quantity from the user is lesser or equal 
     * to the current quantity of the items inside the vending machine.
     * @param quantity the input of the user's wanted quantity to be bought.
     * @param itemInd the index of where the item chosen by the user is located.
     * @return true if found, and false if not found.
     */
    public boolean checkAvailable(int quantity, int itemInd) {
        if(quantity <= itemSlots.get(itemInd).getItemQuantity()){
            return true;
        }
        else 
            return false;
    }

    /**
     * Checks if the inputted money from the user is greater than or equal to the current transaction's cost
     * @param amount the amount of money inputted by the user 
     * @param transactionCost the current transaction's total cost
     * @return true if the user's inputted money is >= the transaction's total cost, and returns false if not.
     */
    public boolean checkPriceMoney(float amount, float transactionCost){
        if(amount >= transactionCost){
            return true;
        }
        else 
            return false;
    }


    /** 
     * Initializes the quantity of the items inside the vending machine before operating
     * @param scan the Scanner object used for getting the user's input
     */
    public void initializeQuantity() {
        int i;
        for (i = 0; i<8 ; i++) {
            String itemName = itemSlots.get(i).getItemName();
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(
                    null, "Enter quantity for " + itemName + ":", "Initialize Quantity",
                    JOptionPane.QUESTION_MESSAGE));
            itemSlots.get(i).setInitialQuantity(quantity);
            itemSlots.get(i).setItemQuantity(quantity);
        }
    }
    
    /** 
     * Prints the sold items and how many were sold.
     * @param scan the Scanner object used for getting the user's input
     */
    public void transactions(Scanner scan) {
    for (int i = 0; i < 8; i++) {
        if (itemSlots.get(i).getItemQuantity() < itemSlots.get(i).getInitialQuantity()) {
            int transactionQuantity = itemSlots.get(i).getInitialQuantity() -itemSlots.get(i).getItemQuantity();
            System.out.println("[" + itemSlots.get(i).getItemName() + "] Sold = " + transactionQuantity);
        }
    }
}

    /**
     * The interface wherein the user uses to buy from the Vending Machine.
     * @param scan the Scanner object used for getting the user's input
     */
    public void buyItem(Scanner scan){
        int choiceIndex, choiceQuantity, choiceOption;
        float transactionCost = 0, inputAmount;
        
        System.out.println("Please insert cash");
        inputAmount = moneyBank.partialPaymentMenu();
        System.out.println("Cash: " + inputAmount);

        printItems();
        System.out.println("What do you want to buy? (Input the item no.)");
        choiceIndex = scan.nextInt()-1;
        System.out.println("Item selected [" + itemSlots.get(choiceIndex).getItemName()+"]");
        System.out.println("How many would you want to buy?");
        choiceQuantity = scan.nextInt();

        if(checkAvailable(choiceQuantity,choiceIndex)){

            System.out.println("For "+choiceQuantity+" ["+itemSlots.get(choiceIndex).getItemName()+"]"); 

            transactionCost += calculateTransactionCost(choiceQuantity, choiceIndex);
            if(checkPriceMoney(inputAmount, transactionCost)){
            System.out.println("Total Price: "+ transactionCost +"\n");
            System.out.println("[1] Check Out");
            System.out.println("[0] Cancel Transaction");
            choiceOption=scan.nextInt();
            
                switch(choiceOption){
                    case 0 : 
                    break;
                    case 1 : 
                            checkOut(transactionCost, choiceQuantity, choiceIndex, inputAmount);
                    break;
                }
        }
        else{
        System.out.println("Money is too Little.");
        }
        }
        
        else {
        System.out.println("There are only "+itemSlots.get(choiceIndex).getItemQuantity()+" in the vending machine right now.");
        }
    }

    
    
    /** 
     * Updates the item's quantity
     * @param index the index of where the item chosen by the user is located.
     * @param inputQuantity the user's inputted quantity of how many items are bought.
     */
    public void updateQuantity(int index, int inputQuantity){ 
        itemSlots.get(index).setItemQuantity(itemSlots.get(index).getItemQuantity()-inputQuantity);
    }

    /** 
     * Prints the user's transaction 
     * @param transactionCost the total cost of the transaction.
     * @param choiceQuantity the quantity of how many were bought.
     * @param choiceIndex the index of the chosen item of the user.
     * @param inputAmount the money inputted by the user.
     */
    public void checkOut(float transactionCost, int choiceQuantity, int choiceIndex, float inputAmount) {
        float totalChange;
        boolean hasChange;

            System.out.println("\n\nCheck Out");
            totalChange = inputAmount - transactionCost;
            hasChange = moneyBank.makeChange(totalChange);
            
            if(hasChange){
                System.out
                    .println("\nYou bought [" + choiceQuantity + "] [" + itemSlots.get(choiceIndex).getItemName() + "]");
                System.out.println(choiceQuantity + " x " + itemSlots.get(choiceIndex).getItemAmount());
                System.out.println("Total:" + transactionCost);
                updateQuantity(choiceIndex, choiceQuantity);
            }
        }

    /** 
     * Calculates the transaction cost of the user's session.
     * @param quantity the amount of how many items were being bought.
     * @param index the index of the chosen item by the user.
     * @return returns the total price to be paid by the uer.
     */
    public float calculateTransactionCost(int quantity, int index){
        float price = 0;
        int i;
        for(i=0; i<quantity;i++){
            price += itemSlots.get(index).getItemAmount();
        }

        return price;
    }

    /** 
     * Prints the menu of the vending machine with the item's name, quantity, amount, and calories.
     */
    public void printItems() {
    int i;
    int count;
    System.out.println("╒════════════╤═══════════════════╤══════════╤══════════╤══════════╕");
    System.out.println("│  Item no.  │        Name       │ Quantity │  Price   │ Calories │");
    System.out.println("╞════════════╪═══════════════════╪══════════╪══════════╪══════════╡");
    for (i = 0; i < 8; i++) {
        count = i + 1;
        System.out.printf("│    %2d      │  %-16s │    %2d    │  P%.2f  │   %3d    │%n",
                count, itemSlots.get(i).getItemName(),
                itemSlots.get(i).getItemQuantity(),
                itemSlots.get(i).getItemAmount(),
                itemSlots.get(i).getItemCal());
    }
    System.out.println("╘════════════╧═══════════════════╧══════════╧══════════╧══════════╛");
}

    //public void displayInventory

    /** 
     * adds items to the array list.
     * @param item is the array list of the item.
     */
    public void addItems(items item) {
        itemSlots.add(item);
    }
    
    /** 
     * The menu for maintenance that can let the admin add items,
     * collect money, replenish money, set prices, and print the transactions
     * @param scan the Scanner object used for getting the user's input
     */
    public void maintenanceMenu(Scanner scan){
        int nChoice;
        boolean bContinue = true;
        do{
        System.out.println("\nWelcome to the MAINTENANCE menu\n");
        System.out.println("What do you want to do?");
        System.out.println("[1] Add Items");
        System.out.println("[2] Collect Money");
        System.out.println("[3] Replenish Money");
        System.out.println("[4] Set Prices");
        System.out.println("[5] Print Transactions");
        System.out.println("[0] Exit");
        nChoice = scan.nextInt();
        switch(nChoice){
            case 1: //maintenanceRestock(scan);
            break;
            case 2: collectPayment();
            break;
            case 3: refillChangeMenu();
            break;
            case 4: //setPrices(scan);
            break;
            case 5: transactions(scan);
            break;
            default: bContinue = false;
        }
        }while(bContinue);
      
    }
    
    /** 
     * Lets the admin collect the sales of the vending machine.
     */
    public void collectPayment(){
        System.out.println("Collect Payment \nCurrent Amount: P" + moneyBank.getTotalPayment() + "\n");
        System.out.println("[1] Withdraw");
        System.out.println("\n[0] Back to Maintenance Menu");
        /*switch(nChoice){
            case 1: if(moneyBank.getTotalPayment() <= 0){
                    System.out.println("\n No Balance to Withdraw!");
                    }else
                    {
                    System.out.println("Withdrawing: P" + moneyBank.getTotalPayment() + "\n");
                    moneyBank.collectPayment();
                    }
            break;
            case 0:
            break; 
        }*/

    }

    /** 
     * The menu where the admin can choose which denominations to be refilled.
     */
    public void refillChangeMenu(){
        int nChoice, nQuantity = 0;
        System.out.println("\nRefill Menu");
        System.out.println("Current Change Money: P" + moneyBank.getTotalChange());
        System.out.println("\nSelect Denomination:");
        System.out.println("[1] P1");
        System.out.println("[2] P5");
        System.out.println("[3] P10");
        System.out.println("[4] P20");
        System.out.println("[5] P50");
        System.out.println("[6] P100");
        System.out.println("[7] P200");
        System.out.println("[8] P500");
        System.out.println("[0] Exit");
        //nChoice = scan.nextInt();

        /*if(nChoice != 0){
        System.out.print("Input Quantity of Chosen Denomination: ");
//        nQuantity = scan.nextInt();
        }*/

        /*switch(nChoice){
            case 1: moneyBank.refillChange(1, nQuantity);
            break;
            case 2: moneyBank.refillChange(5, nQuantity);
            break;
            case 3: moneyBank.refillChange(10 , nQuantity);
            break;
            case 4: moneyBank.refillChange(20, nQuantity);
            break;
            case 5: moneyBank.refillChange(50, nQuantity);
            break;
            case 6: moneyBank.refillChange(100, nQuantity);
            break;
            case 7: moneyBank.refillChange(200, nQuantity);
            break;
            case 8: moneyBank.refillChange(500, nQuantity);
            break;
            case 0:
            break; 
        }
*/
    }

    /**
     * Lets the user modify the price of each item. If it is lower than zero
     * it is not going to proceed with the change.
     * @param scan passes the Scanner variable
     */
    /*public void setPrices(Scanner scan){
        int itemInd, newPrice;

        System.out.println("Which item do you want to modify the price");
        itemInd = scan.nextInt()-1;
        if(itemInd <= 7){
        System.out.println("Input new price for [" + itemSlots.get(itemInd).getItemName() + "]:");
        newPrice = scan.nextInt();
        if(newPrice >= 0){
            itemSlots.get(itemInd).setItemAmount(newPrice);
            System.out.println("Updated Price for [" + itemSlots.get(itemInd).getItemName()+"] is: "+ itemSlots.get(itemInd).getItemAmount());
        }
        else
            System.out.println("Invalid Price.");
        }
        else
            System.out.println("There is no such item.");
    }*/

    /**
     * Shows menu for restocking items. Asks user to choose from all items what they want restocked and how many.
     * It will look for said item then update its quantity. It prints the update for the user. If the current quantity
     * and the added quantity is more than 15 or will add up to more than 15, it will print out that it's too large.
     * If the user picks a number not listed, it will print that there is no such item.
     * @param scan passes the Scanner variable
     */
    /*public void maintenanceRestock(Scanner scan){
        int itemInd, restockQuantity;
        
        System.out.println("Which Item do you want to restock? (Enter Item no.)");
        itemInd = scan.nextInt()-1;
        if (itemInd<=7){
        System.out.println("How many [" + itemSlots.get(itemInd).getItemName() + "] would you want to restock?");
        restockQuantity = scan.nextInt();
        if(checkQuantity(restockQuantity, itemSlots.get(itemInd).getItemQuantity())){
            itemSlots.get(itemInd).setItemQuantity(restockQuantity+itemSlots.get(itemInd).getItemQuantity());
            System.out.println("Updated quantity for ["+itemSlots.get(itemInd).getItemName()+"] is "+itemSlots.get(itemInd).getItemQuantity());
        }
        else 
            System.out.println("Input is too large.");
        }
        else 
            System.out.println("There is no such item.");
    }
*/
    /**
     * checks the quantity of an item if it will go past it's limit after restocking.
     * @param quanQuery the quantity of how many would the user want to restock.
     * @param itemQuantity the quantity of the item.
     * @return returns true if it can be restocked, and false if it cant.
     */
    /*public boolean checkQuantity(int quanQuery, int itemQuantity){
        if(quanQuery+itemQuantity<=15)
            return true;
            
        return false;
    }*/

    }


    