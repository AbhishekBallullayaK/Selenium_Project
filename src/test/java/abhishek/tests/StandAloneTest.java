package abhishek.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		
		String username = "abhishek.k@gmail.com";
		String password = "Aa@123456";
		String productName = "ZARA COAT 3";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
				
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));	
		
		WebElement productToSelect = products.stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null) ;
		
		productToSelect.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()=\"My Cart\"]")));
		
		List<WebElement> productsInCart = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean productPresentInCart = productsInCart.stream().anyMatch(productInCart->productInCart.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(productPresentInCart);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions action = new Actions(driver);
		
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,\"ta-item\")])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
	}
		
}
