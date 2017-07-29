
import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

/**
 * This class is used to hold and display information for each order line.
 * Generally, every time current customer add carpart into cart, a holder 
 * object of this class will be created to hold order information of that line.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CartItemHolder extends JPanel {

    private Cart curCart;
    private CartItem orderLine;
    private ContainerController conCtrl;

    private JButton btnDelete;
    private JButton btnMinus;
    private JButton btnPlus;
    private JLabel lblAmount;
    private JLabel lblName;
    private JLabel lblPrice;
    private JPanel items;

    public CartItemHolder(CartItem orderLine, ContainerController conCtrl, Cart cart, JPanel items) {
        super(null);

        this.orderLine = orderLine;
        this.conCtrl = conCtrl;
        this.curCart = cart;
        this.items = items;
        
        //Delete a line from current cart.
        btnDelete = new JButton();
        btnDelete.setBounds(252, 5, 90, 35);
        btnDelete.setBackground(new Color(214, 217, 223));
        btnDelete.setForeground(new Color(0, 0, 0));
        btnDelete.setEnabled(true);
        btnDelete.setFont(new Font("sansserif", 0, 12));
        btnDelete.setText("Delete");
        btnDelete.setVisible(true);
        btnDelete.addActionListener(e -> {
            int amount = this.orderLine.getAmount();
            int quantity = this.orderLine.getCarpart().getQuantity();
            this.orderLine.getCarpart().setQuantity(amount + quantity);
            
            this.orderLine.setAmount(0);
            this.orderLine.setCarpart(null);
            
            this.items.remove(this);
            curCart.getItems().remove(this.orderLine);
            this.items.revalidate();
            this.items.repaint();
        });

        btnMinus = new JButton();
        btnMinus.setBounds(130, 6, 38, 34);
        btnMinus.setBackground(new Color(214, 217, 223));
        btnMinus.setForeground(new Color(0, 0, 0));
        btnMinus.setEnabled(true);
        btnMinus.setFont(new Font("sansserif", 0, 12));
        btnMinus.setText("-");
        btnMinus.setVisible(true);
        btnMinus.addActionListener(e -> {
            int amount = this.orderLine.getAmount();
            if(amount == 1){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "Amount must between 1 and 99!");
                return;
            }else{
                amount -= 1;
                lblAmount.setText("" + amount);
                this.orderLine.setAmount(amount);
                this.orderLine.getCarpart().setQuantity(this.orderLine.getCarpart().getQuantity() + 1);
            }
            
        });

        btnPlus = new JButton();
        btnPlus.setBounds(205, 7, 45, 33);
        btnPlus.setBackground(new Color(214, 217, 223));
        btnPlus.setForeground(new Color(0, 0, 0));
        btnPlus.setEnabled(true);
        btnPlus.setFont(new Font("sansserif", 0, 12));
        btnPlus.setText("+");
        btnPlus.setVisible(true);
        btnPlus.addActionListener(e -> {
            int amount = this.orderLine.getAmount();
            
            if(amount == 99){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "Amount must between 1 and 99!");
                return;
            }else if(this.orderLine.getCarpart().getQuantity() == 0){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "No more in storage!");
                return;
            }else{
                amount += 1;
                lblAmount.setText("" + amount);
                this.orderLine.setAmount(amount);
                this.orderLine.getCarpart().setQuantity(this.orderLine.getCarpart().getQuantity() - 1);
            }
        });

        lblAmount = new JLabel();
        lblAmount.setBounds(170, 9, 35, 30);
        lblAmount.setBackground(new Color(214, 217, 223));
        lblAmount.setForeground(new Color(0, 0, 0));
        lblAmount.setEnabled(true);
        lblAmount.setFont(new Font("sansserif", 0, 12));
        lblAmount.setText("" + this.orderLine.getAmount());
        lblAmount.setVisible(true);

        lblName = new JLabel();
        lblName.setBounds(10, 7, 114, 16);
        lblName.setBackground(new Color(214, 217, 223));
        lblName.setForeground(new Color(0, 0, 0));
        lblName.setEnabled(true);
        lblName.setFont(new Font("sansserif", 0, 12));
        lblName.setText(this.orderLine.getCarpart().getItemName());
        lblName.setVisible(true);

        lblPrice = new JLabel();
        lblPrice.setBounds(10, 25, 111, 18);
        lblPrice.setBackground(new Color(214, 217, 223));
        lblPrice.setForeground(new Color(0, 0, 0));
        lblPrice.setEnabled(true);
        lblPrice.setFont(new Font("sansserif", 0, 12));
        DecimalFormat dFormat = new DecimalFormat("#.00");
        lblPrice.setText("$" + dFormat.format(this.orderLine.getCarpart().getItemPrice()));
        lblPrice.setVisible(true);

        setBorder(BorderFactory.createEtchedBorder(1));
        setBackground(new Color(214, 217, 223));
        setForeground(new Color(0, 0, 0));
        setEnabled(true);
        setFont(new Font("sansserif", 0, 12));
        setVisible(true);

        //adding components to contentPane panel
        add(btnDelete);
        add(btnMinus);
        add(btnPlus);
        add(lblAmount);
        add(lblName);
        add(lblPrice);
    }
    
    /**
     * Indicate the preferred size of the component.
     */
    @Override 
    public Dimension getPreferredSize(){
        return new Dimension(400, 60);
    }
    
}
