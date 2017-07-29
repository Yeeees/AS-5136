import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
/**
 * This class is used to hold and display information for specific vehicle.
 * Generally, each vehicle in the vehicle list will be put in a holder object of this class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VehicleHolder extends JPanel
{
    private Vehicle vehicleInfo;
    
    private JLabel lblDesc;
    private JLabel lblName;
    private JLabel lblPrice;
    private JLabel lblPic;
    
    public VehicleHolder(Vehicle info){
        super(null);
        this.vehicleInfo = info;
        
        lblDesc = new JLabel();
        lblDesc.setBounds(186,8,145,56);
        lblDesc.setBackground(new Color(214,217,223));
        lblDesc.setForeground(new Color(0,0,0));
        lblDesc.setEnabled(true);
        lblDesc.setFont(new Font("sansserif",0,12));
        lblDesc.setText(vehicleInfo.getItemDesc());
        lblDesc.setVisible(true);
        
        lblName = new JLabel();
        lblName.setBounds(9,9,76,17);
        lblName.setBackground(new Color(214,217,223));
        lblName.setForeground(new Color(0,0,0));
        lblName.setEnabled(true);
        lblName.setFont(new Font("sansserif",0,12));
        lblName.setText(vehicleInfo.getItemName());
        lblName.setVisible(true);
        
        lblPrice = new JLabel();
        lblPrice.setBounds(9,40,63,17);
        lblPrice.setBackground(new Color(214,217,223));
        lblPrice.setForeground(new Color(0,0,0));
        lblPrice.setEnabled(true);
        lblPrice.setFont(new Font("sansserif",0,12));
        DecimalFormat dFormat = new DecimalFormat("#.00");
        lblPrice.setText("$" + dFormat.format(vehicleInfo.getItemPrice()));
        lblPrice.setVisible(true);
        
        ImageIcon image = new ImageIcon(vehicleInfo.getItemName() + ".jpg");
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
        
        
        add(lblDesc);
        add(lblName);
        add(lblPrice);
        add(lblPic);
    }
    
    /**
     * Indicate the preferred size of the component.
     */
    @Override 
    public Dimension getPreferredSize(){
        return new Dimension(478,380);
    }
}
