package Sweet.System;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ed;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContentManagementFeature {

    SweetSystem myApp;
    Recipe recipe2;
    Post post2;
    public ContentManagementFeature(SweetSystem myApp) { this.myApp = myApp; }

    @When("I request to see the content")
    public void iRequestToSeeTheContent() {
        ArrayList<Recipe> recipes = myApp.getRecipes();
        ArrayList<Post> posts = myApp.getPosts();

        for (Recipe recipe : recipes) {
            System.out.println(recipe.toString());
        }

        for (Post post : posts) {
            System.out.printf(post.toString());

        }

    }

    @Then("I should see a list of all recipes and posts")
    public void iShouldSeeAListOfAllRecipesAndPosts() {
        ArrayList<Recipe> recipes = myApp.getRecipes();
        ArrayList<Post> posts = myApp.getPosts();
        String expectedFromRecipes1 = "Kunafa" + ": "+"dough\n";
        String expectedFromRecipes2 ="Chocolate Cake" + ": " + "a cake flavored with melted chocolate, cocoa powder" + "\n";
        String actualFromRecipes1= "";
        String actualFromRecipes2= "";
        String expectedFromPosts = "Kunafa" + ": "+"dough\n";;
        String actualFromPosts="";


        for (Recipe recipe : recipes) {

            if (recipe.getTitle().equals("Kunafa")) {
                actualFromRecipes1 = recipe.toString();
                assertEquals("Recipes went not as expected", actualFromRecipes1, expectedFromRecipes1);
            }
            if (recipe.getTitle().equals("Chocolate Cake")) {
                actualFromRecipes2 = recipe.toString();
                assertEquals("Recipes went not as expected", actualFromRecipes2, expectedFromRecipes2);
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
        boolean Edited = myApp.editRecipe("Ice Creem","Yummy",sampleRecipe);
        assertTrue(Edited);

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
        boolean Edited = myApp.editPost("Hi","This is a post",samplePost);
        assertTrue(Edited);
    }

    @Then("I should be able to delete an existing post")
    public void iShouldBeAbleToDeleteAnExistingPost() {
        boolean deleted = myApp.deletePost(post2);
        assertTrue(deleted);
    }

    @When("I request to see the users feedback")
    public void iRequestToSeeTheUsersFeedback() {
        String expectedMessage = "User's Feedback: The sweets are awesome, the place was quite and cosy, and the service was perfect, 10/10 Sweet shop!";
        String actualMessage = "";
        for (User user : myApp.Users) {
            actualMessage = user.getUserFeedback().toString();
            assertEquals("Requesting to see the user's feedback went rouge",expectedMessage, actualMessage);
        }

    }

    @Then("I should see a list of all users feedback")
    public void iShouldSeeAListOfAllUsersFeedback() {

        for (User user : myApp.Users) {
            System.out.println(user.getUserFeedback().toString());
        }
    }


}