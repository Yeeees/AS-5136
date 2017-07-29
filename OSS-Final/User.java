/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is the super class for all user entities.
 * 
 * @author jiaji
 */
public class User
{
    // instance variables - replace the example below with your own
    private String id;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private CustType type;

    public User() {
    }

    public User(String id, String userName, String password, String phone, String email, CustType type) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }
    
    

    public void setId(String newId){
        id = newId;
    }
    
    public void setName(String newName){
        userName = newName;
    }
    
    public void setPassword(String newPassword){
        password = newPassword;
    }
    
    public void setPhone(String newPhone){
        phone = newPhone;
    }
    
    public void setEmail(String newEmail){
        email = newEmail;
    }

    public void setType(CustType type) {
        this.type = type;
    }
    
    
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return userName;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public String getEmail(){
        return email;
    }

    public CustType getType() {
        return type;
    }
    
}