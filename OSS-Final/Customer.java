/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Customer means user who will do purchase at shop view.
 * 
 * @author jiaji
 */
public class Customer extends User {
    private String address;
    private boolean isPremium;

    public Customer(){
        
    }
    public Customer(String id, String userName, String password, String phone, String email, CustType type, String address, boolean isPremium) {
        super(id, userName, password, phone, email, type);
        this.address = address;
        this.isPremium = isPremium;
    }

   

    
    public String getAddress() {
        return address;
    }

    public boolean isIsPremium() {
        return isPremium;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }
    
}
