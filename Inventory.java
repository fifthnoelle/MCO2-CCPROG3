import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<items> itemSlots;
    private ArrayList<SpecialItem> specialItemSlots;
    private Scanner scan = new Scanner(System.in);

    public Inventory(){
        this.itemSlots = new ArrayList<items>();
        this.specialItemSlots = new ArrayList<SpecialItem>();
        }

/** 
     * Initializes the quantity of the items inside the vending machine before operating
     * @param scan the Scanner object used for getting the user's input
     */
    public void initializeQuantity(Scanner scan){
        int i, quantity;
        System.out.println("Stocks for Regular Vending Machine\n");
        for(i=0 ; i<8 ; i++){
            System.out.print("Enter Quantity for "+ itemSlots.get(i).getItemName()+": ");
            quantity = scan.nextInt();
            itemSlots.get(i).setItemQuantity(quantity);
            itemSlots.get(i).setInitialQuantity(quantity);
        }
    }

    public void initializeSpecialQuantity(){
        int i, quantity;
        System.out.println("\n\nStocks for Special Vending Machine");
        for(i=0 ; i<specialItemSlots.size() ; i++){
            System.out.print("Enter Quantity for "+ specialItemSlots.get(i).getItemName()+": ");
            quantity = scan.nextInt();
            specialItemSlots.get(i).setItemQuantity(quantity);
            specialItemSlots.get(i).setInitialQuantity(quantity);
        }
    }

    public void addSpecialItems(SpecialItem item) {
        specialItemSlots.add(item);
    }

    /** 
     * adds items to the array list.
     * @param item is the array list of the item.
     */
    public void addItems(items item) {
        itemSlots.add(item);
    }


    public ArrayList<items> getItems(){
        return this.itemSlots;
    }

    public ArrayList<SpecialItem> getSpecialItems(){
        return this.specialItemSlots;
    }
}