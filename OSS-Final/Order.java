
import java.util.Date;

/**
 * This class is used to record user purchase information.
 * 
 * @author jiaji
 */
public class Order {
    
    private Customer customer;
    private Date madeDate;

    public Order() {
    }

    public Order(Customer customer, Date madeDate) {
        this.customer = customer;
        this.madeDate = madeDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getMadeDate() {
        return madeDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setMadeDate(Date madeDate) {
        this.madeDate = madeDate;
    }
    
}
