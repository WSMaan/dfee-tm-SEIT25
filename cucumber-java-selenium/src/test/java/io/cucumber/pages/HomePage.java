package io.cucumber.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage extends Page {
    private static final Logger logger = LogManager.getLogger(HomePage.class);


    // Locators
    private final By listItemsLocator = By.xpath("//*[@id=\"content\"]/ul/li");

    public HomePage(ChromeDriver driver) {
        super(driver);
    }

    public List<WebElement> getListItems() {
        return driver.findElements(listItemsLocator);
    }


    //  Extracts  text from list items on the homepage
     // @return List of cleaned link texts

    public List<String> getCleanedLinks() {
        List<WebElement> listItems = getListItems();
        List<String> cleanedLinks = new ArrayList<>();

        for (WebElement listItem : listItems) {
            String itemText = listItem.getText();

            // Check if the item text is not null or empty
            if (itemText != null && !itemText.trim().isEmpty()) {
                // Clean the item text: remove leading numbers and trim whitespace
                String cleanedLink = itemText.replaceAll("^\\d+\\s*", "").trim();

                // Add non-empty links to the list
                if (!cleanedLink.isEmpty()) {
                    cleanedLinks.add(cleanedLink);
                }
            }
        }

        return cleanedLinks;
    }

    public void verifyLinks() throws IOException {
        // Read the expected list from the file
        List<String> expectedLinks = Files.readAllLines(Paths.get("src/test/resources/expected_links.txt"));

        // Get actual links
        List<String> actualLinks = getCleanedLinks();

        // Identify missing links
        List<String> missingLinks = expectedLinks.stream()
                .filter(link -> !actualLinks.contains(link))
                .toList();

        //  Log  if the test fails
        if (!missingLinks.isEmpty()) {
            logger.error(" Test Failed! Missing Links: {}", missingLinks);
            logger.error(" Actual Links Found: {}", actualLinks);
            logger.error(" Expected Links: {}", expectedLinks);

            Assert.fail("Homepage is missing expected links: " + missingLinks);
        }
    }
}