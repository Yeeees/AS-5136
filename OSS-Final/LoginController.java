
/**
 * This class get user information if the user exists in the system.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoginController
{
    private ContainerController conCtrl;
    
    public LoginController(ContainerController conCtrl){
        this.conCtrl = conCtrl;
    }
    
    public User validateUser(String name, String password){
        
        User temp = conCtrl.getUser(name);
        
        if(temp == null){
            return null;
        }
        if(password.equals(temp.getPassword())){
            return temp;
        }else{
            return null;
        }
    }
}
