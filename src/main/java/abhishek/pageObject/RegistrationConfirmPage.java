// java
package abhishek.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class RegistrationConfirmPage extends AbstractComponents {

    WebDriver driver;

    public RegistrationConfirmPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".headcolor")
    WebElement confirmMessage;

    @FindBy(xpath = "//button[normalize-space()='Login' or normalize-space()='Login']")
    WebElement loginButtonOnConfirm;

    By messageBy = By.cssSelector(".headcolor");
    By loginButtonBy = By.xpath("//button[normalize-space()='Login' or normalize-space()='Login']");

    public String getConfirmMessage() {
        waitForElementToAppear(messageBy);
        return confirmMessage.getText();
    }

    public LoginPage clickLoginButton() {
        waitForElementToAppear(loginButtonBy);
        loginButtonOnConfirm.click();
        return new LoginPage(driver);
    }
}
