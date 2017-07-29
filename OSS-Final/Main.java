/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is the program entry class, only takes one method that initiate the whole system
 * by creating one container controller and one view controller.
 * 
 * @author jiaji
 *
 */
public class Main {
    
    public static void main(String[] args){
        ContainerController conCtrl = new ContainerController();
        ViewController viewCtrl = new ViewController();
        
        viewCtrl.initOSS(conCtrl);
        
    }
    
}
