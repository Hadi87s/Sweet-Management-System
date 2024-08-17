package Sweet.System;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SplittableRandom;

public class StoreOwner extends User{
    private String username;
    private String password;
    private String email;
    private String Address;
    private String BusinessName;
    public static ArrayList<Product>products = new ArrayList<Product>();
    public ArrayList<String>report = new ArrayList<String>();
    private double totalProfit;
    private boolean storeOwnerExist;
    private boolean AdminRequest;
    private boolean OwnerLoggedIn;
    private boolean emailChanged;
    private boolean passwordChanged;
    private boolean businessNameChanged;
    private boolean AddressChanged;
    private ArrayList<String> messagesList= new ArrayList<>();
    public static final String ANSI_WHITE = "\u001B[97m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public StoreOwner(String businessName, String address) {
        super(null,null,'S');
        BusinessName = businessName;
        Address = address;
    }

    public StoreOwner(String username, String password, String email, String address) {
        super(username,password,'S');
        this.username = username;
        this.password = password;
        this.email = email;
        Address = address;
    }

    public StoreOwner(String username, String password, String email) {
        super(username,password,'S');
        this.username = username;
        this.password = password;
        this.email = email;
        storeOwnerExist = false;
        AdminRequest = false;
        OwnerLoggedIn = false;
        emailChanged = false;
        passwordChanged = false;
        businessNameChanged = false;
        AddressChanged = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStoreOwnerExist() {
        return storeOwnerExist;
    }

    public void setStoreOwnerExist(boolean storeOwnerExist) {
        this.storeOwnerExist = storeOwnerExist;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }
    public double calculateTotalProfit() {
        double profits=0.0;
        for (Product product : products) {
            profits +=product.getProfit();
        }
        totalProfit=profits;
        return profits;
    }
    public String getMostSellingItem() {
            Product max = new Product("There is no best Selling item",10,5);
            max.setSellingTimes(0);
            for (Product product : products) {
                if(max.getSellingTimes() < product.getSellingTimes())
                    max = product;
            }
            return "for Store "+getUsername()+" The max selling item is "+ max.getName();

    }

    public Product getBestSellingProduct(){
        Product max = new Product("There is no best Selling item",10,5);
        max.setSellingTimes(0);
        for (Product product : products) {
            if(max.getSellingTimes() < product.getSellingTimes())
                max = product;
        }
        return max;
    }

    public ArrayList<String> getQuantitySoldTimes(){
        for (Product product : products) {
            report.add(product.getName() + " product has been sold for "+ product.getSellingTimes() +" times");
        }

        return report;
    }

    public boolean isAdminRequest() {
        return AdminRequest;
    }

    public void setAdminRequest(boolean adminRequest) {
        AdminRequest = adminRequest;
    }

    public boolean isOwnerLoggedIn() {
        return OwnerLoggedIn;
    }

    public void setOwnerLoggedIn(boolean loggedIn) {
        OwnerLoggedIn = loggedIn;
    }

    public  boolean printAllProducts(){
        boolean printed = false;
        System.out.println("------ Products List ------");
        for(Product p : products)
        {

            System.out.println(p);
            printed = true;
        }
        return printed;
    }

    public static boolean isProductAvailable(String name) {
        for(Product p : products)
        {
            if(p.getName().equals(name))
                return true;
        }
        return false;
    }

    public void addProduct(String name, String description,double price, double rmp){
        Product newProduct = new Product(name ,description,price,rmp);
        addProductToFile("Products.txt",newProduct);
        products.add(newProduct);
    }
    public boolean updateProduct(String name, String description) {
        boolean updated = false;
        for(Product p : products)
        {
            if(p.getName().equals(name))
                p.setDescription(description);
            updated = true;
        }
        return updated;
    }

    public boolean removeProduct(String name) {
        boolean deleted = false;
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getName().equals(name)) {
                iterator.remove();
                deleted = true;
            }
        }
        return deleted;
    }

    public int getAllSellingTimes ()
    {
        int sum = 0;
        for (Product product : products) {
            sum+=product.getSellingTimes();
        }
        return sum;
    }
    public String printProfitsReport() {
        String Report = "The total profit is: "+getTotalProfit() +", The number of sold items is : " + getAllSellingTimes();
        return Report;
    }

    public boolean setDiscountOnProduct(String productName, double discount) {
        boolean applied = false;
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                p.setDiscount(discount);
                applied = true;
                p.updatePrice(discount);
            }
        }
        return applied;
    }

    public String viewAccountDetails(){
        return ANSI_BRIGHT_YELLOW + "Business name: "+ getBusinessName() + "\nUsername: " + getUsername() + "\nPassword: "+getPassword()+"\nEmail: "+getEmail()+"\nCity: "+getAddress() + ANSI_RESET;

    }

    public boolean updateEmail(String newEmail){
        setEmail(newEmail);
        emailChanged= true;
        return emailChanged;
    }

    public boolean updatePassword(String newPassword){
        setPassword(newPassword);
        passwordChanged = true;
        return passwordChanged;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }
    public boolean updateBusinessName(String newBusinessName){
        setBusinessName(newBusinessName);
        businessNameChanged = true;
        return businessNameChanged;
    }
    public boolean updateAddress(String newAddress){
        setAddress(newAddress);
        AddressChanged = true;
        return AddressChanged;
    }

        public String completePayment(double payment,String ItemName){

        for(Product p : products)
        {
            if(p.getName().equals(ItemName)){
                System.out.println(p.toString()+"Change: "+(payment-p.getPrice())+"\n");
            return p.toString()+"Change: "+(payment-p.getPrice())+"\n";

        }
    }
        return"Error: item not found";
    }

    public ArrayList<String> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(ArrayList<String> messagesList) {
        this.messagesList = messagesList;
    }

    public void addMessage(String message) {
        messagesList.add(message);
    }

    @Override
    public String toString() {
        return username;
    }

    public static void loadProductsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(" ");
                if (productData.length == 4) {
                    String name = productData[0];
                    String description = productData[1];
                    double price = Double.parseDouble(productData[2]);
                    double rawMaterialPrice = Double.parseDouble(productData[3]);

                    Product product = new Product(name, description, price, rawMaterialPrice);
                    products.add(product);
                } else {
                    System.out.println("Invalid data format in file: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addProductToFile(String fileName, Product product) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            String productData = product.getName() + " " +
                    product.getDescription() + " " +
                    product.getPrice() + " " +
                    product.getRawMaterialCost();
            bw.write(productData);
            bw.newLine(); // Move to the next line for the next product
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeProductsToFile(String fileName, ArrayList<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                String productData = product.getName() + " " +
                        product.getDescription() + " " +
                        product.getPrice() + " " +
                        product.getRawMaterialCost();
                bw.write(productData);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProductInFile(String fileName, String productName, Product updatedProduct) {
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getName().equals(productName)) {
                products.set(i, updatedProduct); // Replace with the updated product
                found = true;
                break;
            }
        }

        if (found) {
            writeProductsToFile(fileName, products);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public boolean deleteProductFromFile(String fileName, String productName) {
        boolean found = false;

        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(productName)) {
                iterator.remove(); // Remove the product from the list
                found = true;
                break;
            }
        }

        if (found) {
            writeProductsToFile(fileName, products); // Update the file with the modified list
            System.out.println("Product removed.");
        }
        else {
            System.out.println("Product not found.");
        }

        return found;
    }

    public static boolean printAllProducts1(){
        boolean printed = false;
        int counter=1;
        System.out.println(ANSI_WHITE + ANSI_BOLD+ "------ Products List ------" + ANSI_RESET);
        for(Product p : products)
        {

            System.out.println(ANSI_WHITE+counter+"  "+p.getName()+"  price: "+p.getPrice() + ANSI_RESET);
            counter++;
            printed = true;
        }
        return printed;
    }

    public static double getProductPrice(String toFind){
        double Price =0;


        for(Product p : products)
        {
            if(p.getName().equals(toFind)){

                Price = p.getPrice();
                return Price;
          }
        }
        System.out.println("Product not found ");

        return Price;
    }



}

