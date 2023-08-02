/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mco_gui;

/**
 *
 * @author rdpun
 */
public class items {
    private String itemName;
    private float itemAmount;
    private int itemQuantity;
    private int initialQuantity;
    private int itemCal;

    public items(String itemName, float itemAmount, int itemCal){
        this.itemName = itemName;
        this.itemAmount = itemAmount;
        this.itemCal = itemCal;
    }
    
    public items(String itemName, int itemQuantity, float itemAmount, int itemCal){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemAmount = itemAmount;
        this.itemCal = itemCal;
    }

    
    /** 
     * gets the item's name.
     * @return returns the item's name.
     */
    public String getItemName() {
        return itemName;
    }
    
    /** 
     * gets the items's price.
     * @return returns the item's price.
     */
    public float getItemAmount() {
        return itemAmount;
    }

    /** 
     * gets the items's quantity.
     * @return returns the item's quantity.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /** 
     * gets the items's calories.
     * @return returns the item's calories.
     */
    public int getItemCal() {
        return itemCal;
    }

    /** 
     * gets the items's quantity (initial).
     * @return returns the item's quantity (initial).
     */
    public int getInitialQuantity() {
        return initialQuantity;
    }

    /** 
     * sets the price of the item.
     * @param itemAmount the price of the item.
     */
    public void setItemAmount(float itemAmount) {
        this.itemAmount = itemAmount;
    }

    /** 
     * sets the calories of the item.
     * @param itemCal the calories of the item.
     */
    public void setItemCal(int itemCal) {
        this.itemCal = itemCal;
    }

    /** 
     * sets the name of the item.
     * @param itemName the name of the item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** 
     * sets the quantity of the item (initial).
     * @param initialQuantity the quantity of the item (initial).
     */
    public void setInitialQuantity(int initialQuantity){
        this.initialQuantity=initialQuantity;
    }

    /** 
     * sets the quantity of the item.
     * @param itemQuantity the quantity of the item.
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}

