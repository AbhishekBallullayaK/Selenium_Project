package abhishek.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhishek.pageObject.CheckoutPage;
import abhishek.pageObject.MyOrdersPage;
import abhishek.pageObject.OrderConfirmPage;
import abhishek.pageObject.ProductCartsPage;
import abhishek.pageObject.ProductsLandingPage;
import abhishek.testComponents.BaseTest;



public class OrderProductTest extends BaseTest{
	
	@Test(dataProvider="getData", groups= {"PlaceOrder"})
	public void orderProduct(HashMap<String, String> inputData) throws InterruptedException, IOException{
		
		//This will order a new Product
		ProductsLandingPage productsPage = loginPage.loginWebApplication(inputData.get("username"), inputData.get("password"));
		
		productsPage.addProductToCart(inputData.get("product"));
		ProductCartsPage cartsPage = productsPage.navigateToCartsPage();
		
		boolean isProductFoundInCart = cartsPage.verifyProductAddedToCart(inputData.get("product"));
		Assert.assertTrue(isProductFoundInCart);
		CheckoutPage checkoutPage = cartsPage.clickCheckOutButton();
		
		checkoutPage.selectCountry("India");
		
		OrderConfirmPage confirmPage = checkoutPage.clickPlaceOrderButton();
		
		String confirmMessage = confirmPage.getConfirmMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	@Test(dependsOnMethods= {"orderProduct"})
	public void verifyOrderHistory() throws InterruptedException {
		ProductsLandingPage productsPage = loginPage.loginWebApplication("abhishek.k@gmail.com", "Aa@123456");
		
		MyOrdersPage ordersPage = productsPage.navigateToOrdersPage();
		
		boolean orderMatch = ordersPage.verifyProductOrdered("ZARA COAT 3");
		Assert.assertTrue(orderMatch);
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\abhishek\\data\\placeOrderData.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> firstData = new HashMap<String, String>();
//		firstData.put("username", "abhishek.k@gmail.com");
//		firstData.put("password", "Aa@123456");
//		firstData.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> secondData = new HashMap<String, String>();
//		secondData.put("username", "anujna@gmail.com");
//		secondData.put("password", "Aa@123456");
//		secondData.put("product", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{firstData},
//							   {secondData}};
//	}
	
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"abhishek.k@gmail.com","Aa@123456","ZARA COAT 3"},
//							   {"anujna@gmail.com","Aa@123456","ADIDAS ORIGINAL"}};
//	}
}
