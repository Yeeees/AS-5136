
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
 * This view is for users whose type is administrator.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdminView extends View {

    private JLabel label1;
    private JButton btnLogout;

    public AdminView(ContainerController conCtrl, ViewController viewCtrl) {
        super("Administrator", conCtrl, viewCtrl);
    }

    public void display() {
        this.setSize(383, 286);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(383, 286));
        contentPane.setBackground(new Color(192, 192, 192));

        label1 = new JLabel();
        label1.setBounds(40, 38, 250, 52);
        label1.setBackground(new Color(214, 217, 223));
        label1.setForeground(new Color(0, 0, 0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif", 0, 20));
        label1.setText("Admin View");
        label1.setVisible(true);

        btnLogout = new JButton();
        btnLogout.setBounds(260, 38, 90, 35);
        btnLogout.setBackground(new Color(214, 217, 223));
        btnLogout.setForeground(new Color(0, 0, 0));
        btnLogout.setEnabled(true);
        btnLogout.setFont(new Font("sansserif", 0, 12));
        btnLogout.setText("Logout");
        btnLogout.setVisible(true);
        //Register Logout
        btnLogout.addActionListener(e -> {
            getViewCtrl().createView(ViewType.Login, false, null);
        });

        //adding components to contentPane panel
        contentPane.add(label1);
        contentPane.add(btnLogout);
        
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
        this.setVisible(true);
    }

}
