package Sweet.System;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private String  username;
    private String password;
    private boolean userLoggedIn;
    private boolean AddressChanged;
    private boolean EmailChanged;
    private Character Role;
    private String city;
    private String email;
    private String Address;
    private Feedback userFeedback;
    private static ArrayList<String> messagesList = new ArrayList<String>();
    private static ArrayList<Order> orderList = new ArrayList<Order>();
    private ArrayList<DessertCreation> dessertCreations = new ArrayList<>();
    private ArrayList<DessertCreation> sharedDessertCreations = new ArrayList<>();
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_RESET = "\u001B[0m";


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Character role) {
        this.username = username;
        this.password = password;
        Role = role;
    }

    public User(String username, String password, String email, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        userLoggedIn = false;
        AddressChanged = false;
        EmailChanged = false;
    }
 // we need another constructor to set the email and the city among with the username and the password.

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

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Feedback getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(Feedback userFeedback) {
        this.userFeedback = userFeedback;
    }

    public ArrayList<String> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(ArrayList<String> messageList) {
        this.messagesList = messageList;
    }

    public void addMessage(String message) {
        messagesList.add(message);
    }

    public String getMessage(int index){
        return messagesList.get(index);
    }

    public String getRequestContent(String request) {
        return "\n"+getUsername() + " requested a special delivery with a message of: "+request +"\n\nThe provided email if further communication is required: "+ getEmail();
    }
    public String viewAccountDetails(){
        return ANSI_BRIGHT_YELLOW +"\nUsername: " + getUsername() + "\nPassword: "+getPassword()+"\nEmail: "+getEmail()+"\nCity: "+getCity()+"\n" + ANSI_RESET;

    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public void addOrder(Order order){
        order.setOrderStatus("Processed");
        order.setProcessed(true);
        orderList.add(order);
    }

    public Order getOrder(int index){
        return orderList.get(index);
    }

    public static Order getOrderById(String id){
        for(Order order : orderList){
            if (order.getOrderID().equals(id)){
                return order;
            }
        }
        return null;
    }

    public void cancelOrder(String Id)
    {
        for(Order order : orderList){
            if (order.getOrderID().equals(Id)){
                order.setOrderStatus("Cancelled");
            }
        }
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
    public boolean updateAddress(String newAddress){
        setAddress(newAddress);
        AddressChanged = true;
        return AddressChanged;
    }

    public boolean updateEmail(String newEmail){
        setEmail(newEmail);
        EmailChanged = true;
        return EmailChanged;
    }
    public void postDessertCreation(DessertCreation creation) {
        dessertCreations.add(creation);
    }

    public DessertCreation getLatestDessertCreation() {
        if (dessertCreations.isEmpty()) return null;
        return dessertCreations.get(dessertCreations.size() - 1);
    }

    public void shareDessertCreation(DessertCreation creation, User targetUser) {
        targetUser.sharedDessertCreations.add(creation);
    }

    public ArrayList<DessertCreation> getSharedDessertCreations() {
        return sharedDessertCreations;
    }

    @Override
    public String toString() {
        return username;
    }

    public Character getRole() {
        return Role;
    }

    public void setRole(Character role) {
        Role = role;
    }
    public static boolean loadOrdersFromFile(String fileName) {


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] orderData = line.split(" ");
                if (orderData.length == 3) {
                    String productName = orderData[0];
                    String orderID = orderData[1];
                    int quantity = Integer.parseInt(orderData[2]);

                    Order order = new Order(productName, quantity, orderID);
                    orderList.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


}
