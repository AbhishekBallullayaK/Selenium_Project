package abhishek.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		//Initialize webdriver object
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="userEmail")
	WebElement emailField;
	
	@FindBy(id="userPassword")
	WebElement passwordField;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement incorrectEmailOrPasswordError;
	
	public void startWebApplication() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductsLandingPage loginWebApplication(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		ProductsLandingPage productsPage = new ProductsLandingPage(driver);
		return productsPage;

	}
	
	public String getIncorrectEmailOrPasswordErrorMessage() {
		waitForElementToAppear(incorrectEmailOrPasswordError);
		String errorMessage =  incorrectEmailOrPasswordError.getText();
		return errorMessage;
		
	}
}
