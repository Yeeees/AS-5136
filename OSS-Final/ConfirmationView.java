
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
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.*;

/**
 * This view will be displayed when customer decides to checkout, customer's basic information and 
 * subtotal to be paid are displayed here, the customer can choose to checkout or cancel; the former one 
 * will lead to create a new cart object, the latter one will lead customer go back to the cart view.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConfirmationView extends View {

    private Cart curCart;
    private Customer curCust;

    private JButton btnCancel;
    private JButton btnConfirm;
    private JLabel lblTitle;
    private JPanel panel1;

    public ConfirmationView(ContainerController conCtrl, ViewController viewCtrl, Cart cart) {
        super("Confirmation", conCtrl, viewCtrl);
        curCart = cart;
        curCust = cart.getCustomer();
    }

    public void display() {

        this.setSize(300, 500);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(300, 500));
        contentPane.setBackground(new Color(192, 192, 192));

        btnCancel = new JButton();
        btnCancel.setBounds(156, 458, 90, 35);
        btnCancel.setBackground(new Color(214, 217, 223));
        btnCancel.setForeground(new Color(0, 0, 0));
        btnCancel.setEnabled(true);
        btnCancel.setFont(new Font("sansserif", 0, 12));
        btnCancel.setText("Cancel");
        btnCancel.setVisible(true);
        btnCancel.addActionListener(e -> {
            parent.setEnabled(true);
            getViewCtrl().setCurView(parent);
            this.dispose();
        });

        btnConfirm = new JButton();
        btnConfirm.setBounds(42, 458, 90, 35);
        btnConfirm.setBackground(new Color(214, 217, 223));
        btnConfirm.setForeground(new Color(0, 0, 0));
        btnConfirm.setEnabled(true);
        btnConfirm.setFont(new Font("sansserif", 0, 12));
        btnConfirm.setText("Confirm");
        btnConfirm.setVisible(true);
        btnConfirm.addActionListener(e -> {
            Date date = new Date();
            curCart.setMadeDate(date);
            conCtrl.addCart(curCart);
            parent.setEnabled(true);
            getViewCtrl().setCurView(parent);
            ((CartView)parent).afterOrderWritten();
            this.dispose();
        });

        lblTitle = new JLabel();
        lblTitle.setBounds(21, 14, 90, 35);
        lblTitle.setBackground(new Color(214, 217, 223));
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setEnabled(true);
        lblTitle.setFont(new Font("sansserif", 0, 12));
        lblTitle.setText("E-Receipe");
        lblTitle.setVisible(true);

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(14, 58, 264, 395);
        panel1.setBackground(new Color(214, 217, 223));
        panel1.setForeground(new Color(0, 0, 0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif", 0, 12));
        panel1.setVisible(true);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Thank you : " + curCust.getName() + " ! "));
        panel1.add(new JLabel("We are dealing with you order. "));
        panel1.add(new JLabel("Please Confirm your information : "));
        panel1.add(new JLabel("    Address : " + curCust.getAddress()));
        panel1.add(new JLabel("    Email : " + curCust.getEmail()));
        panel1.add(new JLabel("    Phone : " + curCust.getPhone()));
        panel1.add(new JLabel("Your total Payment : " + calculatePayment()));
        

        //adding components to contentPane panel
        contentPane.add(btnCancel);
        contentPane.add(btnConfirm);
        contentPane.add(lblTitle);
        contentPane.add(panel1);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parent.setEnabled(true);
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
     * This method returns how much the user should pay for the order.
     */
    private String calculatePayment() {
        double pay = 0;
        for (CartItem line : curCart.getItems()) {
            double oneLine = line.getAmount() * line.getCarpart().getItemPrice();
            pay += oneLine;
        }

        DecimalFormat dFormat = new DecimalFormat("#.00");
        return "$" + dFormat.format(pay);
    }
}
