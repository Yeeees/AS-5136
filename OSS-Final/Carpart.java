
/**
 * Car part is item that customer can put into cart and buy.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Carpart extends Item
{
    // instance variables - replace the example below with your own
    private int quantity;
    private CarpartType category;

    public Carpart() 
    {
        
    }

    public Carpart(String itemId, String itemName, double itemPrice, String itemDesc, int quantity, CarpartType category) {
        super(itemId, itemName, itemPrice, itemDesc);
        this.quantity = quantity;
        this.category = category;
    }
    
    

    public void setQuantity(int num)
    {
        this.quantity = num;
    }
    
    public void setCategory(CarpartType name)
    {
        this.category = name;
    }
    
    public int getQuantity()
    {
        return this.quantity;
    }
    
    public CarpartType getCategory()
    {
        return this.category;
    }
}
