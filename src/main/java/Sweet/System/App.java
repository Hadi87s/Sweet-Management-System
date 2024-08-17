package Sweet.System;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class App {
    static SweetSystem app;
    static User user;
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_WHITE = "\u001B[97m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BOLD = "\u001B[1m";

    public static void UserPersonalInformation(User obj) {
        while (true)
        {
            System.out.println("Choose what you want to change:");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("3. Email");
            System.out.println("4. City");
            System.out.println("5. Exit");
            int insideOption = scanner.nextInt();

            if (insideOption == 1){
                System.out.printf("New Username: ");
                String username = scanner.next();
                obj.setUsername(username); //changing the username to the new one.
                System.out.println("Username Has Been Changed.");
            }
            else if(insideOption == 2){
                System.out.printf("New Password: ");
                String password = scanner.next();
                System.out.println("");
                System.out.printf("New Password Again: ");
                String duplicatePassword = scanner.next();
                System.out.println("");
                if (duplicatePassword.equals(password)){
                    obj.setPassword(password);
                    System.out.println("Password Has Been Changed.");
                }
                else System.out.println("Password Does Not Match.");
            }
            else if(insideOption == 3){
                System.out.printf("New Email: ");
                String email = scanner.next();
                obj.setEmail(email);
                System.out.println("");
                System.out.println("Email Has Been Changed.");
            }
            else if(insideOption == 4){
                System.out.printf("New City: ");
                String City = scanner.next();
                obj.setCity(City);
                System.out.println("");
                System.out.println("City Has Been Changed.");
            }
            else if (insideOption == 5){
                break;
            }
        }
    }
    public static boolean LoginSetup(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(ANSI_BRIGHT_BLUE + "Please enter your Username: " + ANSI_RESET);
        String username = scanner.next();
        System.out.println(ANSI_BRIGHT_BLUE + "Please enter your Password: " + ANSI_RESET);
        String password = scanner.next();
        System.out.println("");

        user = app.isUserRegister(username, password);
        if(user == null)
            return false;
        else return true;
    }
    public static boolean SignUpSetup(){
            Scanner scanner = new Scanner(System.in);

            System.out.println(ANSI_BRIGHT_BLUE+ "Please enter your Username: " + ANSI_RESET);
            String username = scanner.next();
            System.out.println(ANSI_BRIGHT_BLUE + "Please enter your Password: " + ANSI_RESET);
            String password = scanner.next();
            System.out.println(ANSI_BRIGHT_BLUE + "Please enter your Email: " + ANSI_RESET);
            String email = scanner.next();
            System.out.println(ANSI_BRIGHT_BLUE + "Please enter your City: " + ANSI_RESET);
            String city = scanner.next();
            System.out.println("");
            return app.registerUser( username, password, email, city);
    }
    public static void storeOwnerMenu(){
        while(true){
            String multiLineString =
                    ANSI_BRIGHT_BLUE+
                            "1. show account information\n"
                            + "2. get products\n"
                            + "3. total profit\n"
                            + "4. get most selling item\n"
                            + "5. get best selling product\n"
                            + "6. Add product\n"
                            + "7. remove product\n"
                            + "8. update product\n"
                            + "0. Logout\n"
                            + ANSI_RESET;

            System.out.println("");
            System.out.println(multiLineString);
            Scanner scanner = new Scanner(System.in);
            int options = scanner.nextInt();

            StoreOwner obj = app.getStoreOwnerByUsername(user.getUsername());

            if(options == 1){
                System.out.println(obj.viewAccountDetails());
            }
            else if (options == 2) {
                obj.printAllProducts();
            }
            else if(options == 3){
                System.out.println(obj.calculateTotalProfit());
            }

            else if(options == 4){
                System.out.println(obj.getMostSellingItem());
            }
            else if(options == 5){
                System.out.println(obj.getBestSellingProduct());
            }
            else if(options == 6){
                System.out.println("Product Name:");
                String productName = scanner.next();
                System.out.println("Description:");
                String description = scanner.next();
                System.out.println("Price:");
                double price = scanner.nextDouble();
                System.out.println("Raw Material Price:");
                double rawMaterialPrice = scanner.nextDouble();
                obj.addProduct(productName,description,price,rawMaterialPrice);
                System.out.println("Product Has Been Added.");
            }
            else if(options == 7){
                System.out.println("Product Name :");
                String productName = scanner.next();
                obj.deleteProductFromFile("Products.txt",productName);

            }
            else if(options == 8){
                System.out.println("Product Name(You want to update) :");
                String productName = scanner.next();
                System.out.println("Description :");
                String description = scanner.next();
                System.out.println("Price:");
                double price = scanner.nextDouble();
                System.out.println("Raw Material Price:");
                double rawMaterialPrice = scanner.nextDouble();
                Product newProduct = new Product(productName,description,price,rawMaterialPrice);
                obj.updateProductInFile("Products.txt",productName,newProduct);
            }
            else if (options == 0){
                break;
            }
        }

        }
    public static void UserMainMenu(){
        while (true){
            String multiLineString =
                    ANSI_BRIGHT_BLUE
                            + "1. Show account information\n"
                            + "2. Get orders\n"
                            + "3. Get order by id\n"
                            + "4. Add order\n"
                            + "5. Cancel order\n"
                            + "6. Add dessert creation post\n"
                            + "7. Get all posts\n"
                            + "8. Delete post\n"
                            + "9. Make a special request\n"
                            + "10. Manage account information\n"
                            + "0. Logout\n"
                            + ANSI_RESET;
            System.out.println(multiLineString);
            Scanner scanner = new Scanner(System.in);
            int options = scanner.nextInt();

            User obj = app.getUserByUsername(user.getUsername());


            if(options == 1){
                System.out.println(obj.viewAccountDetails());
            }
            else if(options == 2){
                System.out.println("------ User Orders ------");
                if (obj.getOrderList().isEmpty()) {
                    System.out.println("Your Shopping cart is empty.\n");
                }
                else {
                    for(Order o : obj.getOrderList()){
                        System.out.print(o);
                        System.out.println("      Status:          "+o.getOrderStatus()+"\n");
                    }
                }
            }
            else if(options == 3){
                System.out.println("The order id :");
                String id = scanner.next();

                for(Order o : obj.getOrderList()){
                    if(id.equals(o.getOrderID())){
                        System.out.print(o);
                        System.out.println("      Status:          "+o.getOrderStatus()+"\n");
                    }
                }
            }
            else if(options == 4){

                StoreOwner.printAllProducts1();

                System.out.print("Enter Product name: ");
                String ProductName = scanner.next();

                if(StoreOwner.isProductAvailable(ProductName)) {

                    double price=StoreOwner.getProductPrice(ProductName);

                    int randomNumber = generateRandomNumber();
                    String orderID = Integer.toString(randomNumber);

                    // Input for Quantity
                    System.out.print("Enter Quantity: ");
                    int Quantity = scanner.nextInt();

                    Order o = new Order(ProductName, Quantity, orderID);
                    obj.addOrder(o);

                    System.out.println("\n\nYour Order has been added.");
                    System.out.println("the details of your order is: " +
                            "\norder number: "+orderID+
                            "\n"+ProductName+"  Quantity: "+Quantity+
                            "\nprice: "+price*Quantity+"\n\n");
                }
                else {
                    System.out.println("Something wrong try again");
                }
            }
            else if(options == 5){
                System.out.print("Enter Order ID: ");
                String orderID = scanner.next();

                obj.cancelOrder(orderID);
                System.out.println("Order cancelled successfully.");
            }
            else if(options == 6){

                // Input for Title
                System.out.print("Enter Title: ");

                String title = scanner.next();

                // Input for description
                System.out.print("Enter Description: ");
                scanner.nextLine();//to let me enter description
                String description = scanner.nextLine();

                Post desert = new Post(title,description);
                app.addPost(desert);

                if(app.isPostAdded()){
                    System.out.println("Post add successfully.");
                }
                else{
                    System.out.println("error occurred while adding post.");
                }

            }
            else if(options == 7){

                for(Post po : app.getPosts()){
                    System.out.println(po.toString());
                }
            }
            else if(options == 8){
                // Input for Title
                System.out.print("Enter Title: ");
                String title = scanner.next();
                Post p = null;
                for(Post po : app.getPosts()){
                    if(title.equals(po.getTitle())){
                        p = po;
                    }
                }

                if(p!=null){
                    app.deletePost( p);
                    System.out.println("Post deleted successfully.\n");
                }
                else System.out.println("Post not found.\n");

            }
            else if (options == 9){
                System.out.println("Write your request below:");
                String request = scanner.nextLine();
                request = scanner.nextLine();

                app.prindStoreOwners();

                System.out.println("Write the StoreOwner's Name to make a establish connection:");
                String ownerName = scanner.next();

                StoreOwner owner = app.getStoreOwnerByBusinessName(ownerName);

                app.enableEmailNotifications();
                app.makeSpecialRequest(obj,owner,request);

            }
            else if(options == 10){
                System.out.println(obj.viewAccountDetails());
                UserPersonalInformation(obj);

            }
            else if(options == 0) {
                break;
            }
        }

    }
    public static void AdminMenu(){
        while(true) {
            String multiLineString =
                    ANSI_BRIGHT_BLUE+
                            "1. show account information\n"
                            + "2. Add a Store Owner\n"
                            + "3. Add a Raw Material Supplier\n"
                            + "4. Add a new Admin\n"
                            + "5. \n"
                            + "6. \n"
                            + "7. \n"
                            + "0. Logout\n"
                            + ANSI_RESET;

            System.out.println("");
            System.out.println(multiLineString);
            Scanner scanner = new Scanner(System.in);
            int options = scanner.nextInt();

            Admin obj = app.getAdminByUsername(user.getUsername());
            if(options == 1){
                System.out.println(obj.viewAccountDetails());
            }
            else if (options == 2){
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's desired Username: " + ANSI_RESET);
                String Username = scanner.next();
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's desired Password: " + ANSI_RESET);
                String Password = scanner.next();
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's Email Address: " + ANSI_RESET);
                String Email = scanner.next();
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's Business Name: " + ANSI_RESET);
                String BusinessName = scanner.next();
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's Address: " + ANSI_RESET);
                String Address = scanner.next();

                StoreOwner owner = new StoreOwner(Username, Password, Email, Address);
                owner.setBusinessName(BusinessName);

                //Adding the storeOwner to the file
                if(app.addStoreOwnerToFile("StoreOwner.txt",owner))
                    System.out.println(ANSI_BRIGHT_YELLOW + "Store Owner added successfully." + ANSI_RESET);
            }
            else if (options == 3){

            }
            else if (options == 0){
                break;
            }
        }
    }
    public static void main(String[] args) {
        try {
            app = new SweetSystem();
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
        }



       Scanner scanner = new Scanner(System.in);
       boolean signedUp = false;
       while(true) {
           System.out.println(ANSI_BRIGHT_BLUE + "1.Login\n2.Signup" + ANSI_RESET);
           int options = scanner.nextInt();
           if (options == 1) {
               if (LoginSetup()) {
                   break;
               } else {
                   System.out.println("Invalid username or password, please try again.");
               }
           } else if (options == 2) {
               if (SignUpSetup()) {
                   System.out.println("User has been SignedUp successfully.");

               } else System.out.println("Username is already in use, or the password is too weak.");
           }


           if (user.getRole() == 'A' || user.getRole() == 'a') {
               System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Administration Unit ------" + ANSI_RESET + "\n");
               AdminMenu();
           } else if (user.getRole() == 'S' || user.getRole() == 's') {
               System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Store Management Unit ------" + ANSI_RESET + "\n");
               storeOwnerMenu();
           } else if (user.getRole() == 'R' || user.getRole() == 'r') {
               System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Raw Material Management Unit ------" + ANSI_RESET + "\n");

               scanner.next();
               //raw material supplier menu
           } else if (user.getRole() == 'U' || user.getRole() == 'u') {
               System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Sweet System ------" + ANSI_RESET + "\n");
               UserMainMenu();
           } else {
               System.out.println(ANSI_BOLD + ANSI_WHITE + "SOMETHING WEN WRONG WHEN ATTEMPTING TO LOGIN..." + ANSI_RESET + "\n");
           }
       }
    }


    public static int generateRandomNumber() {
        Random random = new Random();
        int number = 1000 + random.nextInt(9000); // Ensures the number is between 1000 and 9999
        return number;
    }






}