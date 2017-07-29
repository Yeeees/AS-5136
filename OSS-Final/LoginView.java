
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
import javax.swing.border.Border;
import javax.swing.*;

/**
 * Login view is the first view when user launch the system. 
 * it will ask user to input their user name and password to login
 * user will be led to different step view based on the user type 
 * derived from the information they input.
 *
 * @author jiaji
 */
public class LoginView extends View {

    private LoginController loginCtrl;

    private JLabel lblUname;
    private JButton btnExit;
    private JButton btnLogin;
    private JLabel lblPW;
    private JPasswordField pwPassword;
    private JTextField tfUsername;

    public LoginView(ContainerController conCtrl, ViewController viewCtrl) {
        super("Login OSS", conCtrl, viewCtrl);
        loginCtrl = new LoginController(conCtrl);
    }

    public void display() {
        setBounds(350, 150, 500, 400);
        //setResizable(false);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.setBackground(new Color(192, 192, 192));

        lblUname = new JLabel();
        lblUname.setBounds(69, 103, 90, 35);
        lblUname.setBackground(new Color(214, 217, 223));
        lblUname.setForeground(new Color(0, 0, 0));
        lblUname.setEnabled(true);
        lblUname.setFont(new Font("sansserif", 0, 12));
        lblUname.setText("User Name : ");
        lblUname.setVisible(true);

        btnExit = new JButton();
        btnExit.setBounds(266, 277, 90, 35);
        btnExit.setBackground(new Color(214, 217, 223));
        btnExit.setForeground(new Color(0, 0, 0));
        btnExit.setEnabled(true);
        btnExit.setFont(new Font("sansserif", 0, 12));
        btnExit.setText("Exit");
        btnExit.setVisible(true);
        //Register Exit
        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        btnLogin = new JButton();
        btnLogin.setBounds(117, 278, 90, 35);
        btnLogin.setBackground(new Color(214, 217, 223));
        btnLogin.setForeground(new Color(0, 0, 0));
        btnLogin.setEnabled(true);
        btnLogin.setFont(new Font("sansserif", 0, 12));
        btnLogin.setText("Login");
        btnLogin.setVisible(true);
        btnLogin.addActionListener(e -> {
            toShopView();
        });

        lblPW = new JLabel();
        lblPW.setBounds(69, 165, 90, 35);
        lblPW.setBackground(new Color(214, 217, 223));
        lblPW.setForeground(new Color(0, 0, 0));
        lblPW.setEnabled(true);
        lblPW.setFont(new Font("sansserif", 0, 12));
        lblPW.setText("Password : ");
        lblPW.setVisible(true);

        pwPassword = new JPasswordField();
        pwPassword.setBounds(178, 167, 178, 30);
        pwPassword.setBackground(new Color(214, 217, 223));
        pwPassword.setForeground(new Color(0, 0, 0));
        pwPassword.setEnabled(true);
        pwPassword.setFont(new Font("sansserif", 0, 12));
        pwPassword.setVisible(true);

        tfUsername = new JTextField();
        tfUsername.setBounds(175, 100, 183, 33);
        tfUsername.setBackground(new Color(255, 255, 255));
        tfUsername.setForeground(new Color(0, 0, 0));
        tfUsername.setEnabled(true);
        tfUsername.setFont(new Font("sansserif", 0, 12));
        tfUsername.setText("");
        tfUsername.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(lblUname);
        contentPane.add(btnExit);
        contentPane.add(btnLogin);
        contentPane.add(lblPW);
        contentPane.add(pwPassword);
        contentPane.add(tfUsername);
        
        //Save on close
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
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
        //Display the view.

        if (parent != null) {
            parent.dispose();
        }
        setVisible(true);
    }
    
    
    /**
     * With help from login controller, this view will leads user to different step view based on their types.
     * if the user is a customer, create a new cart for him.
     */
    public void toShopView() {
        User tempUser = loginCtrl.validateUser(tfUsername.getText(), new String(pwPassword.getPassword()));
        if (tempUser != null) {
            switch (tempUser.getType()) {
                case Customer:
                    Cart newCart = new Cart((Customer)tempUser,null); 
                    getViewCtrl().createView(ViewType.Shop, false, newCart);
                    break;
                case Admin:
                    getViewCtrl().createView(ViewType.Admin, false, null);
                    break;
                case Mechanic:
                    getViewCtrl().createView(ViewType.Mechanic, false, null);
                    break;
            }
        } else {
            JOptionPane errorMsg = new JOptionPane();
            errorMsg.showMessageDialog(this, "Login Error, please provide validated information.");
        }
    }
}
