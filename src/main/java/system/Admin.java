package system;

public class Admin extends User{
    private boolean adminLoggedIn;

    public Admin(String username, String password) {
        super(username, password,'A');
        adminLoggedIn = false;
    }

    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

    public void setAdminLoggedIn(boolean adminLoggedIn) {
        this.adminLoggedIn = adminLoggedIn;
    }

    @Override
    public String viewAccountDetails(){
        return ANSI_BRIGHT_YELLOW + "\nUsername: " + getUsername() + "\nPassword: "+getPassword()+ ANSI_RESET;

    }
    @Override
    public String toString() {
        return  super.getUsername();
    }
}

