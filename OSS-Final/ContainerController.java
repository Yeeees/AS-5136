import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This controller is used to make any manipulation to the data in the container.
 */
public class ContainerController {
    private Container container = new Container();
    
    /**
     * Default constructor, load all necessary data from text files.
     */
    public ContainerController(){
        loadUserData();
        loadCarpartData();
        loadShopOrderData();
        loadVehicleData();
    }
    
    /**
     * Load Users' data.
     */
    public void loadUserData(){
        String userFile = "userList.txt";
        ArrayList<User> userList = new ArrayList<User>();
        String[] userField;
        try
        {
        FileReader inputFile = new FileReader(userFile);
        Scanner parser = new Scanner(inputFile);
        while(parser.hasNextLine())
        {
            String line = parser.nextLine();
            userField = line.split(",");
            
            switch(userField[0]){
                case "Customer": Customer customer = new Customer(userField[1],userField[2],userField[3],userField[4],userField[5],CustType.Customer,userField[6],Boolean.valueOf(userField[7]));
                                 userList.add(customer);
                                 break;
                case "Mechanic": Mechanic mechanic = new Mechanic(userField[1],userField[2],userField[3],userField[4],userField[5],CustType.Mechanic);
                                 userList.add(mechanic);
                                 break;
                case "Admin": Administrator administrator = new Administrator(userField[1],userField[2],userField[3],userField[4],userField[5],CustType.Admin);
                                        userList.add(administrator);
                                        break;
            }
        }
        inputFile.close();
        container.setUserList(userList);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(userFile + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    /**
     * Load vehicles's data.
     */
    public void loadVehicleData(){
        String vehicleFile = "vehicle.txt";
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        String[] vehicleField;
        try{
            FileReader inputFile = new FileReader(vehicleFile);
            Scanner parser = new Scanner(inputFile);
            while(parser.hasNextLine()){
                String line = parser.nextLine();
                vehicleField = line.split(",");
                DecimalFormat formatter = new DecimalFormat("#.00");
                Double itemPrice = Double.parseDouble(vehicleField[2]);
                Vehicle vehicle = new Vehicle(vehicleField[0],vehicleField[1],itemPrice,vehicleField[3]);
                vehicleList.add(vehicle);
            }
            inputFile.close();
            container.setVehicleList(vehicleList);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(vehicleFile + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Load car parts's data.
     */
    public void loadCarpartData(){
        String carpartFile = "carPart.txt";
        ArrayList<Carpart> carpartList = new ArrayList<Carpart>();
        String[] carpartField;
        try
        {
        FileReader inputFile = new FileReader(carpartFile);
        Scanner parser = new Scanner(inputFile);
        while(parser.hasNextLine())
        {
            String line = parser.nextLine();
            carpartField = line.split(",");
            
            Carpart carpart = new Carpart();
            carpart.setItemId(carpartField[1]);
            carpart.setItemName(carpartField[2]);
            DecimalFormat formatter = new DecimalFormat("#.00");
            Double itemPrice = Double.parseDouble(carpartField[3]);
            formatter.format(itemPrice);
            carpart.setItemPrice(itemPrice);
            carpart.setItemDesc(carpartField[4]);
            carpart.setQuantity(Integer.parseInt(carpartField[5]));
            
            switch(carpartField[0]){
                case "Tyre": carpart.setCategory(CarpartType.Tyre);
                                    break;
                case "SparkingPlug": carpart.setCategory(CarpartType.SparkingPlug);
                                    break;
                case "Bumper": carpart.setCategory(CarpartType.Bumper);
                                    break;
                case "Headlights": carpart.setCategory(CarpartType.Headlights);
                                    break;
                                }
            carpartList.add(carpart);
        }
        inputFile.close();
        container.setCarpartList(carpartList);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(carpartFile + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Load shopping orders' data
     */
    public void loadShopOrderData(){
        String orderFile = "shopOrder.txt";
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            FileReader inputFile = new FileReader(orderFile);
            Scanner parser = new Scanner(inputFile);
            while(parser.hasNextLine())
            {
                
                String line = parser.nextLine();
                String[] cusField;
                cusField = line.split(",");
                
                
                Customer customer = findCustomer(cusField[0]);
                //cart.setCustomer(customer);
                Date date = df.parse(cusField[1]);
                //cart.setMadeDate(date);
                
                Cart cart = new Cart(customer,date);
                String[] cartField;
                cartField = cusField[2].split(";");
                for(int i=0;i < cartField.length;i++)
                {
                    String[] itemField = cartField[i].split("=");
                    CartItem item = new CartItem(findCarpart(itemField[0]),Integer.parseInt(itemField[1]));
                    cart.addItems(item);        
                }
            
                cartList.add(cart);
            }
            container.setShopOrderList(cartList);
        
            inputFile.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(orderFile + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Return a customer based on the customer's username, return null is does not exist.
     */
    public Customer findCustomer(String cusName){
        Customer one = new Customer();
        for(int i=0;i<container.getUserList().size();i++)
        {
            if(container.getUserList().get(i).getName().equals(cusName) && container.getUserList().get(i).getType().equals(CustType.Customer))
            {   
                one = (Customer)container.getUserList().get(i);
            }
         }    
        return one;
    }
    
    /**
     * Return a car part based on the name.
     */
    public Carpart findCarpart(String itemName){
        Carpart one = new Carpart();
        for(Carpart temp : container.getCarpartList())
        {
            if(temp.getItemName().equals(itemName))
                one = temp;
        }
        return one;
    }
    
    /**
     * Return all car parts.
     */
    public ArrayList<Carpart> getAllCarpart(){
        return container.getCarpartList();
    }
    
    /**
     * Return all vehicles.
     */
    public ArrayList<Vehicle> getAllVehicle(){
        return container.getVehicleList();
    }
    
    /**
     * Save the users's data to the text file.
     */
    public void saveUserData(){
        try
        {
            PrintWriter outputFile = new PrintWriter("test.txt");
            for(User index : this.container.getUserList())
            {
                outputFile.print(index.getType());
                outputFile.print(",");
                outputFile.print(index.getId());
                outputFile.print(",");
                outputFile.print(index.getName());
                outputFile.print(",");
                outputFile.print(index.getPassword());
                outputFile.print(",");
                outputFile.print(index.getPhone());
                outputFile.print(",");
                outputFile.print(index.getEmail());
                outputFile.print(",");
                if(index.getType().equals(CustType.Customer))
                {
                    outputFile.print(((Customer)index).getAddress());
                    outputFile.print(",");
                    outputFile.println(((Customer)index).isIsPremium());
                }
                else
                {
                    outputFile.println();
                }
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Save the car parts's data to the text file.
     */
    public void saveCarpartData(){
        try
        {
            PrintWriter outputFile = new PrintWriter("test.txt");
            for(Carpart index : this.container.getCarpartList())
            {
                outputFile.print(index.getCategory());
                outputFile.print(",");
                outputFile.print(index.getItemId());
                outputFile.print(",");
                outputFile.print(index.getItemName());
                outputFile.print(",");
                outputFile.print(index.getItemPrice());
                outputFile.print(",");
                outputFile.print(index.getItemDesc());
                outputFile.print(",");
                outputFile.println(index.getQuantity());
                
                
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Save the shopping orders's data to the text file.
     */
    public void saveOrderData(){
        try
        {
            PrintWriter outputFile = new PrintWriter("test.txt");
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            for(Cart index : this.container.getShopOrderList())
            {
                outputFile.print(index.getCustomer().getName());
                outputFile.print(",");
                String madeDate = df.format(index.getMadeDate());
                outputFile.print(madeDate);
                outputFile.print(",");
                
                
                for(int i = 0;i < index.getItems().size();i++)
                {
                    if(i == 0)
                    {
                        outputFile.print(index.getItems().get(i).getCarpart().getItemName());
                        outputFile.print("=");
                        outputFile.print(index.getItems().get(i).getAmount());
                    }
                    if(i == index.getItems().size()-1)
                    {
                        outputFile.print(";");
                        outputFile.print(index.getItems().get(i).getCarpart().getItemName());
                        outputFile.print("=");
                        outputFile.println(index.getItems().get(i).getAmount());
                    }
                    if(i != 0 && i != index.getItems().size()-1)
                    {
                        outputFile.print(";");
                        outputFile.print(index.getItems().get(i).getCarpart().getItemName());
                        outputFile.print("=");
                        outputFile.print(index.getItems().get(i).getAmount());
                    }
                }
                
                
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }    

    /**
     * Get a user based one the user name, return null if no such a user name.
     */
    public User getUser(String username){
        User user = null;
        for(User temp : this.container.getUserList())
        {
            if(temp.getName().equals(username))
                user = temp;
        }
        return user;
    }
    
    /**
     * Add the cart to the  shopping order list after user confirm checkout.
     */
    public void addCart(Cart cart){
        this.container.addCart(cart);
    }
}
