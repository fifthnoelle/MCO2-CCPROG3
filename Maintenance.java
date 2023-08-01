import java.util.ArrayList;
import java.util.Scanner;

public class Maintenance {
    private MoneyBank moneyBank;
    private Inventory inventory;
    private boolean isSpecial;
    private ArrayList<items> itemSlots;
    private ArrayList<SpecialItem> specialItemSlots;
    Scanner scan = new Scanner(System.in);

    public Maintenance(MoneyBank moneyBank, Inventory inventory){
        this.moneyBank = moneyBank;
        this.inventory = inventory;
    }

    public void updateInventory(){
        this.itemSlots = inventory.getItems();
        this.specialItemSlots = inventory.getSpecialItems();
    }

    public void setMaintenanceType(boolean isSpecial){
        this.isSpecial = isSpecial;
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
            case 1: maintenanceRestock(scan);
            break;
            case 2: collectPayment();
            break;
            case 3: refillChangeMenu();
            break;
            case 4: setPrices(scan);
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
        int nChoice = scan.nextInt();
        switch(nChoice){
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
        }

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
        nChoice = scan.nextInt();

        if(nChoice != 0){
        System.out.print("Input Quantity of Chosen Denomination: ");
        nQuantity = scan.nextInt();
        }

        switch(nChoice){
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

    }

    /**
     * Lets the user modify the price of each item. If it is lower than zero
     * it is not going to proceed with the change.
     * @param scan passes the Scanner variable
     */
    public void setPrices(Scanner scan){
        int itemInd, newPrice, nChoice;

        if(!isSpecial){
            printItems(0);
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
                
        }else if(isSpecial){

            System.out.println("Which inventory are you editing");
            System.out.println("[1] Regular Inventory");
            System.out.println("[2] Halo-halo Inventory");
            nChoice = scan.nextInt();

            switch(nChoice){
                case 1:printItems(0);
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
                    break;

                case 2: printItems(1);
                    System.out.println("Which item do you want to modify the price");
                    itemInd = scan.nextInt()-1;

                    if(itemInd <= 7){
                    System.out.println("Input new price for [" + specialItemSlots.get(itemInd).getItemName() + "]:");
                    newPrice = scan.nextInt();
                    if(newPrice >= 0){
                        specialItemSlots.get(itemInd).setItemAmount(newPrice);
                        System.out.println("Updated Price for [" + specialItemSlots.get(itemInd).getItemName()+"] is: "+ specialItemSlots.get(itemInd).getItemAmount());
                    }
                    else
                        System.out.println("Invalid Price.");
                    }
                    else
                        System.out.println("There is no such item.");
                    break;
            }
        }

            
    }

    /**
     * Shows menu for restocking items. Asks user to choose from all items what they want restocked and how many.
     * It will look for said item then update its quantity. It prints the update for the user. If the current quantity
     * and the added quantity is more than 15 or will add up to more than 15, it will print out that it's too large.
     * If the user picks a number not listed, it will print that there is no such item.
     * @param scan passes the Scanner variable
     */
    public void maintenanceRestock(Scanner scan){
        int itemInd, restockQuantity, nChoice;
        
        if(!isSpecial){
            printItems(0);
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
                
        }else if(isSpecial){

            System.out.println("Which inventory are you editing");
            System.out.println("[1] Regular Inventory");
            System.out.println("[2] Halo-halo Inventory");
            nChoice = scan.nextInt();

            switch(nChoice){
                case 1:printItems(0);
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
            break;

                case 2: printItems(1);
                    System.out.println("Which Item do you want to restock? (Enter Item no.)");
            itemInd = scan.nextInt()-1;
            if (itemInd<=7){
            System.out.println("How many [" + specialItemSlots.get(itemInd).getItemName() + "] would you want to restock?");
            restockQuantity = scan.nextInt();
            if(checkQuantity(restockQuantity, specialItemSlots.get(itemInd).getItemQuantity())){
                specialItemSlots.get(itemInd).setItemQuantity(restockQuantity+specialItemSlots.get(itemInd).getItemQuantity());
                System.out.println("Updated quantity for ["+specialItemSlots.get(itemInd).getItemName()+"] is "+specialItemSlots.get(itemInd).getItemQuantity());
            }
            else 
                System.out.println("Input is too large.");
            }
            else 
                System.out.println("There is no such item.");
            break;
        }
                    
            }
    }

    /** 
     * Prints the sold items and how many were sold.
     * @param scan the Scanner object used for getting the user's input
     */
    public void transactions(Scanner scan) {
        
        if(!isSpecial){
            for (int i = 0; i < 8; i++) {
                if (itemSlots.get(i).getItemQuantity() < itemSlots.get(i).getInitialQuantity()) {
                    int transactionQuantity = itemSlots.get(i).getInitialQuantity() -itemSlots.get(i).getItemQuantity();
                    System.out.println("[" + itemSlots.get(i).getItemName() + "] Sold = " + transactionQuantity);
                }
            }
        }else if(isSpecial){
            
            for (int i = 0; i < 8; i++) {
                if (itemSlots.get(i).getItemQuantity() < itemSlots.get(i).getInitialQuantity()) {
                    int transactionQuantity = itemSlots.get(i).getInitialQuantity() -itemSlots.get(i).getItemQuantity();
                    System.out.println("[" + itemSlots.get(i).getItemName() + "] Sold = " + transactionQuantity);
                }
            }

            for (int i = 0; i < 8; i++) {
                if (specialItemSlots.get(i).getItemQuantity() < specialItemSlots.get(i).getInitialQuantity()) {
                    int transactionQuantity = specialItemSlots.get(i).getInitialQuantity() -specialItemSlots.get(i).getItemQuantity();
                    System.out.println("[" + specialItemSlots.get(i).getItemName() + "] Sold = " + transactionQuantity);
                }
            }
        }
    }

    /**
     * checks the quantity of an item if it will go past it's limit after restocking.
     * @param quanQuery the quantity of how many would the user want to restock.
     * @param itemQuantity the quantity of the item.
     * @return returns true if it can be restocked, and false if it cant.
     */
    public boolean checkQuantity(int quanQuery, int itemQuantity){
        if(quanQuery+itemQuantity<=15)
            return true;
            
        return false;
    }

    public void printItems(int option) {
        int i;
        int count;
        
        if(option == 0){
            System.out.println("╒════════════╤═══════════════════╤══════════╤══════════╤══════════╕");
            System.out.println("│  Item no.  │        Name       │ Quantity │  Price   │ Calories │");
            System.out.println("╞════════════╪═══════════════════╪══════════╪══════════╪══════════╡");
            for (i = 0; i < itemSlots.size(); i++) {
                count = i + 1;
                System.out.printf("│    %2d      │  %-16s │    %2d    │  P%.2f  │   %3d    │%n",
                        count, itemSlots.get(i).getItemName(),
                        itemSlots.get(i).getItemQuantity(),
                        itemSlots.get(i).getItemAmount(),
                        itemSlots.get(i).getItemCal());
            }

        System.out.println("╘════════════╧═══════════════════╧══════════╧══════════╧══════════╛");

        }else if(option == 1){
            System.out.println("╒════════════╤══════════════════\u2550\u2550\u2550\u2550\u2550═╤══════════╤══════════╤══════════╕");
            System.out.println("│  Item no.  │          Name          │ Quantity │  Price   │ Calories │");
            System.out.println("╞════════════╪═══════════════════\u2550\u2550\u2550\u2550\u2550╪══════════╪══════════╪══════════╡");
            for (i = 0; i < specialItemSlots.size(); i++) {
                count = i + 1;
                System.out.printf("│    %2d      │  %-20s  │    %2d    │  P%-5.2f  │   %3d    │%n",
                        count, specialItemSlots.get(i).getItemName(),
                        specialItemSlots.get(i).getItemQuantity(),
                        specialItemSlots.get(i).getItemAmount(),
                        specialItemSlots.get(i).getItemCal());
            }
            System.out.println("╘════════════╧═══════════════════\u2550\u2550\u2550\u2550\u2550╧══════════╧══════════╧══════════╛");
        }
    }
}
