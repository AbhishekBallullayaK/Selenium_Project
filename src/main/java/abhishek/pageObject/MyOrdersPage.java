package abhishek.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.abstartComponents.AbstractComponents;

public class MyOrdersPage extends AbstractComponents{

	WebDriver driver;
	
	public MyOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> myOrdersList;
	
//	@FindBy(css=".cartSection h3")
//	List<WebElement> myOrdersList;
	
	public boolean verifyProductOrdered(String productName) {
		boolean isOrderPresent = myOrdersList.stream().anyMatch(myOrders->
			myOrders.getText().equals(productName));
		return isOrderPresent;	
	}

}
