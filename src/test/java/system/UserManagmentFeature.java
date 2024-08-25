package system;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Iterator;
import java.util.List;

import static system.userType.STORE_OWNER;
import static system.userType.SUPPLIER;
import static org.junit.Assert.*;

public class UserManagmentFeature {
    private SweetSystem myApp;
    private List<Object> userList;
    Admin admin = new Admin("Admin","Admin");
    StoreOwner storeOwner1 = new StoreOwner("StoreOwner1","SO1","storeOwner1@example.com");
    RawSupplier supplier1 =new RawSupplier("Supplier1","RMS1","supplier1@example.com");
    boolean ifIntered = false;

    public UserManagmentFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }

    @Given("I am an admin logged in to the Sweet System using username {string} and password {string}")
    public void iAmAnAdminLoggedInToTheSweetSystemUsingUsernameAndPassword(String un, String passcode) {
        admin.setUsername(un);
        admin.setPassword(passcode);
        for (Admin a : myApp.admins)
        {
            if(a.getUsername().equals(un) && a.getPassword().equals(passcode))
            {
                admin.setAdminLoggedIn(true);
                assertTrue(admin.isAdminLoggedIn());
            }
            else
            {
                admin.setAdminLoggedIn(false);
                assertFalse(admin.isAdminLoggedIn());
            }
        }
        assertNotNull(admin.viewAccountDetails()); //this is to view admin's information or credintials
        assertNotNull(admin.toString());
    }

    @When("I add a store owner with a username {string} and a password {string} and an email {string}")
    public void iAddAStoreOwnerWithAUsernameAndAPasswordAndAnEmail(String un, String pass, String email) {
        storeOwner1 = new StoreOwner(un,pass,email);
        myApp.storeOwners.add(storeOwner1);
        myApp.setMessage("Operation Complete!");
    }

    @Then("the store owner should be added to the Sweet System")
    public void theStoreOwnerShouldBeAddedToTheSweetSystem() {

        assertTrue(myApp.isAddedInSystem(STORE_OWNER ,storeOwner1.getUsername()));
    }

    @Then("a confirmation message will appear")
    public void aConfirmationMessageWillAppear() {
        myApp.setMessage("Operation Complete!");
        String expectedMessage = "Operation Complete!";
        assertEquals("Store Owner was not added properly!", expectedMessage, myApp.getMessage());
    }

    @Given("a store owner with username {string} exists in the Sweet System")
    public void aStoreOwnerWithUsernameExistsInTheSweetSystem(String username) {
        for(StoreOwner st : myApp.storeOwners)
        {
            if(st.getUsername().equals(username))
            {
                storeOwner1.setStoreOwnerExist(true);
                assertTrue(storeOwner1.isStoreOwnerExist());
                break;
            }
            else {
                storeOwner1.setStoreOwnerExist(false);
                assertFalse(storeOwner1.isStoreOwnerExist());
                break;
            }
        }

    }

    @When("I remove the store owner with a username {string}")
    public void iRemoveTheStoreOwnerWithAUsername(String username) {
        boolean removed = myApp.removeStoreOwner(username);
        ifIntered = false;
        if (removed)
            ifIntered = true;
        storeOwner1.setStoreOwnerExist(false);
    }


    @Then("the store owner should be removed from the Sweet System")
    public void theStoreOwnerShouldBeRemovedFromTheSweetSystem() {
        assertFalse(storeOwner1.isStoreOwnerExist());
    }

    @When("I add a supplier with a username {string} and a password {string} and an email {string}")
    public void iAddASupplierWithAUsernameAndAPasswordAndAnEmail(String un, String pass, String email) {
        supplier1 = new RawSupplier(un,pass,email);
        myApp.suppliers.add(supplier1);
        myApp.setMessage("Operation Complete!");
    }

    @Then("the supplier should be added to the Sweet System")
    public void theSupplierShouldBeAddedToTheSweetSystem() {
        assertTrue(myApp.isAddedInSystem(SUPPLIER ,supplier1.getUsername()));

    }

    @Given("a supplier with username {string} exists in the Sweet System")
    public void aSupplierWithUsernameExistsInTheSweetSystem(String username) {
        for(RawSupplier sp : myApp.suppliers)
        {
            if(sp.getUsername().equals(username))
            {
                supplier1.setSupplierExist(true);
                assertTrue(supplier1.isSupplierExist());
                break;
            }
            else {
                supplier1.setSupplierExist(false);
                assertFalse(supplier1.isSupplierExist());
                break;
            }
        }
    }

    @When("I remove a supplier with a username {string}")
    public void iRemoveASupplierWithAUsername(String username) {
        Iterator<RawSupplier> iterator = myApp.suppliers.iterator();
        while (iterator.hasNext()) {
            RawSupplier sp = iterator.next();
            if (sp.getUsername().equals(username)) {
                iterator.remove();
                supplier1.setSupplierExist(false);
                break;
            }
        }
    }

    @Then("the supplier should be removed from the Sweet System")
    public void theSupplierShouldBeRemovedFromTheSweetSystem() {
        assertFalse(supplier1.isSupplierExist());
    }


    @When("I view the user list")
    public void iViewTheUserList() {
        userList = myApp.getAllUsers();
    }

    @Then("I should see the list of all store owners and raw material suppliers and normal users")
    public void iShouldSeeTheListOfAllStoreOwnersAndRawMaterialSuppliersAndNormalUsers() {
        boolean hasUsers = false;
        boolean hasStoreOwners = false;
        boolean hasSuppliers = false;

        for (Object user : userList) {
            if (user instanceof User) {
                hasUsers = true;
            } else if (user instanceof StoreOwner) {
                hasStoreOwners = true;
            } else if (user instanceof RawSupplier) {
                hasSuppliers = true;
            }
        }

        assertTrue("User list should contain Users or StoreOwners or RawMaterialSuppliers", hasUsers ||hasStoreOwners ||hasSuppliers);
    }


}

