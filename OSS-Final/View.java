
import javax.swing.JFrame;

/**
 * This class is super class of all view classes in this system.
 * Each view object will refer to the container controller and view controller objects,
 * also a referrence to its parent view, which means the view creating the current view.
 *
 * @author jiaji
 */
public abstract class View extends JFrame{
    protected ContainerController conCtrl;
    protected ViewController viewCtrl;
    protected View parent;
    
    private View(String title){
        super(title);
    }

    public View(String title, ContainerController conCtrl, ViewController viewCtrl) {
        this(title);
        this.conCtrl = conCtrl;
        this.viewCtrl = viewCtrl;
    }
    
    public ContainerController getContainerCtrl(){
        return conCtrl;
    }
    
    public ViewController getViewCtrl(){
        return viewCtrl;
    }
    
    public void setParent(View parent){
        this.parent = parent;
    }
    
    /**
     * Every view class must implement this method for arranging their controls and register action listener.
     */
    public abstract void display();
    
}
