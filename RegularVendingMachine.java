import java.util.*;

public class RegularVendingMachine{
    private Inventory inventory;
    private ArrayList<items> itemSlots;
    private MoneyBank moneyBank;
    Scanner scan = new Scanner(System.in);

    public RegularVendingMachine(MoneyBank moneyBank, Inventory inventory){
        this.itemSlots = new ArrayList<>();
        this.moneyBank = moneyBank;
        this.inventory = inventory;
    }

    public void updateInventory(){
        this.itemSlots = inventory.getItems();
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
     * The menu where the user can choose to buy or to exit from the Regular Vending Machine.
     * @param scan the Scanner object used for getting the user's input
     */
    public void RegularVendMenu(Scanner scan){
        int nChoice;
        boolean bContinue=true;

        System.out.println("\nWelcome to the REGULAR VENDING MACHINE menu\n");
        printItems();
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Buy");
        System.out.println("[2] Exit");
        nChoice = scan.nextInt();
        do{
        switch(nChoice){
            case 1:buyItem(scan);
            bContinue = false;
            break;
            case 2: bContinue = false;
            break;
        }
        }while(bContinue);
        
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

    if(choiceIndex != 9){
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
                        case 1 : checkOut(transactionCost, choiceQuantity, choiceIndex, inputAmount);
                                updateQuantity(choiceIndex, choiceQuantity);
                        break;
                    }
                }
                else{
                    System.out.println("Money is too Little.");
                    moneyBank.resetPartialPayment();
                }
            }
            else {
                System.out.println("There are only "+itemSlots.get(choiceIndex).getItemQuantity()+" in the vending machine right now.");
                moneyBank.resetPartialPayment();
            }
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

            System.out.println("\n\nCheck Out");
            System.out
                    .println("\nYou bought [" + choiceQuantity + "] [" + itemSlots.get(choiceIndex).getItemName() + "]");
            System.out.println(choiceQuantity + " x " + itemSlots.get(choiceIndex).getItemAmount());
            System.out.println("Total:" + transactionCost);
            totalChange = inputAmount - transactionCost;
            moneyBank.makeChange(totalChange);

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

}