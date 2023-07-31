import java.util.*;

public class MoneyBank {
    Money money;
    private ArrayList<Money> changeMoney = new ArrayList<>();
    private ArrayList<Money> paymentMoney = new ArrayList<>();
    private ArrayList<Money> partialPayment = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    MoneyBank() {
       setDenomination();
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
    public float partialPaymentMenu(){
        int i, nChoice;
        float totalCash = 0;
        
        System.out.println("\nChoose Denominations:");
        do{
        for (i = 0; i < paymentMoney.size(); i++) {
            Money currMoney = paymentMoney.get(i);
            System.out.println("[" + (i+1) + "] P" + currMoney.getValue());
        }
        System.out.println("[" + (0) + "] Done");
        nChoice = scan.nextInt();
        
        
        switch(nChoice){
            case 1: partialPayment.get(0).setQuantity(partialPayment.get(0).getQuantity() + 1);
                    totalCash += partialPayment.get(0).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(0).getValue());
            break;
            case 2: partialPayment.get(1).setQuantity(partialPayment.get(1).getQuantity() + 1);
                    totalCash += partialPayment.get(1).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(1).getValue()); 
            break;
            case 3: partialPayment.get(2).setQuantity(partialPayment.get(2).getQuantity() + 1);
                    totalCash += partialPayment.get(2).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(2).getValue());
            break;
            case 4: partialPayment.get(3).setQuantity(partialPayment.get(3).getQuantity() + 1);
                    totalCash += partialPayment.get(3).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(3).getValue());
            break;
            case 5: partialPayment.get(4).setQuantity(partialPayment.get(4).getQuantity() + 1);
                    totalCash += partialPayment.get(4).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(4).getValue());
            break;
            case 6: partialPayment.get(5).setQuantity(partialPayment.get(5).getQuantity() + 1);
                    totalCash += partialPayment.get(5).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(5).getValue());
            break;
            case 7: partialPayment.get(6).setQuantity(partialPayment.get(6).getQuantity() + 1);
                    totalCash += partialPayment.get(6).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(6).getValue());
            break;
            case 8: partialPayment.get(7).setQuantity(partialPayment.get(7).getQuantity() + 1);
                    totalCash += partialPayment.get(7).getValue();
                    System.out.println("\nAdded P" + partialPayment.get(7).getValue());
            break;
        }

        System.out.println("Subtotal: " + totalCash + "\n");
        }while(nChoice != 0);

        return totalCash;
    }

    public void resetPartialPayment(){
        for (int i = 0; i < partialPayment.size(); i++) {
            Money currMoney = partialPayment.get(i);
            currMoney.setQuantity(0);
            }
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
    public void makeChange(float totalChange) {
        int i;
        float amt = totalChange;

        if(amt>0){
            System.out.println("\nChange:");
        }
        for (i = changeMoney.size()-1; i >= 0; i--) {
            
            Money currMoney = changeMoney.get(i);
            float currVal = currMoney.getValue();
            int currQuantity = currMoney.getQuantity();
            while (amt >= currMoney.getValue() && currMoney.getQuantity() > 0) {
                float fQuantity;
                int quantity;

                fQuantity = amt / currVal;
                quantity = (int)fQuantity;

                if (quantity <= currMoney.getQuantity() && quantity != 0) {

                    amt = amt - (currVal * quantity); 
                    
                    if(currMoney.getQuantity() != 0){
                    System.out.println((int) quantity + " * P" + currVal);
                    }
                    subtractMoney(currVal, (int) quantity); 
                }
                else if (quantity > currMoney.getQuantity() && quantity != 0) {
                    if(currMoney.getQuantity() != 0){
                    System.out.println(currQuantity + " * P" + currVal);
                    }
                    subtractMoney(currVal, currQuantity);
                }
            }
            
        }

        if(amt > 0){
            System.out.println("Not enough change! Maintenance Required. (Change Error)");
            resetPartialPayment();
        }else{
            addPayment();
        }
    }

    /**
     * Shows the quantity of denominations to be used for giving out change to the user.
     */
    public void showChangeStock(){
        int i;
        System.out.println("\nCurrent Change Stock");
        for (i = 0; i < changeMoney.size(); i++) {
            Money currMoney = changeMoney.get(i);
            
            if(currMoney.getQuantity() != 0){
            System.out.println(currMoney.getQuantity() + "\tP" + currMoney.getValue());
            }
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