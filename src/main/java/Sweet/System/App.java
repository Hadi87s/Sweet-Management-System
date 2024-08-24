package Sweet.System;
import java.util.Scanner;
import java.util.Random;

public class App {
    static SweetSystem app;
    static User user;
    static boolean loggedIn = true;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_WHITE = "\u001B[97m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";



    public static void PersonalInformation(User obj) {
        while (true)
        {
            System.out.println(ANSI_WHITE + ANSI_BOLD + "\nChoose what you want to change:\n" + ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("3. Email");
            System.out.println("4. City");
            System.out.println("5. Exit");
            int insideOption = scanner.nextInt();

            if (insideOption == 1){
                System.out.printf(ANSI_WHITE + "New Username: " + ANSI_RESET);
                String username = scanner.next();
                obj.setUsername(username); //changing the username to the new one.
                System.out.println(ANSI_BRIGHT_YELLOW + "Username Has Been Changed." + ANSI_RESET);
            }
            else if(insideOption == 2){
                System.out.printf(ANSI_WHITE + "New Password: "+ ANSI_RESET);
                String password = scanner.next();
                System.out.println();
                System.out.printf(ANSI_WHITE + "New Password Again: "+ ANSI_RESET);
                String duplicatePassword = scanner.next();
                System.out.println();
                if (duplicatePassword.equals(password)){
                    obj.setPassword(password);
                    System.out.println(ANSI_BRIGHT_YELLOW + "Password Has Been Changed." + ANSI_RESET);
                }
                else System.out.println(ANSI_RED + "Password Does Not Match." + ANSI_RESET);
            }
            else if(insideOption == 3){
                System.out.printf(ANSI_WHITE + "New Email: " + ANSI_RESET);
                String email = scanner.next();
                obj.setEmail(email);
                System.out.println();
                System.out.println(ANSI_BRIGHT_YELLOW + "Email Has Been Changed." + ANSI_RESET);
            }
            else if(insideOption == 4){
                System.out.printf(ANSI_WHITE + "New City: " + ANSI_RESET);
                String City = scanner.next();
                obj.setCity(City);
                System.out.println();
                System.out.println(ANSI_BRIGHT_YELLOW + "City Has Been Changed." + ANSI_RESET);
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
        System.out.println();

        user = app.isUserRegister(username, password);
        return user != null;
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
            System.out.println();
            if (app.isUserRegistered(username, password))
             return app.registerUser( username, password, email, city);
            else return false;
    }
    public static void storeOwnerMenu(){
        while(true){
            String multiLineString =
                    ANSI_BRIGHT_BLUE
                            + "1. show account information\n"
                            + "2. get products\n"
                            + "3. total profit\n"
                            + "4. get most selling item\n"
                            + "5. get best selling product\n"
                            + "6. Add product\n"
                            + "7. remove product\n"
                            + "8. update product\n"
                            + "0. Logout\n"
                            + ANSI_RESET;

            System.out.println();
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
                System.out.println(ANSI_WHITE + "Product Name:" + ANSI_RESET);
                String productName = scanner.next();
                System.out.println(ANSI_WHITE + "Description:" + ANSI_RESET);
                String description = scanner.next();
                System.out.println(ANSI_WHITE + "Price:" + ANSI_RESET);
                double price = scanner.nextDouble();
                System.out.println(ANSI_WHITE + "Raw Material Price:" + ANSI_RESET);
                double rawMaterialPrice = scanner.nextDouble();
                obj.addProduct(productName,description,price,rawMaterialPrice);
                System.out.println(ANSI_WHITE + "Product Has Been Added." + ANSI_RESET);
            }
            else if(options == 7){
                System.out.println(ANSI_WHITE + "Product Name :" + ANSI_RESET);
                String productName = scanner.next();
                obj.deleteProductFromFile("Products.txt",productName);

            }
            else if(options == 8){
                System.out.println(ANSI_WHITE + "Product Name(You want to update) :" + ANSI_RESET);
                String productName = scanner.next();
                System.out.println(ANSI_WHITE + "Description :" + ANSI_RESET);
                String description = scanner.next();
                System.out.println(ANSI_WHITE + "Price:" + ANSI_RESET);
                double price = scanner.nextDouble();
                System.out.println(ANSI_WHITE + "Raw Material Price:" + ANSI_RESET);
                double rawMaterialPrice = scanner.nextDouble();
                Product newProduct = new Product(productName,description,price,rawMaterialPrice);
                obj.updateProductInFile("Products.txt",productName,newProduct);
            }
            else if (options == 0){
                loggedIn = false;
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
                            + "11. Add Feedback on a Product\n"
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
                    System.out.println(ANSI_RED + "Your Shopping cart is empty.\n" + ANSI_RESET);
                }
                else {
                    for(Order o : obj.getOrderList()){
                        System.out.print(o);
                        System.out.println(ANSI_WHITE + "      Status:          "+o.getOrderStatus()+"\n" + ANSI_RESET);
                    }
                }
            }
            else if(options == 3){
                System.out.println(ANSI_WHITE +"The order id :"+ ANSI_RESET);
                String id = scanner.next();

                for(Order o : obj.getOrderList()){
                    if(id.equals(o.getOrderID())){
                        System.out.print(o);
                        System.out.println(ANSI_WHITE +"      Status:          "+o.getOrderStatus()+"\n"+ ANSI_RESET);
                    }
                }
            }
            else if(options == 4){

                StoreOwner.printAllProducts();

                System.out.print(ANSI_WHITE + "Enter Product name: "+ ANSI_RESET);
                String ProductName = scanner.next();

                if(StoreOwner.isProductAvailable(ProductName)) {

                    double price=StoreOwner.getProductPrice(ProductName);

                    int randomNumber = generateRandomNumber();
                    String orderID = Integer.toString(randomNumber);

                    // Input for Quantity
                    System.out.print(ANSI_WHITE + "Enter Quantity: "+ ANSI_RESET);
                    int Quantity = scanner.nextInt();

                    Order o = new Order(ProductName, Quantity, orderID);
                    obj.addOrder(o);

                    System.out.println(ANSI_BRIGHT_YELLOW + "\n\nYour Order has been added."+ ANSI_RESET);
                    System.out.println(ANSI_WHITE + "the details of your order is: " +
                            "\norder number: "+orderID+
                            "\n"+ProductName+"  Quantity: "+Quantity+
                            "\nprice: "+price*Quantity+"\n\n" + ANSI_RESET);
                }
                else {
                    System.out.println(ANSI_RED +"Something wrong try again"+ ANSI_RESET);
                }
            }
            else if(options == 5){
                System.out.print(ANSI_WHITE + "Enter Order ID: "+ ANSI_RESET);
                String orderID = scanner.next();

                obj.cancelOrder(orderID);
                System.out.println(ANSI_BRIGHT_YELLOW + "Order cancelled successfully."+ ANSI_RESET);
            }
            else if(options == 6){

                // Input for Title
                System.out.print(ANSI_WHITE +"Enter Title: "+ ANSI_RESET);

                String title = scanner.next();

                // Input for description
                System.out.print(ANSI_WHITE +"Enter Description: "+ ANSI_RESET);
                scanner.nextLine();//to let me enter description
                String description = scanner.nextLine();

                Post desert = new Post(title,description);
                app.addPost(desert);

                if(app.isPostAdded()){
                    System.out.println(ANSI_BRIGHT_YELLOW +"Post add successfully."+ ANSI_RESET);
                }
                else{
                    System.out.println(ANSI_RED + "Error occurred while adding post."+ ANSI_RESET);
                }

            }
            else if(options == 7){

                for(Post po : app.getPosts()){
                    System.out.println(po.toString());
                }
            }
            else if(options == 8){
                // Input for Title
                System.out.print(ANSI_WHITE + "Enter Title: "+ ANSI_RESET);
                String title = scanner.next();
                Post p = null;
                for(Post po : app.getPosts()){
                    if(title.equals(po.getTitle())){
                        p = po;
                    }
                }

                if(p!=null){
                    app.deletePost( p);
                    System.out.println(ANSI_BRIGHT_YELLOW +"Post deleted successfully.\n"+ ANSI_RESET);
                }
                else System.out.println(ANSI_RED + "Post not found.\n"+ ANSI_RESET);

            }
            else if (options == 9){
                System.out.println(ANSI_WHITE +"Write your request below:"+ ANSI_RESET);
                String request = scanner.nextLine();
                request = scanner.nextLine();

                app.printStoreOwners();

                System.out.println(ANSI_WHITE +"Write the StoreOwner's Name to make a establish connection:"+ ANSI_RESET);
                String ownerName = scanner.next();

                StoreOwner owner = app.getStoreOwnerByBusinessName(ownerName);

                app.enableEmailNotifications();
                app.makeSpecialRequest(obj,owner,request);

            }
            else if(options == 10){
                System.out.println(obj.viewAccountDetails());
                PersonalInformation(obj);

            }
            else if (options == 11){
                StoreOwner.printAllProducts();
                System.out.println();
                System.out.printf(ANSI_WHITE + "Specify which product to give Feedback on: " + ANSI_RESET);
                String productName = scanner.next();
                System.out.printf(ANSI_WHITE + "Feedback message: " + ANSI_RESET);
                String feedback = scanner.nextLine();
                feedback = scanner.nextLine();
                System.out.println();
                Feedback fb = new Feedback(productName, feedback);
                app.addUserFeedback(fb);
                System.out.println(ANSI_BRIGHT_YELLOW + "Feedback added successfully."+ ANSI_RESET + "\n");
            }
            else if(options == 0) {
                loggedIn = false;
                break;
            }
        }

    }
    public static void AdminMenu(){
        while(true) {
            String multiLineString =
                    ANSI_BRIGHT_BLUE
                            + "1. show account information\n"
                            + "2. Add a Store Owner\n"
                            + "3. Add a Raw Material Supplier\n"
                            + "4. Add a new Admin\n"
                            + "0. Logout\n"
                            + ANSI_RESET;

            System.out.println();
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
                RawSupplier supplier = new RawSupplier(Username, Password, Email);
                supplier.setAddress(Address);
                supplier.setBusinessName(BusinessName);
                if(app.addSupplierToFile("Suppliers.txt",supplier)){
                    System.out.println(ANSI_BRIGHT_YELLOW +"A Supplier has been added successfully." + ANSI_RESET);
                }
                else
                    System.out.println(ANSI_RED + "Error occurred while trying to add a Supplier" + ANSI_RESET);
            }
            else if (options == 4){
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's desired Username: " + ANSI_RESET);
                String Username = scanner.next();
                System.out.println(ANSI_BRIGHT_BLUE +"Enter Owner's desired Password: " + ANSI_RESET);
                String Password = scanner.next();
                Admin admin = new Admin(Username, Password);
                if(app.addAdminToFile("Admin.txt",admin)){
                    System.out.println(ANSI_BRIGHT_YELLOW +"An Admin has been added successfully." + ANSI_RESET);
                }
                else
                    System.out.println(ANSI_RED + "Error occurred while trying to add an Admin." + ANSI_RESET);
            }
            else if (options == 0){
                loggedIn = false;
                break;
            }
        }
    }
    public static void SupplierMenu(){
        while(true) {
            String multiLineString =
                    ANSI_BRIGHT_BLUE
                            + "1. show account information\n"
                            + "2. Manage account information\n"
                            + "0. Logout\n"
                            + ANSI_RESET;

            System.out.println();
            System.out.println(multiLineString);
            Scanner scanner = new Scanner(System.in);
            int options = scanner.nextInt();

            RawSupplier obj = app.getSupplierByUsername(user.getUsername());
            if(options == 1){
                System.out.println(obj.viewAccountDetails());
            }
            else if (options == 2){
                System.out.println(obj.viewAccountDetails());
                PersonalInformation(obj);
            }
        }
    }


