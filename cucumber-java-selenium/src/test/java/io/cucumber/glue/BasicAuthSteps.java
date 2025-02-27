package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.BasicAuthPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicAuthSteps extends Context {
    private BasicAuthPage basicAuthPage;

    public BasicAuthSteps(Manager manager) {
        super(manager);
        this.basicAuthPage = new BasicAuthPage(manager.getDriver());
    }

    @When("the user navigates to the {string} page")
    public void userNavigatesTo(String pageName) {
        basicAuthPage = new BasicAuthPage(manager.getDriver());
        basicAuthPage.navigateToBasicAuth();
    }

    @Then("the user should see a success message")
    public void verifyLoginSuccess() {
        assertTrue(basicAuthPage.isLoginSuccessful());
    }
}
