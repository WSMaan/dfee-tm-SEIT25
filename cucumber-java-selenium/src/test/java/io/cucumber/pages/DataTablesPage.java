package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class DataTablesPage extends Page {
    private By example1Table = By.id("table1");

    public DataTablesPage(ChromeDriver driver) {
        super(driver);
    }

    public List<Map<String, String>> extractTableData() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<Map<String, String>> tableData = new ArrayList<>();

        try {
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(example1Table));
            List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                if (cells.size() >= 5) { // Only extract data if 5 columns exist
                    Map<String, String> rowData = new HashMap<>();
                    rowData.put("Last Name", cells.get(0).getText().trim());
                    rowData.put("First Name", cells.get(1).getText().trim());
                    rowData.put("Email", cells.get(2).getText().trim());
                    rowData.put("Due", cells.get(3).getText().trim());
                    rowData.put("Web Site", cells.get(4).getText().trim());

                    tableData.add(rowData);
                }
            }
        } catch (Exception e) {
            System.out.println("Error extracting table data: " + e.getMessage());
        }

        return tableData;
    }

    public boolean verifyTableContainsExpectedEntries(List<Map<String, String>> expectedEntries) {
        List<Map<String, String>> actualData = extractTableData();

        for (Map<String, String> expectedRow : expectedEntries) {
            if (!actualData.contains(expectedRow)) {
                System.out.println(" Expected entry missing: " + expectedRow);
                return false;
            }
        }

        System.out.println(" All expected table entries are present.");
        return true;
    }
}
