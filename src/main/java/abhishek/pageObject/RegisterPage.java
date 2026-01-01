// java
package abhishek.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import abhishek.abstartComponents.AbstractComponents;

public class RegisterPage extends AbstractComponents {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(id = "userEmail")
    WebElement emailField;

    @FindBy(id = "userMobile")
    WebElement mobileField;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationSelect;

    @FindBy(css = "input[type='radio'][formcontrolname='gender']")
    List<WebElement> genderOptions;

    @FindBy(css = "input[formcontrolname='required']")
    WebElement termsCheckbox;

    @FindBy(id = "userPassword")
    WebElement passwordField;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordField;

    @FindBy(id = "login")
    WebElement registerButton;

    @FindBy(css = "a.text-reset")
    WebElement loginHereLink;

    By registerButtonBy = By.id("login");

    public void fillRegistrationForm(String firstName, String lastName, String email, String mobile,
                                     String occupation, String gender, String password, boolean acceptTerms) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        emailField.clear();
        emailField.sendKeys(email);

        mobileField.clear();
        mobileField.sendKeys(mobile);

        if (occupation != null && !occupation.isEmpty()) {
            new Select(occupationSelect).selectByVisibleText(occupation);
        }

        if (gender != null && !gender.isEmpty()) {
            for (WebElement opt : genderOptions) {
                String val = opt.getAttribute("value");
                if (val != null && val.equalsIgnoreCase(gender)) {
                    if (!opt.isSelected()) {
                        opt.click();
                    }
                    break;
                }
            }
        }

        passwordField.clear();
        passwordField.sendKeys(password);

        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);

        if (acceptTerms && !termsCheckbox.isSelected()) {
            termsCheckbox.click();
        } else if (!acceptTerms && termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }

    public RegistrationConfirmPage submitRegistration() {
        registerButton.click();
        return new RegistrationConfirmPage(driver);
    }

    public RegistrationConfirmPage registerUser(String firstName, String lastName, String email, String mobile,
                                                String occupation, String gender, String password) {
        fillRegistrationForm(firstName, lastName, email, mobile, occupation, gender, password, true);
        return submitRegistration();
    }

    public void clickLoginHere() {
        loginHereLink.click();
    }

    public void waitForRegisterButton() {
        waitForElementToAppear(registerButtonBy);
    }
}
