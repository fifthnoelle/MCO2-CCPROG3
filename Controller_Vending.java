import java.util.*;

public class Controller_Vending{
    
    /** 
     * This is the main code of the vending machine, it initializes the items' name, price, calories, and quantity.
     * it also serves as the menu as to where the user is going to use to navigate through the vending machine.
     * @param args
     */
    public static void main(String[] args) {
        int nChoice;
        boolean bmainContinue = true, isSpecial = false;
        Scanner scan = new Scanner(System.in);
        MoneyBank moneyBank= new MoneyBank();
        Inventory inventory = new Inventory();
        Maintenance maintenance = new Maintenance(moneyBank, inventory);
        RegularVendingMachine session = new RegularVendingMachine(moneyBank, inventory);
        SpecialVendingMachine specialSession = new SpecialVendingMachine(moneyBank, inventory);

        items crushedIce = new items("Crushed Ice", (float) 10.00, 0);
        items milk = new items("Evaporated Milk", (float)20.00, 122);
        items sweetenedSaba = new items("Sweetened Saba", (float)25.00, 155);
        items jackfruit = new items("Langka Fruit", (float)15.00, 157);
        items sweetBeans = new items("Sweet Beans", (float)30.00, 172);
        items sweetenedMacapuno = new items("Macapuno", (float) 25.00, 120);
        items ubeIcecream = new items("Ube Ice Cream", (float) 50.00, 273);
        items mangoIcecream = new items("Mango Ice Cream", (float) 50.00, 290);

        inventory.addItems(crushedIce);
        inventory.addItems(milk);
        inventory.addItems(sweetenedSaba);
        inventory.addItems(jackfruit);
        inventory.addItems(sweetBeans);
        inventory.addItems(sweetenedMacapuno);
        inventory.addItems(ubeIcecream);
        inventory.addItems(mangoIcecream);

        SpecialItem sweetenedRedBeans = new SpecialItem("Sweetened Red Beans", (float) 5.00, 50, "Tossing the red beans...");
        SpecialItem spSweetenedMacapuno = new SpecialItem("Sweetened Macapuno", (float) 10.00, 100, "Adding sweetened macapuno...");
        SpecialItem sweetenedLangka = new SpecialItem("Sweetened Langka", (float) 10.00, 89, "Topping some Langka in...");
        SpecialItem cookedSago = new SpecialItem("Cooked Sago", (float) 5.00, 30, "Putting the cooked sago...");
        SpecialItem shavedIce = new SpecialItem("Shaved Ice", (float) 5.00, 0, "Scooping some shaved ice and throwing them in...", true);
        SpecialItem evaporatedMilk = new SpecialItem("Evaporated Milk", (float) 5.00, 112, "Pouring evaporated milk...");
        SpecialItem condensedMilk = new SpecialItem("Condensed Milk", (float) 5.00, 126, "Pouring down the condensed milk...");
        SpecialItem ubeHalaya = new SpecialItem("Ube Halaya", (float) 5.00, 103, "Throwing in ube halaya...", true);

        inventory.addSpecialItems(sweetenedRedBeans);
        inventory.addSpecialItems(spSweetenedMacapuno);
        inventory.addSpecialItems(sweetenedLangka);
        inventory.addSpecialItems(cookedSago);
        inventory.addSpecialItems(shavedIce);
        inventory.addSpecialItems(evaporatedMilk);
        inventory.addSpecialItems(condensedMilk);
        inventory.addSpecialItems(ubeHalaya);

        do{
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Create Regular Vending Machine");
        System.out.println("[2] Create Special Vending Machine");
        System.out.println("[0] Exit");

        nChoice = scan.nextInt();
        
        switch (nChoice) {
        
            case 1: System.out.println("\n\nStocks for Regular Vending Machine\n");
                    inventory.initializeQuantity(scan);
                    bmainContinue = false;
                    isSpecial = false;
                break;
            case 2: System.out.println("\n\nStocks for Special Vending Machine\n");
                    inventory.initializeQuantity(scan);
                    inventory.initializeSpecialQuantity();
                    bmainContinue = false;
                    isSpecial = true;
                break;
            case 0: bmainContinue = false;
                System.out.println("Thank you for using the VENDING MACHINE!");
                break;
            default: System.out.println("Invalid Input");
        }
        }while(bmainContinue);

        maintenance.setMaintenanceType(isSpecial);
        bmainContinue = true;
        
        do{
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Use Vending Machine");
        System.out.println("[2] Maintenance");
        System.out.println("[0] Exit");

        nChoice = scan.nextInt();
        
        switch (nChoice) {
        
            case 1: if(!isSpecial){
                        session.updateInventory();
                        session.RegularVendMenu(scan);
                    }else if (isSpecial){
                        specialSession.specialVendMenu();
                    }
                break;
            case 2: maintenance.updateInventory();
                    maintenance.maintenanceMenu(scan);
                break;
            case 0: bmainContinue = false;
                System.out.println("Thank you for using the VENDING MACHINE!");
                break;
            default: System.out.println("Invalid Input");
        }
        }while(bmainContinue);

        scan.close();

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}