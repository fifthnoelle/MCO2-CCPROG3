/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mco_gui;

public class Money{
    private float value; //denomination
    private int quantity;

    public Money(float value){
        setValue(value);
    }

    
    /** 
     * Returns the value variable listed inside Money
     * @return float
     */
    public float getValue(){
        return this.value;
    }

    
    /** 
     * Returns the quantity listed inside Money
     * @return int
     */
    public int getQuantity(){
        return this.quantity;
    }
    
    /**
     * Lets value in Money be set with the passed amount
     * @param amount the amount of the value in float
     */
    public void setValue(float amount){
        this.value = amount;
    }

    /**
     * Lets quantity in Money be set with the passed quantity
     * @param quantity the quantity to update with
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
