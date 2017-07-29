
/**
 * Customer can only view Vehicle nut not buy any.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vehicle extends Item
{
    
    public Vehicle()
    {
        
    }
    
    public Vehicle(String itemId,String itemName, double itemPrice, String itemDesc)
    {
        super(itemId, itemName, itemPrice, itemDesc);
    }
}
