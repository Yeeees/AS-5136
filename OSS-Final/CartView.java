
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
 * When customer decides to go to cart this window will be opened meanwhile the shop view will be locked.
 * all products that customer decides to purchase are listed in this screen and the customer can make adjustment
 * is he wants.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CartView extends View {

    private Cart curCart;
    private Customer curCust;

    private JButton btnCheckout;
    private JLabel lblTitle;
    private JPanel paneItems;
    private JScrollPane scrollPane;

    public CartView(ContainerController conCtrl, ViewController viewCtrl, Cart cart) {
        super("Cart", conCtrl, viewCtrl);
        curCart = cart;
    }

    public void display() {
        this.setSize(400, 500);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(400, 500));
        contentPane.setBackground(new Color(192, 192, 192));

        btnCheckout = new JButton();
        btnCheckout.setBounds(252, 420, 90, 35);
        btnCheckout.setBackground(new Color(214, 217, 223));
        btnCheckout.setForeground(new Color(0, 0, 0));
        btnCheckout.setEnabled(true);
        btnCheckout.setFont(new Font("sansserif", 0, 12));
        btnCheckout.setText("Checkout");
        btnCheckout.setVisible(true);
        //Register Checkout
        btnCheckout.addActionListener(e -> {
            if (curCart.getItems().size() == 0) {
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "Error : Empty Cart!!");
                return;
            }
            getViewCtrl().createView(ViewType.Confirm, true, curCart);
        });

        lblTitle = new JLabel();
        lblTitle.setBounds(19, 14, 90, 35);
        lblTitle.setBackground(new Color(214, 217, 223));
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setEnabled(true);
        lblTitle.setFont(new Font("sansserif", 0, 12));
        lblTitle.setText("Cart List");
        lblTitle.setVisible(true);

        paneItems = new JPanel(null);
        paneItems.setBorder(BorderFactory.createEtchedBorder(1));
        paneItems.setSize(350, 360);
        paneItems.setBackground(new Color(214, 217, 223));
        paneItems.setForeground(new Color(0, 0, 0));
        paneItems.setEnabled(true);
        paneItems.setFont(new Font("sansserif", 0, 12));
        paneItems.setVisible(true);

        //Initiate the Car part list with products stuffed into 
        paneItems.setLayout(new BoxLayout(paneItems, BoxLayout.Y_AXIS));
        ArrayList<CartItem> list = curCart.getItems();
        for (CartItem temp : list) {
            CartItemHolder item = new CartItemHolder(temp, conCtrl, curCart, paneItems);
            paneItems.add(item);
        }

        scrollPane = new JScrollPane(paneItems);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(12, 46, 351, 361);
        scrollPane.setBackground(new Color(214, 217, 223));
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setEnabled(true);
        scrollPane.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(btnCheckout);
        contentPane.add(lblTitle);
        contentPane.add(scrollPane);

        //Enable Shop view on close
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parent.setEnabled(true);
                ((ShopView)parent).refreshShopView();
                getViewCtrl().setCurView(parent);
            }
        });

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * This method will be invoked after customer confirms checkout at checkout view, 
     * it will refer to a new cart object.
     */
    public void afterOrderWritten() {
        curCart = new Cart(curCust,null);
        ((ShopView)parent).setNewCart(curCart);
        paneItems.removeAll();
        JOptionPane msg = new JOptionPane();
        paneItems.revalidate();
        paneItems.repaint();
        msg.showMessageDialog(this, "Order was Submitted!");
    }

}
