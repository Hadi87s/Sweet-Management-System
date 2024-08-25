package system;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class UserAccountFeature {
    SweetSystem myApp;

    public UserAccountFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }
    private User targetUser;

    boolean emailUpdated = false;
    boolean addressUpdated = false;
    @When("I update my personal account information with email {string} and address {string}")
    public void iUpdateMyPersonalAccountInformationWithEmailAndAddress(String email, String address) {
        for (User user : myApp.getUsers()) {
            if(user.getUsername().equals("User1")) {
                emailUpdated = user.updateEmail(email);
                addressUpdated = user.updateAddress(address);
                break;
            }
        }

    }

    @Then("my account information should be updated successfully")
    public void myAccountInformationShouldBeUpdatedSuccessfully() {
        assertTrue(emailUpdated && addressUpdated);
    }

    @Then("the email should be {string}")
    public void theEmailShouldBe(String newEmail) {
        emailUpdated = false;
        for (User user: myApp.getUsers()) {
            if (user.getEmail().equals(newEmail)) {
                emailUpdated = true;
                break;
            }
        }
        assertTrue(emailUpdated);
    }

    @Then("the address should be {string}")
    public void theAddressShouldBe(String address) {
        addressUpdated = false;
        for (User user: myApp.getUsers()) {
            user.setAddress(address);
            if (user.getAddress().equals(address)) {
                addressUpdated = true;
            }
        }
        assertTrue(addressUpdated);
    }

    @When("I post a new dessert creation with title {string} and description {string}")
    public void iPostANewDessertCreationWithTitleAndDescription(String title, String description) {
        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                DessertCreation creation = new DessertCreation(title, description);
                user.postDessertCreation(creation);
            }
        }
    }

    @Then("the dessert creation should be posted successfully")
    public void theDessertCreationShouldBePostedSuccessfully() {
        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                DessertCreation creation = user.getLatestDessertCreation();
                assertNotNull("Dessert creation should be posted", creation);
            }
        }
    }

    @Then("the title should be {string}")
    public void theTitleShouldBe(String title) {
        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                DessertCreation creation = user.getLatestDessertCreation();
                assertEquals("Titles should match", title, creation.getTitle());
            }
        }
    }

    @Then("the description should be {string}")
    public void theDescriptionShouldBe(String description) {
        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                DessertCreation creation = user.getLatestDessertCreation();
                assertEquals("Descriptions should match", description, creation.getDescription());
            }
        }
    }

    @Given("I have posted a dessert creation with title {string} and description {string}")
    public void iHavePostedADessertCreationWithTitleAndDescription(String title, String description) {
        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                DessertCreation creation = new DessertCreation(title, description);
                user.postDessertCreation(creation);
            }
        }
    }

    @When("I share the dessert creation with username {string}")
    public void iShareTheDessertCreationWithUsername(String username) {
        targetUser = new User("User2","123","User2@example.com","Nablus");
        assertNotNull("Target user should exist", targetUser);
        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                DessertCreation creation = user.getLatestDessertCreation();
                user.shareDessertCreation(creation, targetUser);
            }
        }
    }

    @Then("the dessert creation should be shared successfully")
    public void theDessertCreationShouldBeSharedSuccessfully() {

        for (User user: myApp.getUsers()) {
            if (user.getUsername().equals("User1")) {
                assertTrue("Dessert creation should be shared",
                        targetUser.getSharedDessertCreations().contains(user.getLatestDessertCreation()));
            }
        }
    }




}
