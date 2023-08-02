package com.mycompany.mco_gui;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<items> itemSlots;
    private ArrayList<SpecialItems> specialItemSlots;
    private Scanner scan = new Scanner(System.in);

    public Inventory(){
        this.itemSlots = new ArrayList<items>();
        this.specialItemSlots = new ArrayList<SpecialItems>();
        }

/** 
     * Initializes the quantity of the items inside the vending machine before operating
     * @param scan the Scanner object used for getting the user's input
     */
    /*public void initializeQuantity(){
        int i, quantity;
        for(i=0 ; i<8 ; i++){
            System.out.print("Enter Quantity for "+ itemSlots.get(i).getItemName()+": ");
            quantity = scan.nextInt();
            itemSlots.get(i).setItemQuantity(quantity);
            itemSlots.get(i).setInitialQuantity(quantity);
        }
    }

    public void initializeSpecialQuantity(){
        int i, quantity;
        System.out.println("\nStocks for Halo-halo\n");
        for(i=0 ; i<specialItemSlots.size() ; i++){
            System.out.print("Enter Quantity for "+ specialItemSlots.get(i).getItemName()+": ");
            quantity = scan.nextInt();
            specialItemSlots.get(i).setItemQuantity(quantity);
            specialItemSlots.get(i).setInitialQuantity(quantity);
        }
    }*/

    public void addSpecialItems(SpecialItems item) {
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

    public ArrayList<SpecialItems> getSpecialItems(){
        return this.specialItemSlots;
    }
}