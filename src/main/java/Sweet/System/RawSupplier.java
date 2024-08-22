package Sweet.System;

import java.util.ArrayList;
import java.util.function.Supplier;

public class RawSupplier extends User {
    private String username;
    private String password;
    private String email;
    private String BusinessName;
    private String Address;
    private boolean supplierExist;
    private static ArrayList<String> messagesList= new ArrayList<>();



    public RawSupplier(String username, String password, String email) {
        super(username, password,'R');
        this.username = username;
        this.password = password;
        this.email = email;
        supplierExist = false;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isSupplierExist() {
        return supplierExist;
    }

    public void setSupplierExist(boolean supplierExist) {
        this.supplierExist = supplierExist;
    }
    @Override
    public ArrayList<String> getMessagesList() {
        return messagesList;
    }

    @Override
    public void addMessage(String message) {
        messagesList.add(message);
    }


    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }
    @Override
    public String getAddress() {
        return Address;
    }
    @Override
    public void setAddress(String address) {
        Address = address;
    }
    @Override
    public String viewAccountDetails(){
        return "Business name: "+ getBusinessName() + "\nUsername: " + getUsername() + "\nPassword: "+getPassword()+"\nEmail: "+getEmail()+"\nCity: "+getAddress()+"\n";

    }

    @Override
    public String toString() {
        return username;
    }
}
