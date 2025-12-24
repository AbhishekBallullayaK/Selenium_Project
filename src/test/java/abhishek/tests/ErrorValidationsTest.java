package abhishek.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import abhishek.pageObject.ProductCartsPage;
import abhishek.pageObject.ProductsLandingPage;
import abhishek.testComponents.BaseTest;
import abhishek.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
	
	String username = "anujna@gmail.com";
	String password = "Aa@123456";
	String incorrectPassword = "Aa@1234567";
	String productName = "ZARA COAT 3";
	String countryName = "India";

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void verifyLoginErrorValidation() {
		loginPage.loginWebApplication(username, incorrectPassword);
		String errorMessage = loginPage.getIncorrectEmailOrPasswordErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");

		// div[@aria-label='Incorrect email or password.']
		// .ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	}

	@Test
	public void verifyProductAddedToCart() throws InterruptedException, IOException {

		

		ProductsLandingPage productsPage = loginPage.loginWebApplication("abhishek.k@gmail.com", "Aa@123456");

		productsPage.addProductToCart(productName);
		ProductCartsPage cartsPage = productsPage.navigateToCartsPage();

		boolean isProductFoundInCart = cartsPage.verifyProductAddedToCart("ZARA COAT 33");
		Assert.assertFalse(isProductFoundInCart);
	
	}
}
