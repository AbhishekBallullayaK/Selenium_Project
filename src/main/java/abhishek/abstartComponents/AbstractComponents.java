package abhishek.abstartComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhishek.pageObject.MyOrdersPage;
import abhishek.pageObject.ProductCartsPage;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));


	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));


	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));

	}
	
	public ProductCartsPage navigateToCartsPage() {
		cartButton.click();
		ProductCartsPage cartsPage = new ProductCartsPage(driver);
		return cartsPage;
	}
	
	public MyOrdersPage navigateToOrdersPage() {
		ordersButton.click();
		MyOrdersPage ordersPage = new MyOrdersPage(driver);
		return ordersPage;
	}
	 
}
