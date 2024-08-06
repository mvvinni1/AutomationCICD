package vinaySeleniumLearning.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import vinaySelenium.Pageobject.CartPage;
import vinaySelenium.Pageobject.CheckOutPage;
import vinaySelenium.Pageobject.ConfirmationPage;
import vinaySelenium.Pageobject.landingPage;
import vinaySelenium.Pageobject.productCatalogues;
import vinaySeleniumLearning.TestComponent.BaseTest;
import vinaySeleniumLearning.TestComponent.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" },retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		landingpage.loginApplication("vinay@rahulshettyacademys.com", "Vinay@rahul2024");

		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		productCatalogues ProductCatalogues = landingpage.loginApplication("vinay1@rahulshettyacademy.com",
				"Tester@123");
		List<WebElement> products = ProductCatalogues.getProductList();
		ProductCatalogues.addProductToCart(productName);
		CartPage cartPage = ProductCatalogues.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

	}

}
