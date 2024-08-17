package Sweet.System;

public class Admin extends User{
    private String username;
    private String password;
    private boolean adminLoggedIn;

    public Admin(String username, String password) {
        super(username, password,'A');
        this.username = username;
        this.password = password;
        adminLoggedIn = false;
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

    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

    public void setAdminLoggedIn(boolean adminLoggedIn) {
        this.adminLoggedIn = adminLoggedIn;
    }

    public String viewAccountDetails(){
        return ANSI_BRIGHT_YELLOW + "Business name: "+ "\nUsername: " + getUsername() + "\nPassword: "+getPassword()+"\nEmail: "+getEmail()+"\nCity: "+getAddress() + ANSI_RESET;

    }
    @Override
    public String toString() {
        return  username;
    }
}

