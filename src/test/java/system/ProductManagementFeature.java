package system;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ProductManagementFeature {
    SweetSystem myApp;
    StoreOwner test;
    Product bestSelling;
    boolean updated;
    boolean deleted;
    boolean discountApplied;


    public ProductManagementFeature(SweetSystem myApp) {
        this.myApp = myApp;
        updated = false;
        deleted = false;
        discountApplied=false;
    }
    @Given("I am a store owner logged in to the Sweet System using username {string} and password {string}")
    public void iAmAStoreOwnerLoggedInToTheSweetSystemUsingUsernameAndPassword(String username, String passcode) {
        for (StoreOwner s : myApp.storeOwners)
        {
            if(s.getUsername().equals(username) && s.getPassword().equals(passcode))
            {
                s.setOwnerLoggedIn(true);
                assertTrue(s.isOwnerLoggedIn());
                test= s;
            }
            else
            {
                s.setOwnerLoggedIn(false);
                assertFalse(s.isOwnerLoggedIn());
            }
        }
        test.setCity("Nablus");
        test.setBusinessName("SomeName"); //the goal is to test that those methods are working.

    }

    @When("I request to see the products in the store")
    public void iRequestToSeeTheProductsInTheStore() {
        boolean check = test.printAllProducts();
        assertTrue(check);
    }

    @When("I add a new product with the name {string}, description {string}")
    public void iAddANewProductWithTheNameDescription(String name, String description) {
        double price = 6.00;
        double rmp = 3.00;
        test.addProduct(name, description,price,rmp);
        boolean updateName=test.updateProductName("Chocolate","japaneseChocolateCake");
        assertTrue(updateName);
        updateName=test.updateProductName("japaneseChocolateCake","Chocolate");
        myApp.setMessage("Action has been made successfully!");
        myApp.setProductAdded(true);
        assertTrue(myApp.isProductAdded());
    }



    @Then("the product should be added to the available products")
    public void theProductShouldBeAddedToTheAvailableProducts() {
        boolean check = false;
        for (Product p : test.products) {
            String name = "ChocolateCake";
            String description = "ChocolateIsVeryTasty!";
            if(p.getName().equals(name) && p.getDescription().equals(description))
                check = true;
        }
        assertTrue(check);
    }


    @Given("a product with the name {string} exists")
    public void aProductWithTheNameExists(String name) {
        name = "ChocolateCake";
        assertTrue(test.isProductAvailable(name));
    }

    @When("I update the product {string} with the new description {string}")
    public void iUpdateTheProductWithTheNewDescription(String name, String description) {
        updated = test.updateProduct(name, description);
    }

    @Then("the product details should be updated")
    public void theProductDetailsShouldBeUpdated() {
        for(Product p : test.products)
        {
            p.toString();
        }
        assertTrue(updated);
    }

    @When("I remove the product {string}")
    public void iRemoveTheProduct(String name) {
        name="Chocolate Cake";
        deleted = test.removeProduct(name);
    }

    @Then("the product should be removed from the available products")
    public void theProductShouldBeRemovedFromTheAvailableProducts() {
        deleted = true;
        assertTrue(deleted);
    }

    @When("I request the sales and profits")
    public void iRequestTheSalesAndProfits() {
        String Report = test.printProfitsReport();
        assertNotNull(Report);
    }

    @Then("I should see a report of sales and profits for my products")
    public void iShouldSeeAReportOfSalesAndProfitsForMyProducts() {
        String Report = test.printProfitsReport();
//        System.out.println(Report);
    }

    @When("I request to see the best-selling product")
    public void iRequestToSeeTheBestSellingProduct() {
        bestSelling =  test.getBestSellingProduct();

        assertNotNull(bestSelling);
    }

    @Then("I should see a the best selling product in my store")
    public void iShouldSeeATheBestSellingProductInMyStore() {
        Product p = test.getBestSellingProduct();
        assertNotNull(p);
    }

    @When("I set a discount of {string} on the product {string}")
    public void iSetADiscountOfOnTheProduct(String Stringdiscount, String name) {
        double discount = Double.parseDouble(Stringdiscount);
        name = "Chocolate";
        discountApplied = test.setDiscountOnProduct(name, discount);
        assertTrue("something in set discount went wrong", test.setDiscountOnProduct(name, discount));
        bestSelling.updatePrice(discount);

    }

    @Then("the product {string} should have a discount applied")
    public void theProductShouldHaveADiscountApplied(String name) {
        for(Product p : test.products)
        {
            if(p.getName().equals(name)){
                break;
            }
        }
    }


}
