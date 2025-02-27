package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.HomePage;

import java.io.IOException;
import java.util.List;

public class Home extends Context {
    private final HomePage homePage;

    public Home(Manager manager) {
        super(manager);
        this.homePage = new HomePage(manager.getDriver());
    }

    @When("I extract the list of links from the homepage")
    public void extractLinks() {
        // Extract links & store in context
        List<String> actualLinks = homePage.getCleanedLinks();
        stash("actualLinks", actualLinks);
    }

    @Then("the list should contain the expected links")
    public void verifyLinks() throws IOException {
        // Call verification method from HomePage
        homePage.verifyLinks();
    }
}
