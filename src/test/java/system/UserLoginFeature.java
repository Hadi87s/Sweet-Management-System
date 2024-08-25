package system;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class UserLoginFeature {

    SweetSystem myApp;
    User user = new User("User1","123","user1@example.com","Nablus");
    //dependency Injection
    public UserLoginFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }

    @Given("user is not in the Sweet system")
    public void userIsNotInTheSweetSystem() {
        assertFalse(user.isUserLoggedIn());
    }


    @When("user enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        user = new User(username,password); //this should be modified
        for(User u : myApp.getUsers()) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user.setUserLoggedIn(true);
                myApp.setMessage("Welcome " + username + "!");
                break;
            }
            else if(u.getUsername().equals(username)){
                myApp.setMessage("Wrong Password!");
                user.setUserLoggedIn(false);
            }
            else {
                myApp.setMessage("Invalid Credentials!");
                user.setUserLoggedIn(false);
            }
        }
    }

    @Then("user is now in the system")
    public void userIsNowInTheSystem() {
        user.testGetSetRole();//just to test
        assertTrue(user.isUserLoggedIn());
    }

    @Then("a welcome message will appear")
    public void aWelcomeMessageWillAppear() {
        String expectedMessage = "Welcome User1!";
        assertEquals("Welcome message not displayed correctly", expectedMessage, myApp.getMessage());
    }

    @Then("user is not logged in to the system")
    public void userIsNotLoggedInToTheSystem() {
        assertFalse(user.isUserLoggedIn());
    }

    @Then("a wrong password message will appear")
    public void aWrongPasswordMessageWillAppear() {
        String expectedMessage = "Wrong Password!";
        assertEquals("Wrong password message not displayed correctly", expectedMessage, myApp.getMessage());
    }


    @Then("a wrong credential message will appear")
    public void aWrongCredentialMessageWillAppear() {
        String expectedMessage = "Invalid Credentials!";
        assertEquals("Invalid credentials message not displayed correctly", expectedMessage, myApp.getMessage());
    }
}

