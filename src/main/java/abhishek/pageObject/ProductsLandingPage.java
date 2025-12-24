package abhishek.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class ProductsLandingPage extends AbstractComponents{

	WebDriver driver;
	
	public ProductsLandingPage(WebDriver driver) {
		//Initialize webdriver object
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement LoadingAnnimator;
	
	By productsBy = By.cssSelector(".mb-3");
	By productAddToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement productToSelect = getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return productToSelect;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement productToAddToCart = getProductByName(productName);
		productToAddToCart.findElement(productAddToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(LoadingAnnimator);

	}
	
}
