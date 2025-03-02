package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.DataTablesPage;
import io.cucumber.pages.SortableDataTablesPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortableDataTablesSteps extends Context {
    private SortableDataTablesPage sortableDataTablesPage;
    private DataTablesPage dataTablesPage;

    public SortableDataTablesSteps(Manager manager) {
        super(manager);
        this.sortableDataTablesPage = new SortableDataTablesPage(manager.getDriver());
    }

    @When("the user clicks on the {string} link")
    public void userClicksOnLink(String linkText) {
        dataTablesPage = sortableDataTablesPage.clickSortedDataTables();
    }

    @When("the user extracts data from the {string} table")
    public void userExtractsTableData(String tableName) {
        dataTablesPage.extractTableData();
    }

    @Then("the extracted table data should match the expected entries:")
    public void verifyExtractedTableData(io.cucumber.datatable.DataTable expectedData) {
        assertTrue(dataTablesPage.verifyTableContainsExpectedEntries(expectedData.asMaps(String.class, String.class)),
                "Expected entries were not found in the table!");
    }
}
