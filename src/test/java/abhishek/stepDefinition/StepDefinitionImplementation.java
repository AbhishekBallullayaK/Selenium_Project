package abhishek.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import abhishek.pageObject.CheckoutPage;
import abhishek.pageObject.LoginPage;
import abhishek.pageObject.OrderConfirmPage;
import abhishek.pageObject.ProductCartsPage;
import abhishek.pageObject.ProductsLandingPage;
import abhishek.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{

	public LoginPage loginPage;
	public ProductsLandingPage productsPage;
	public ProductCartsPage cartsPage;
	public CheckoutPage checkoutPage;
	public OrderConfirmPage confirmPage;
	
	@Given("Land on the Ecommerce website")
	public void land_on_the_ecommerce_website() throws IOException {
		loginPage = launchWebApplication();
	}
	
	@Given("^Login with email (.+) and password (.+)$")
	public void login_with_email_and_password(String email, String password) {
		productsPage = loginPage.loginWebApplication(email, password);
	}
	
	@When("^Add product (.+) to the Cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		productsPage.addProductToCart(productName);
	}
	
	@When("^Checkout the product (.+) and place the order$")
	public void checkout_product_and_place_order(String productName) {
		cartsPage = productsPage.navigateToCartsPage();

		boolean isProductFoundInCart = cartsPage.verifyProductAddedToCart(productName);
		Assert.assertTrue(isProductFoundInCart);
		checkoutPage = cartsPage.clickCheckOutButton();
		
		checkoutPage.selectCountry("India");
		
		confirmPage = checkoutPage.clickPlaceOrderButton();
	}
	
	@Then("Verify the success message {string}")
	public void verify_success_message(String string) {
		String confirmMessage = confirmPage.getConfirmMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();	
	}
	
	@Then("Verify the error message {string}")
	public void verify_error_message(String string) {
		String errorMessage = loginPage.getIncorrectEmailOrPasswordErrorMessage();
		Assert.assertEquals(errorMessage, string);
		driver.close();

	}


}
