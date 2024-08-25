package sweet.system;

public class RawSupplier extends User {
    private String email;
    private String businessName;
    private boolean supplierExist;


    public RawSupplier(String username, String password, String email) {
        super(username, password,'R');
        this.email = email;
        supplierExist = false;
    }

    @Override
    public String getEmail(){
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


    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String viewAccountDetails(){
        return "Business name: "+ getBusinessName() + "\nUsername: " + getUsername() + "\nPassword: "+getPassword()+"\nEmail: "+getEmail()+"\nCity: "+getAddress()+"\n";

    }

    @Override
    public String toString() {
        return super.getUsername();
    }
}
