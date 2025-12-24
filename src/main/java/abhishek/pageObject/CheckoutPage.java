package abhishek.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="[placeholder=\"Select Country\"]")
	WebElement selectCountryField;
	
	@FindBy(xpath="(//button[contains(@class,\"ta-item\")])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	By countryList = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		
		action.sendKeys(selectCountryField, countryName).build().perform();

		waitForElementToAppear(countryList);
		
		selectCountry.click();
	}
	
	public OrderConfirmPage clickPlaceOrderButton() {
		placeOrderButton.click();
		OrderConfirmPage confirmPage = new OrderConfirmPage(driver);
		return confirmPage;
		
	}

	
}
