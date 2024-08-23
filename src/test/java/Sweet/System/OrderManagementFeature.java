package Sweet.System;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class OrderManagementFeature {
    SweetSystem myApp;
    private StoreOwner storeOwner;
    private Order order;
    public OrderManagementFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }

    @Given("I am a user logged in to the Sweet System using username {string} and password {string}")
    public void iAmAUserLoggedInToTheSweetSystemUsingUsernameAndPassword(String username, String password) {
        boolean loggedIn = false;
        for (User user : myApp.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedIn = true;
                break;
            }
        }
        assertTrue(loggedIn);
    }

    @When("I place an order for the product {string} with quantity {int} and an ID {string}")
    public void iPlaceAnOrderForTheProductWithQuantityAndAnID(String product, Integer quantity, String orderId) {
        for (User user : myApp.getUsers()) {
            for (Order o : user.getOrderList()){
                if (o.getOrderID().equals(orderId)){
                    order = new Order(product,quantity,orderId);
                    user.addOrder(order);
                    break;
                }
            }
        }
    }

    @Then("the order should be processed")
    public void theOrderShouldBeProcessed() {
        assertTrue(order.isProcessed());
    }
    @Then("the order status should be {string}")
    public void theOrderStatusShouldBe(String expectedStatus) {
        String actualStatus = order.getOrderStatus();
        assertEquals("Order status should match", expectedStatus, actualStatus);
    }

    @Given("I have placed an order with order ID {string}")
    public void iHavePlacedAnOrderWithOrderID(String orderId) {
        for (User user : myApp.getUsers()) {
            for (Order o : user.getOrderList()){
                if (o.getOrderID().equals(orderId)){
                    order = user.getOrderById(orderId);
                    assertNotNull("Order should exist", order);
                    break;
                }
            }
        }
        //just to test the function
        for (User u : myApp.getUsers()) {
        assertNull("Order should not exist", u.getOrderById("7997600"));
        break;
    }
    }

    @Given("an order with order ID {string} exists")
    public void anOrderWithOrderIDExists(String orderId) {
        for (User user : myApp.getUsers()) {
            for (Order o : user.getOrderList()){
                if (o.getOrderID().equals(orderId)){
                    order = user.getOrderById(orderId);
                    assertNotNull("Order should exist", order);
                    break;
                }
            }
        }
    }


    @When("I update the status of order {string} to {string}")
    public void iUpdateTheStatusOfOrderTo(String orderId, String status) {
        for (User user : myApp.getUsers()) {
            for (Order o : user.getOrderList()){
                if (o.getOrderID().equals(orderId)){
                    order = user.getOrderById(orderId);
                    assertNotNull("Order should exist", order);
                    order.setOrderStatus(status);
                }
            }
        }
    }

    @When("I cancel the order {string}")
    public void iCancelTheOrder(String orderId) {
        for (User u : myApp.getUsers()) {
            for (Order o : u.getOrderList()){
                if (o.getOrderID().equals(orderId)){
                    order = u.getOrderById(orderId);
                    assertNotNull("Order should exist", order);
                    u.cancelOrder(orderId);
                    break;
                }
            }
        }

    }

}
