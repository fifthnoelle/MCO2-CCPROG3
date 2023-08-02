package com.mycompany.mco_gui;


public class SpecialItems extends items {
    private String prepStatement;
    private boolean indivItem = false;

    public SpecialItems(String itemName, float itemAmount, int itemCal, String prepStatement){
        super(itemName, itemAmount, itemCal);
        this.prepStatement = prepStatement;
    }

    public SpecialItems(String itemName, float itemAmount, int itemCal, String prepStatement, boolean indivItem){
        super(itemName, itemAmount, itemCal);
        this.prepStatement = prepStatement;
        this.indivItem = indivItem;
    }

    public String getPrepStatement(){
        return this.prepStatement;
    }

    public boolean checkIndiv(){
        return this.indivItem;
    }

}
