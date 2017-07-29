/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This represents a line in the order.
 * 
 * @author jiaji
 */
public class CartItem {
    private Carpart carpart;
    private int amount;

    public CartItem() {
    }

    public CartItem(Carpart carpart, int amount) {
        this.carpart = carpart;
        this.amount = amount;
    }

    public Carpart getCarpart() {
        return carpart;
    }

    public int getAmount() {
        return amount;
    }

    public void setCarpart(Carpart carpart) {
        this.carpart = carpart;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
