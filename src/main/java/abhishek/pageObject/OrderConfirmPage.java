package abhishek.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class OrderConfirmPage extends AbstractComponents{

	
	WebDriver driver;
	
	public OrderConfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	By message = By.cssSelector(".hero-primary");
	
	public String getConfirmMessage() {
		
		waitForElementToAppear(message);
		String msg = confirmMessage.getText();
		return msg;
	}
	

}
