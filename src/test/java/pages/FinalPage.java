package pages;

import driver.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalPage extends BasePage {

    private final By successMsg = By.xpath("//span[contains(.,'Спасибо за обращение')]");

    public FinalPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccess() {
        return waitVisible(successMsg).isDisplayed();
    }
}