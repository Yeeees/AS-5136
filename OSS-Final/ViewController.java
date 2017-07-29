
/**
 * This controller is used to create different views throughout the system's running.
 * it will record current view, type of current view and a refferrence to the container controller.
 *
 * @author jiaji
 */
public class ViewController {
    private ContainerController conCtrl;
    private View curView;
    private ViewType viewType;
    
    public void setCurView(View view){
        curView = view;
    }
    
    /**
     * This method is used in the initiation of the system, 
     * the Login view will be display and waits for user inputing username and password.
     */
    public void initOSS(ContainerController con){
        conCtrl = con;
        curView = new LoginView(conCtrl,this);
        viewType = ViewType.Login;
        curView.display();
    }
    
    
    /**
     * This method is used by other views to create another view based on demands.
     */
    public void createView(ViewType type, boolean isParentKept,Cart cart){
        
        switch (type){
            case Login:createLogin(isParentKept);break;
            case Shop:createShop(isParentKept,cart);break;
            case Cart:createCart(isParentKept,cart);break;
            case Confirm:createConfirm(isParentKept,cart);break;
            case Admin:createAdmin(isParentKept);break;
            case Mechanic:createMechanic(isParentKept);
        }
        
    }
    
    
    /////////////////////////////////////////////////
    //Below are methods used by the createView() method.
    /////////////////////////////////////////////////
    private void handleNewViewAndCurView(View tempView, boolean isParentKept){
        if(isParentKept){
            curView.setEnabled(false);
            tempView.setParent(curView);
            curView = tempView;
            curView.display();
        }
        else{
            View toDispose = curView;
            curView = tempView;
            curView.display();
            toDispose.dispose();
        }
    }
    
    private void createLogin(boolean isParentKept){
        View tempView = new LoginView(conCtrl,this);
        handleNewViewAndCurView(tempView,isParentKept);
    }
    
    private void createShop(boolean isParentKept, Cart cart){
        View tempView = new ShopView(conCtrl,this,cart);
        handleNewViewAndCurView(tempView,isParentKept);
    }
    
    private void createCart(boolean isParentKept, Cart cart){
        View tempView = new CartView(conCtrl,this,cart);
        handleNewViewAndCurView(tempView,isParentKept);
    }
    
    private void createConfirm(boolean isParentKept, Cart cart){
        View tempView = new ConfirmationView(conCtrl,this,cart);
        handleNewViewAndCurView(tempView,isParentKept);
    }
    
    private void createAdmin(boolean isParentKept){
        View tempView = new AdminView(conCtrl,this);
        handleNewViewAndCurView(tempView,isParentKept);
    }
    
    private void createMechanic(boolean isParentKept){
        View tempView = new MechanicView(conCtrl,this);
        handleNewViewAndCurView(tempView,isParentKept);
    }
    
}
