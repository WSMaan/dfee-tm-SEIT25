package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Home extends Page {
    private static final Logger logger = LogManager.getLogger(Home.class);

    public Home(ChromeDriver driver) {
        super(driver);
       
    }

    @FindBy(css = "h1")
    private WebElement title;

    public WebElement getTitle() {
        return title;
    }

    public void refresh() {
        driver.navigate().refresh();
       
    }


}
