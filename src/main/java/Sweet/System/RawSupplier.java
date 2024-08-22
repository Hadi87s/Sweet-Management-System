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


    public boolean isSupplierExist() {
        return supplierExist;
    }

    public void setSupplierExist(boolean supplierExist) {
        this.supplierExist = supplierExist;
    }


    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
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
