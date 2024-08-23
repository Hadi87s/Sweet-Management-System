package Sweet.System;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommunicationAndNotificationFeature {

    SweetSystem myApp;

    public CommunicationAndNotificationFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }
    @When("I send a message to user {string} with the content {string}")
    public void iSendAMessageToUserWithTheContent(String username, String message) {
        myApp.sendMessageToUser(message, username);
        assertTrue(myApp.isMessageSent());
    }

    @Then("the user {string} should receive the message with the content {string}")
    public void theUserShouldReceiveTheMessageWithTheContent(String username, String message) {
        // Fetch the actual user object from myApp's Users list
        User user = myApp.getUserByUsername(username);
        boolean messageFound = false;
        ArrayList<String> messages = user.getMessagesList();


        for (String m : messages) {
            if (m.trim().equals(message.trim())) {
                messageFound = true;
                break;
            }
        }
        assertTrue("Message not found", messageFound);
    }



    @When("I send a message to supplier {string} with the content {string}")
    public void iSendAMessageToSupplierWithTheContent(String name, String message) {
        RawSupplier supplier = new RawSupplier(name,"123","SuppFERr1@example.com");
        supplier.setUsername(name);
        supplier.setPassword("RMS0");
        supplier.setEmail("Supplier1@example.com");
        supplier.setBusinessName("SondoForSupplying");
        supplier.setAddress("Nablus");

        assertNotNull(supplier.getEmail());
        assertNotNull(supplier.getPassword());
        assertNotNull(supplier.getBusinessName());
        assertNotNull(supplier.getAddress());
        assertNotNull(supplier.viewAccountDetails());
        assertNotNull(supplier.toString());

        myApp.sendMessageToSupplier(message,name);
        assertTrue(myApp.isMessageSent());
    }

    @Then("the supplier {string} should receive the message with the content {string}")
    public void theSupplierShouldReceiveTheMessageWithTheContent(String name, String message) {
        RawSupplier r = myApp.getSupplierByUsername(name);
        boolean messageFound = false;
        ArrayList<String> messages = r.getMessagesList();

        for(String m : messages) {
            if (m.equals(message)) {
                messageFound = true;
                break;
            }
        }
        assertTrue("Message not found",messageFound);
    }

    @Given("I have enabled email notifications")
    public void iHaveEnabledEmailNotifications() {
        myApp.enableEmailNotifications();
        assertTrue(myApp.isEmailNotificationsEnabled());
    }


    @When("a special request is made by user {string} to store owner {string}")
    public void aSpecialRequestIsMadeByUserToStoreOwner(String username, String ownername) {
        String requestContent = "I would like to have a chocolate cake with chocolate ships on top, and a vanilla cream as a covering, thank you!";
        StoreOwner owner = myApp.getStoreOwnerByUsername(ownername);
        owner.setEmail("MedbLucifer@gmail.com");
        User user = myApp.getUserByUsername(username);

        myApp.makeSpecialRequest(user, owner,user.getRequestContent(requestContent));
        assertTrue(myApp.isSpecialRequestMade());
        owner = myApp.getStoreOwnerByUsername("zahiABUshalbak");
        assertNull("something went wrong on get not valid store owner",owner);
    }



    @Then("I should receive an email notification with the content {string}")
    public void iShouldReceiveAnEmailNotificationWithTheContent(String expectedContent) {
        String actualContent = myApp.getLastEmailNotificationContent();
        assertNotNull("No email notification received", actualContent);

    }



}
