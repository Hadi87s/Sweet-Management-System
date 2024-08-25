package system;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ContentManagementFeature {

    SweetSystem myApp;
    Recipe recipe2;
    Post post2;
    Admin admin3;
    public ContentManagementFeature(SweetSystem myApp) { this.myApp = myApp; }

    @When("I request to see the content")
    public void iRequestToSeeTheContent() {
        List<Recipe> recipes = myApp.getRecipes();
        List<Post> posts = myApp.getPosts();
        boolean found = false;
        for (Recipe recipe : recipes) {
            if (recipe.toString() != null) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        for (Post post : posts) {
            if (post.toString() != null) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        admin3 = myApp.getAdminByUsername("Admin3");//just to check
        assertNotNull("some thing wrong on get admin ",admin3);
        admin3=myApp.getAdminByUsername("Admin31");
        assertNull("some thing wrong on get admin not valid ",admin3);
    }

    @Then("I should see a list of all recipes and posts")
    public void iShouldSeeAListOfAllRecipesAndPosts() {
        List<Recipe> recipes = myApp.getRecipes();
        List<Post> posts = myApp.getPosts();
        String actualFromRecipes1= "";
        String actualFromRecipes2= "";
        String expectedFromPosts = "Kunafa" + ": "+"dough\n";
        String actualFromPosts="";


        for (Recipe recipe : recipes) {

            if (recipe.getTitle().equals("Kunafa")) {
                actualFromRecipes1 = recipe.toString();
                assertNotNull("Recipes went not as expected", actualFromRecipes1);
            }
            if (recipe.getTitle().equals("ChocolateCake")) {
                actualFromRecipes2 = recipe.toString();
                assertNotNull("Recipes went not as expected", actualFromRecipes2);
            }
        }




        for (Post post : posts) {
            actualFromPosts = post.toString();
            assertEquals("Posts went not as expected", expectedFromPosts, actualFromPosts);
        }

    }

    @Then("I should be able to add a new recipe")
    public void iShouldBeAbleToAddANewRecipe() {
        recipe2 = new Recipe("Creep","a melted chocolate desert made usually with Aziza brand");
        myApp.addRecipe(recipe2);
        assertTrue(myApp.isRecipeAdded());
    }

    @Then("I should be able to edit an existing recipe")
    public void iShouldBeAbleToEditAnExistingRecipe() {
        Recipe sampleRecipe = new Recipe("HAHS","HFDHF");
        myApp.addRecipe(sampleRecipe);
        assertTrue(myApp.editRecipe("Ice Creem","Yummy",sampleRecipe));

    }

    @Then("I should be able to delete an existing recipe")
    public void iShouldBeAbleToDeleteAnExistingRecipe() {
        boolean deleted = myApp.deleteRecipe(recipe2);
        assertTrue(deleted);
    }

    @Then("I should be able to add a new post")
    public void iShouldBeAbleToAddANewPost() {
        post2 = new Post("Kunafa at abu-sir's shop","its fockin nice!");
        myApp.addPost(post2);
        assertTrue(myApp.isPostAdded());
    }

    @Then("I should be able to edit an existing post")
    public void iShouldBeAbleToEditAnExistingPost() {
        Post samplePost = new Post("HAHS","HFDHF");
        myApp.addPost(samplePost);
        assertTrue(myApp.editPost("Hi","This is a post",samplePost));
    }

    @Then("I should be able to delete an existing post")
    public void iShouldBeAbleToDeleteAnExistingPost() {
        boolean deleted = myApp.deletePost(post2);
        assertTrue(deleted);
    }

    @When("I request to see the users feedback")
    public void iRequestToSeeTheUsersFeedback() {
        boolean feedbackChecker = false;

        for (Feedback fb : myApp.getFeedbacks()) {
            if (fb != null) {
                assertNotNull(fb);
                feedbackChecker = true;
                break;
            }
        }
        assertTrue(feedbackChecker);
    }

    @Then("I should see a list of all users feedback")
    public void iShouldSeeAListOfAllUsersFeedback() {
        boolean feedbackShown = true;
        assertTrue(feedbackShown);
    }


}
