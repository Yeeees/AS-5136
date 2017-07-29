
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.*;

/**
 * This is the initial screen after a customer successfully login. This window will load and display all 
 * car part and vehicle information, then hold information of current customer and create a new cart for 
 * the customer, the cart will be kept until the customer end a purchase and checkout.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShopView extends View {
    
    //These fields are used to hold current customer and current cart.
    private Cart curCart;
    private Customer curCust;
    
    //These are the window controls.
    private JButton btnGoToCart;
    private JButton btnLogout;
    private JLabel lblUserName;
    private JLabel lblWelcome;
    private JPanel panelCarpartList;
    private JScrollPane scrollPane;

    public ShopView(ContainerController conCtrl, ViewController viewCtrl, Cart newCart) {
        super("Shop", conCtrl, viewCtrl);
        curCart = newCart;
        curCust = curCart.getCustomer();
    }
    
    //Each sub-class of View have to override this class to arrange the controls and initiate the actions.
    public void display() {

        this.setSize(599, 469);
        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(599, 469));
        contentPane.setBackground(new Color(192, 192, 192));

        btnGoToCart = new JButton();
        btnGoToCart.setBounds(417, 57, 90, 35);
        btnGoToCart.setBackground(new Color(214, 217, 223));
        btnGoToCart.setForeground(new Color(0, 0, 0));
        btnGoToCart.setEnabled(true);
        btnGoToCart.setFont(new Font("sansserif", 0, 12));
        btnGoToCart.setText("GoToCart");
        btnGoToCart.setVisible(true);
        //Register Go to Cart
        btnGoToCart.addActionListener(e -> {
            getViewCtrl().createView(ViewType.Cart, true,curCart);
        });

        btnLogout = new JButton();
        btnLogout.setBounds(416, 15, 90, 35);
        btnLogout.setBackground(new Color(214, 217, 223));
        btnLogout.setForeground(new Color(0, 0, 0));
        btnLogout.setEnabled(true);
        btnLogout.setFont(new Font("sansserif", 0, 12));
        btnLogout.setText("Logout");
        btnLogout.setVisible(true);
        //Register Logout
        btnLogout.addActionListener(e -> {
            getViewCtrl().createView(ViewType.Login, false,null);
        });

        lblUserName = new JLabel();
        lblUserName.setBounds(90, 18, 90, 35);
        lblUserName.setBackground(new Color(214, 217, 223));
        lblUserName.setForeground(new Color(0, 0, 0));
        lblUserName.setEnabled(true);
        lblUserName.setFont(new Font("sansserif", 0, 12));
        lblUserName.setText("");
        lblUserName.setVisible(true);

        lblWelcome = new JLabel();
        lblWelcome.setBounds(26, 20, 123, 31);
        lblWelcome.setBackground(new Color(214, 217, 223));
        lblWelcome.setForeground(new Color(0, 0, 0));
        lblWelcome.setEnabled(true);
        lblWelcome.setFont(new Font("sansserif", 0, 12));
        lblWelcome.setText("Welcome! " + curCust.getName());
        lblWelcome.setVisible(true);
        ////////////////////////////////////////////////////////////////////////////
        //this panel holds all car parts and vehicles.
        panelCarpartList = new JPanel(null);
        panelCarpartList.setBorder(BorderFactory.createEtchedBorder(1));
        panelCarpartList.setSize(478, 317);
        panelCarpartList.setBackground(new Color(214, 217, 223));
        panelCarpartList.setForeground(new Color(0, 0, 0));
        panelCarpartList.setEnabled(true);
        panelCarpartList.setFont(new Font("sansserif", 0, 12));
        panelCarpartList.setVisible(true);
        
        
        //Initiate the Car part list with products stuffed into 
        panelCarpartList.setLayout(new BoxLayout(panelCarpartList, BoxLayout.Y_AXIS));
        //Add vehicle to the list
        ArrayList<Vehicle> vlist = conCtrl.getAllVehicle();
        for(Vehicle temp : vlist){
            VehicleHolder item = new VehicleHolder(temp);
            panelCarpartList.add(item);
        }
        //Add carpart to the list
        ArrayList<Carpart> list = conCtrl.getAllCarpart();
        for(Carpart temp : list){
            ProductHolder item = new ProductHolder(temp,curCart, conCtrl);
            panelCarpartList.add(item);
        }
        
        scrollPane = new JScrollPane(panelCarpartList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(34, 115,480, 319);
        scrollPane.setBackground(new Color(214, 217, 223));
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setEnabled(true);
        scrollPane.setVisible(true);
        
        //////////////////////////////////////////////////////////////////////////////////////////////
        //adding components to contentPane panel
        contentPane.add(btnGoToCart);
        contentPane.add(btnLogout);
        contentPane.add(lblUserName);
        contentPane.add(lblWelcome);
        contentPane.add(scrollPane);
        
        //Save on close
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(curCart.getItems().size() != 0){
                    for(CartItem item : curCart.getItems()){
                        item.getCarpart().setQuantity(item.getCarpart().getQuantity() + item.getAmount());
                    } 
                }
                conCtrl.saveUserData();
                conCtrl.saveCarpartData();
                conCtrl.saveOrderData();
            }
        });

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    
    /**
     * This method is used to refresh all carpart components in the panel list every time after the 
     * shop view is reacttivated.
     */
    public void refreshShopView(){
        Component[] arrComp = panelCarpartList.getComponents();
        for(Component comp : arrComp){
            if(comp instanceof ProductHolder)
                ((ProductHolder)comp).refresh();
        }
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    /**
     * Used to create a new cart after current used has checked out.
     */
    public void setNewCart(Cart newCart){
        curCart = newCart;
        curCust = curCart.getCustomer();
    }
    
    
}

