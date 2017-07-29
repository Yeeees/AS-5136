/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Administrator means user who should go to admin view after login.
 * @author jiaji
 */
public class Administrator extends User{

    public Administrator(String id, String userName, String password, String phone, String email, CustType type) {
        super(id, userName, password, phone, email, type);
    }
    
}
