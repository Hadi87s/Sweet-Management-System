package system;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ExplorationAndPurchaseFeature {

    StoreOwner test;
    SweetSystem myApp;
    String expectedDietary = """
            Chocolate Cake
            Nutrient: Calories: 305 Fat: 22g Sugar: 74g Protein: 5g
            """;
    String expectedFoodAlergies = """
    Kunafa: dough
    """;
    String searchForDessert = """
    Chocolate Cake
    """;

    public ExplorationAndPurchaseFeature(SweetSystem myApp) {
        this.myApp = myApp;
        test=myApp.getStoreOwners().get(0);
    }

    @When("I want to explore dessert options")
    public void iWantToExploreDessertOptions() {

        assertNotNull(myApp.getOptionList());
    }

    @Then("I should be able to browse and search for dessert recipes")
    public void iShouldBeAbleToBrowseAndSearchForDessertRecipes() {
        assertNotNull("the Recipes search went not as expected",myApp.searchingList(searchForDessert));
    }

    @Then("List of dessert recipes I'm looking for should appear.")
    public void listOfDessertRecipesIMLookingForShouldAppear() {
        assertNotNull("the Recipes search went not as expected",myApp.searchingList(searchForDessert));

    }

    @When("I have specific dietary needs or food allergies")
    public void iHaveSpecificDietaryNeedsOrFoodAllergies() {
        assertNotNull(myApp.printListOfDietaryNeedsAndFoodAllergies( ));

    }

    @Then("I should be able to filter recipes based on those requirements")
    public void iShouldBeAbleToFilterRecipesBasedOnThoseRequirements() {
        String foodAllergiesToDeleteWhenFoundIt ="dairy";

        String searchForNutrient1="Sugar";
        String dietaryNeedSearchingFor="74g";
        assertEquals("the dietary search went not as expected",expectedDietary,myApp.searchingForNutrient( searchForNutrient1, dietaryNeedSearchingFor));

        searchForNutrient1="Calories";
        dietaryNeedSearchingFor="305";
        assertEquals("the dietary search went not as expected",expectedDietary,myApp.searchingForNutrient( searchForNutrient1, dietaryNeedSearchingFor));

        searchForNutrient1="Fat";
        dietaryNeedSearchingFor="22g";
        assertEquals("the dietary search went not as expected",expectedDietary,myApp.searchingForNutrient( searchForNutrient1, dietaryNeedSearchingFor));

        searchForNutrient1="Protein";
        dietaryNeedSearchingFor="5g";
        assertEquals("the dietary search went not as expected",expectedDietary,myApp.searchingForNutrient( searchForNutrient1, dietaryNeedSearchingFor));

        assertNotNull(myApp.searchingForFoodAllergies( foodAllergiesToDeleteWhenFoundIt));

    }

    @When("I want to buy dessert")
    public void iWantToBuyDessert() {
        assertTrue(test.printAllProducts());
    }

    @Then("I should be able to purchase dessert directly from store owners")
    public void iShouldBeAbleToPurchaseDessertDirectlyFromStoreOwners() {

        assertTrue(test.isProductAvailable("ChocolateCake"));
    }
}

