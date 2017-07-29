
/**
 * One object of this class stands for an item of the dealer,
 * e.g. a car part or vehicle.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String itemId;
    private String itemName;
    private double itemPrice;
    private String itemDesc;

    public Item() {
    }

    public Item(String itemId, String itemName, double itemPrice, String itemDesc) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDesc = itemDesc;
    }
    
    
    
    public void setItemId(String id)
    {
        this.itemId = id;
    }

    public void setItemName(String name)
    {
        this.itemName = name;
    }
    
    public void setItemPrice(double price)
    {
        this.itemPrice = price;
    }
    
    public void setItemDesc(String desc)
    {
        this.itemDesc = desc;
    }
    
    public String getItemId()
    {
        return this.itemId;
    }

    public String getItemName()
    {
        return this.itemName;
    }
    
    public double getItemPrice()
    {
        return this.itemPrice;
    }
    
    public String getItemDesc()
    {
        return this.itemDesc;
    }
}
