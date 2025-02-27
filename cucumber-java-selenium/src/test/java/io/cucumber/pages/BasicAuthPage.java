package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicAuthPage extends Page {

    private String basicAuthURL = "https://admin:admin@the-internet.herokuapp.com/basic_auth";

    // Locator for the success message
    private By successMessageLocator = By.xpath("//div[@class='example']/p");

    public BasicAuthPage(ChromeDriver driver) {
        super(driver);
    }


     // Navigates to the Basic Authentication page by passing credentials in the URL.

    public void navigateToBasicAuth() {
        driver.get(basicAuthURL);
    }


    // Checks if login was successful by verifying the presence of the success message.



    public boolean isLoginSuccessful() {
        WebElement messageElement = driver.findElement(successMessageLocator);
        return messageElement.getText().trim().equals("Congratulations! You must have the proper credentials.");
    }
}