    public static void main(String[] args) {
        try {
            app = new SweetSystem();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_BRIGHT_BLUE + "1.Login\n2.Signup" + ANSI_RESET);
            int options = scanner.nextInt();
            if (options == 1) {
                if (LoginSetup()) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "Invalid username or password, please try again." + ANSI_RESET);
                }
            } else if (options == 2) {
                if (SignUpSetup()) {
                    System.out.println(ANSI_BRIGHT_YELLOW +"User has been SignedUp successfully."+ ANSI_RESET);

                } else System.out.println(ANSI_RED +"Username is already in use, or the password is too weak." + ANSI_RESET);
            }
        }
            while (loggedIn) {

                if (user.getRole() == 'A' || user.getRole() == 'a') {
                    System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Administration Unit ------" + ANSI_RESET + "\n");
                    AdminMenu();
                } else if (user.getRole() == 'S' || user.getRole() == 's') {
                    System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Store Management Unit ------" + ANSI_RESET + "\n");
                    storeOwnerMenu();
                } else if (user.getRole() == 'R' || user.getRole() == 'r') {
                    System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Raw Material Management Unit ------" + ANSI_RESET + "\n");
                    SupplierMenu();
                } else if (user.getRole() == 'U' || user.getRole() == 'u') {
                    System.out.println(ANSI_BOLD + ANSI_WHITE + "------ Welcome to the Sweet System ------" + ANSI_RESET + "\n");
                    UserMainMenu();
                } else {
                    System.out.println(ANSI_BOLD + ANSI_RED + "SOMETHING WEN WRONG WHEN ATTEMPTING TO LOGIN..." + ANSI_RESET + "\n");
                }
            }
        }


    public static int generateRandomNumber() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Ensures the number is between 1000 and 9999
    }






}