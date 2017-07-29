import java.util.ArrayList;

/**
 * Container plays a role as a data holder in the system.
 * it is where all users, orders and items are kept during the system runtime.
 */
public class Container {
	ArrayList<User> userList;
	ArrayList<Carpart> carpartList;
	ArrayList<Cart> shopOrderList;
	ArrayList<Vehicle> vehicleList;
	
	public Container(){
		
	}
	
	public Container(ArrayList<User> users,ArrayList<Carpart> caparts,ArrayList<Cart> orders,ArrayList<Vehicle> vehicles){
		this.userList = users;
		this.carpartList = caparts;
		this.shopOrderList = orders;
		this.vehicleList = vehicles;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<Carpart> getCarpartList() {
		return carpartList;
	}

	public void setCarpartList(ArrayList<Carpart> carpartList) {
		this.carpartList = carpartList;
	}

	public ArrayList<Cart> getShopOrderList() {
		return shopOrderList;
	}

	public void setShopOrderList(ArrayList<Cart> shopOrderList) {
		this.shopOrderList = shopOrderList;
	}
	
	public void addCart(Cart cart){
		this.shopOrderList.add(cart);
	}
	
	public ArrayList<Vehicle> getVehicleList(){
		return this.vehicleList;
	}
	
	public void setVehicleList(ArrayList<Vehicle> vehicleList){
		this.vehicleList = vehicleList;
	}
	
}
