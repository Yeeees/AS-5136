import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Every time a customer made an order, the purchase details are recorded in objects of this class
 * and kept in the system.
 * 
 * @author jiaji
 */
public class Cart extends Order{
    private ArrayList<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(Customer customer, Date madeDate) {
        super(customer, madeDate);
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }
    
    public void addItems(CartItem item){
    	items.add(item);
    }
}
