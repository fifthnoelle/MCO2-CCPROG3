public class SpecialItem extends items {
    private String prepStatement;
    private boolean indivItem = false;
    private int chosenQuantity = 0;

    public SpecialItem(String itemName, float itemAmount, int itemCal, String prepStatement){
        super(itemName, itemAmount, itemCal);
        this.prepStatement = prepStatement;
    }

    public SpecialItem(String itemName, float itemAmount, int itemCal, String prepStatement, boolean indivItem){
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
