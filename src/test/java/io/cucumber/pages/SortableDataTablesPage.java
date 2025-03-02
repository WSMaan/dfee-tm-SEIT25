package io.cucumber.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SortableDataTablesPage extends Page {
    private By sortableTableLink = By.linkText("Sortable Data Tables");

    public SortableDataTablesPage(ChromeDriver driver) {
        super(driver);
    }

    public DataTablesPage clickSortedDataTables() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement sortableTablesLink = wait.until(ExpectedConditions.elementToBeClickable(sortableTableLink));
        sortableTablesLink.click();

        // Navigate to Data Tables page
        return new DataTablesPage(driver);
    }
}
