package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Given;

public class Navigation extends Context {

    public Navigation(Manager manager) {
        super(manager);
    }

    @Given("^the page under test is '(.+)'$")
    public void navToPage(String url) {
        manager.getDriver().get(url);
        stash("pageUrl", url);
    }
}