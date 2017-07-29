import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
/**
 * This class is used to hold and display information for specific car part.
 * Generally, each car part in the car part list will be put in a holder object of this class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProductHolder extends JPanel
{
    private Carpart carpartInfo;
    private Cart cart;
    private ContainerController conCtrl;
    
    private JButton btnPlus;
    private JButton btnToCart;
    private JButton btnMinus;
    private JLabel lblAmount;
    private JLabel lblDesc;
    private JLabel lblName;
    private JLabel lblPrice;
    private JLabel lblQuan;
    private JLabel lblPic;
    
    public ProductHolder(Carpart info, Cart cart,ContainerController conCtrl){
        super(null);
        this.carpartInfo = info;
        this.conCtrl = conCtrl;
        this.cart = cart;
        
        btnMinus = new JButton();
        btnMinus.setBounds(339,7,40,30);
        btnMinus.setBackground(new Color(214,217,223));
        btnMinus.setForeground(new Color(0,0,0));
        btnMinus.setEnabled(true);
        btnMinus.setFont(new Font("sansserif",0,12));
        btnMinus.setText("-");
        btnMinus.setVisible(true);
        btnMinus.addActionListener(e -> {
            int amount = Integer.parseInt(lblAmount.getText());
            if(amount == 0){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "Amount must between 0 and 99!");
                return;
            }else{
                amount -= 1;
                lblAmount.setText("" + amount);
                carpartInfo.setQuantity(carpartInfo.getQuantity() + 1);
                lblQuan.setText("" + carpartInfo.getQuantity());
            }
        });

        btnToCart = new JButton();
        btnToCart.setBounds(361,40,98,35);
        btnToCart.setBackground(new Color(214,217,223));
        btnToCart.setForeground(new Color(0,0,0));
        btnToCart.setEnabled(true);
        btnToCart.setFont(new Font("sansserif",0,12));
        btnToCart.setText("Add to Cart");
        btnToCart.setVisible(true);
        btnToCart.addActionListener(e -> {
            int amount = Integer.parseInt(lblAmount.getText());
            if(amount == 0){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "No purchase with 0 amount!");
                return;
            }else{
                cart.addItems(new CartItem(carpartInfo,amount));
                lblAmount.setText("0");
            }
        });

        btnPlus = new JButton();
        btnPlus.setBounds(416,7,41,28);
        btnPlus.setBackground(new Color(214,217,223));
        btnPlus.setForeground(new Color(0,0,0));
        btnPlus.setEnabled(true);
        btnPlus.setFont(new Font("sansserif",0,12));
        btnPlus.setText("+");
        btnPlus.setVisible(true);
        btnPlus.addActionListener(e -> {
            int amount = Integer.parseInt(lblAmount.getText());
            if(amount == 99){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "Amount must between 0 and 99!");
                return;
            }else if(carpartInfo.getQuantity() == 0){
                JOptionPane errorMsg = new JOptionPane();
                errorMsg.showMessageDialog(this, "No more in storage!");
                return;
            }else{
                amount += 1;
                
                lblAmount.setText("" + amount);
                carpartInfo.setQuantity(carpartInfo.getQuantity() - 1);
                lblQuan.setText("" + carpartInfo.getQuantity());
            }
        });

        lblAmount = new JLabel();
        lblAmount.setBounds(384,11,31,21);
        lblAmount.setBackground(new Color(214,217,223));
        lblAmount.setForeground(new Color(0,0,0));
        lblAmount.setEnabled(true);
        lblAmount.setFont(new Font("sansserif",0,12));
        lblAmount.setText("0");
        lblAmount.setVisible(true);

        lblDesc = new JLabel();
        lblDesc.setBounds(186,8,145,56);
        lblDesc.setBackground(new Color(214,217,223));
        lblDesc.setForeground(new Color(0,0,0));
        lblDesc.setEnabled(true);
        lblDesc.setFont(new Font("sansserif",0,12));
        lblDesc.setText(carpartInfo.getItemDesc());
        lblDesc.setVisible(true);

        lblName = new JLabel();
        lblName.setBounds(9,9,76,17);
        lblName.setBackground(new Color(214,217,223));
        lblName.setForeground(new Color(0,0,0));
        lblName.setEnabled(true);
        lblName.setFont(new Font("sansserif",0,12));
        lblName.setText(carpartInfo.getItemName());
        lblName.setVisible(true);

        lblPrice = new JLabel();
        lblPrice.setBounds(9,40,63,17);
        lblPrice.setBackground(new Color(214,217,223));
        lblPrice.setForeground(new Color(0,0,0));
        lblPrice.setEnabled(true);
        lblPrice.setFont(new Font("sansserif",0,12));
        DecimalFormat dFormat = new DecimalFormat("#.00");
        lblPrice.setText("$" + dFormat.format(carpartInfo.getItemPrice()));
        lblPrice.setVisible(true);

        lblQuan = new JLabel();
        lblQuan.setBounds(88,9,88,19);
        lblQuan.setBackground(new Color(214,217,223));
        lblQuan.setForeground(new Color(0,0,0));
        lblQuan.setEnabled(true);
        lblQuan.setFont(new Font("sansserif",0,12));
        lblQuan.setText("" + carpartInfo.getQuantity());
        lblQuan.setVisible(true);
        
        ImageIcon image = new ImageIcon(carpartInfo.getItemName() + ".jpg");
        lblPic = new JLabel(image);
        lblPic.setBounds(3,82,400,297);
        lblPic.setBackground(new Color(214,217,223));
        lblPic.setForeground(new Color(0,0,0));
        lblPic.setEnabled(true);
        lblPic.setFont(new Font("sansserif",0,12));
        lblPic.setVisible(true);

        
        setBorder(BorderFactory.createEtchedBorder(1));
        setSize(475,380);
        setBackground(new Color(214,217,223));
        setForeground(new Color(0,0,0));
        setEnabled(true);
        setFont(new Font("sansserif",0,12));
        setVisible(true);
        
        //Add components
        add(btnPlus);
        add(btnToCart);
        add(btnMinus);
        add(lblAmount);
        add(lblDesc);
        add(lblName);
        add(lblPrice);
        add(lblQuan);
        add(lblPic);
        
    }
    
    /**
     * Indicate the preferred size of the component.
     */
    @Override 
    public Dimension getPreferredSize(){
        return new Dimension(478,380);
    }
    
    /**
     * refresh the quantity displayed based on the actual value.
     */
    public void refresh(){
        lblQuan.setText("" + carpartInfo.getQuantity());
    }
}
