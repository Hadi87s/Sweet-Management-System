package Sweet.System;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommunicationAndFeedbackFeature {

    SweetSystem myApp;
    StoreOwner test;
    RawSupplier testR;
    Feedback testF;
    public CommunicationAndFeedbackFeature(SweetSystem myApp) {
        this.myApp = myApp;
        test=myApp.storeOwners.get(0);
        testR=myApp.Suppliers.get(0);
        testF=myApp.Feedbacks.get(0);
    }

    @When("I have an inquiry or need assistance")
    public void iHaveAnInquiryOrNeedAssistance() {

        System.out.println("Supplier email: "+testR.getEmail()+"\n");
        System.out.println("Owner email: "+test.getEmail()+"\n");

    }

    @Then("I should be able to send a message describing my problem with store owners and suppliers")
    public void iShouldBeAbleToSendAMessageDescribingMyProblemWithStoreOwnersAndSuppliers() {
        ArrayList<String> expectedOwner= new ArrayList<>();;
        expectedOwner.add("problem message  store Owner 1\n");
        System.out.println("Owner the message you sent: "+test.getMessagesList());

        ArrayList<String> expectedSupllier= new ArrayList<>();;
        expectedSupllier.add("problem message Supplier1\n");
        System.out.println("the message you sent: "+testR.getMessagesList());
        assertEquals("Supplier1 send message went not as expected",expectedSupllier,testR.getMessagesList());

    }

    @When("I want to share my experience")
    public void iWantToShareMyExperience() {
       String expected="Chocolate Cake was crazy\n";


        assertEquals("feedback send went not as expected",expected,testF.getFeedback());

    }

    @Then("I should be able to provide my feedback")
    public void iShouldBeAbleToProvideMyFeedback() {
        System.out.println(testF.toString());

    }

}
