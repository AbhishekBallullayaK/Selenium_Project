package abhishek.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class ProductCartsPage extends AbstractComponents{

	WebDriver driver;
	
	public ProductCartsPage(WebDriver driver) {
		//Initialize webdriver object
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".cartSection h3")
	List<WebElement> productsInCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	public boolean verifyProductAddedToCart(String productName) {
		boolean isProductPresentInCart = productsInCart.stream().anyMatch(productInCart->
			productInCart.getText().equalsIgnoreCase(productName));
		return isProductPresentInCart;	
	}
	
	public CheckoutPage clickCheckOutButton() {
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
}
