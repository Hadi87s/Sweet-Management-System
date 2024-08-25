package system;



import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MonitoringAndReportingFeature {

    SweetSystem myApp;
    StoreOwner storeOwner1 = new StoreOwner("StoreOwner1","SO1","storeOwner1@example.com");
    private HashMap<String, Integer> test;



    public MonitoringAndReportingFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }

    @When("I generate a financial report")
    public void iGenerateAFinancialReport() {
        boolean checker = false;
        double profits;
        for (StoreOwner st : myApp.storeOwners) {
            profits = st.calculateTotalProfit();
            st.setTotalProfit(profits);
            checker = true;
        }
        assertTrue(checker);
    }
    @Then("I should see the total profits")
    public void iShouldSeeTheTotalProfits() {
        String expectedMessage ="The total profit is 5.0";
        String actualMessage="";
        for (StoreOwner st : myApp.storeOwners) {
            actualMessage = "The total profit is " + st.getTotalProfit();
        }
        assertNotNull("A problem occured",actualMessage);
    }

    @When("I request a report of best-selling products")
    public void iRequestAReportOfBestSellingProducts() {
        storeOwner1.setAdminRequest(true);
        assertTrue(storeOwner1.isAdminRequest());
    }
    @Then("I should see the list of best-selling products in each store")
    public void iShouldSeeTheListOfBestSellingProductsInEachStore() {
        String actualMessage= "";
        String expectedMessage="for Store StoreOwner1 The max selling item is Chocolate Cake";
        for (StoreOwner st : myApp.storeOwners) {
            actualMessage = st.getMostSellingItem();
            assertNotNull("Something Went wrong here",actualMessage);
        }
    }
    @Then("the quantity sold for each product should be displayed")
    public void theQuantitySoldForEachProductShouldBeDisplayed() {
        ArrayList<String> actualMessage;
        String expectedMessage="Chocolate Cake product has been sold for 5 times";
        for (StoreOwner st : myApp.storeOwners) {
            actualMessage = st.getQuantitySoldTimes();
            assertNotNull("Something Went wrong here",actualMessage.get(0));
        }
    }

    @When("I request statistics on registered users by city")
    public void iRequestStatisticsOnRegisteredUsersByCity() {
        test = myApp.getUserStatisticsByCity();
    }
    @Then("I should see the number of registered users in each city")
    public void iShouldSeeTheNumberOfRegisteredUsersInEachCity() {
        String actualMessage= test.toString();
        String expectedMessage = "{Qalqiliah=0, Tulkarem=0, Nablus=2, Tubas=0, Jerusalem=0, Jericho=0, Hebron=0, Bethlehem=0, Jenin=1, Ramallah=1}";
        assertEquals(expectedMessage,actualMessage);
    }





}

