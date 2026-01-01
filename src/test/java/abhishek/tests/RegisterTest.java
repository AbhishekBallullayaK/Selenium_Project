// java
package abhishek.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import abhishek.pageObject.RegisterPage;
import abhishek.pageObject.RegistrationConfirmPage;
import abhishek.pageObject.LoginPage;
import abhishek.testComponents.BaseTest;

public class RegisterTest extends BaseTest {

    @Test(groups = {"RegisterUser"})
    public void registerAndNavigateToLogin() throws InterruptedException {
        // Click a Register link/button on the login page (robust XPath that targets common Register text)
        driver.findElement(By.xpath("//a[normalize-space()='Register' or contains(normalize-space(.),'Register')]")).click();

        RegisterPage registerPage = new RegisterPage(driver);

        // prepare unique data to avoid duplicate account issues
        String uniqueEmail = "user" + System.currentTimeMillis() + "@example.com";
        String firstName = "John";
        String lastName = "Doe";
        String mobile = "9999999999";
        String occupation = "Student"; // must match visible option text
        String gender = "Male";
        String password = "Aa@123456";

        // Fill form and submit -> returns confirmation page
        RegistrationConfirmPage confirmPage = registerPage.registerUser(firstName, lastName, uniqueEmail, mobile, occupation, gender, password);

        // Verify confirmation message
        String confirmMsg = confirmPage.getConfirmMessage();
        Assert.assertTrue(confirmMsg != null && confirmMsg.trim().length() > 0, "Confirmation message should be present");
        Assert.assertTrue(confirmMsg.toLowerCase().contains("account created successfully") || confirmMsg.toLowerCase().contains("account created"), "Unexpected confirmation message: " + confirmMsg);

        // Click Login on confirmation popup and verify navigation to LoginPage
        LoginPage returnedLoginPage = confirmPage.clickLoginButton();
        Assert.assertNotNull(returnedLoginPage, "Clicking Login on confirmation should return a LoginPage instance");
    }
}
