package Sweet.System;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommunicationAndFeedbackFeature {

    SweetSystem myApp;
    StoreOwner test;
    RawSupplier testR;
    Feedback testF;
    Feedback Fb;
    String feedbackMessage;
    String targetedProduct;
    public CommunicationAndFeedbackFeature(SweetSystem myApp) {
        this.myApp = myApp;
        test=myApp.storeOwners.get(0);
        testR=myApp.Suppliers.get(0);
        testF=myApp.Feedbacks.get(0);
    }

    @When("I have an inquiry or need assistance")
    public void iHaveAnInquiryOrNeedAssistance() {
        Fb = new Feedback();
    }

    @Then("I should be able to send a message describing my problem with store owners and suppliers")
    public void iShouldBeAbleToSendAMessageDescribingMyProblemWithStoreOwnersAndSuppliers() {
            feedbackMessage = "Some feedback from the user";
            targetedProduct = "Name Of Some Product";
            Fb.setMessage(feedbackMessage);
            Fb.setRelatedProduct(targetedProduct);

    }

    @When("I want to share my experience")
    public void iWantToShareMyExperience() {
       String expected="Chocolate Cake Was Crazy\n";
        Feedback newFeedback=new Feedback(feedbackMessage,targetedProduct);
        myApp.Feedbacks.add(newFeedback); //sharing my experience.
        assertEquals("feedback send went not as expected",expected,testF.getMessage());

    }

    @Then("I should be able to provide my feedback")
    public void iShouldBeAbleToProvideMyFeedback() {
        myApp.printUserFeedbacks();
    }

}
