/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Mechanic stands for user who should go to mechanic view after login.
 * @author jiaji
 */
public class Mechanic extends User {

    public Mechanic(String id, String userName, String password, String phone, String email, CustType type) {
        super(id, userName, password, phone, email, type);
    }

    
    
}
