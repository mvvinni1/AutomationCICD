package vinaySeleniumLearning.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vinaySelenium.Pageobject.CartPage;
import vinaySelenium.Pageobject.CheckOutPage;
import vinaySelenium.Pageobject.ConfirmationPage;
import vinaySelenium.Pageobject.OrderPage;
import vinaySelenium.Pageobject.landingPage;
import vinaySelenium.Pageobject.productCatalogues;
import vinaySeleniumLearning.TestComponent.BaseTest;

public class SubmitOrdertest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		productCatalogues ProductCatalogues = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = ProductCatalogues.getProductList();
		ProductCatalogues.addProductToCart(input.get("productName"));
		CartPage cartPage = ProductCatalogues.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.SelectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.SubmitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// Zara Coat 3
		productCatalogues ProductCatalogues = landingpage.loginApplication("vinay@rahulshettyacademy.com",
				"Vinay@rahul2024");
		OrderPage orderspage = ProductCatalogues.goToOrderPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
	}

	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//vinaySeleniumLearing//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	// @DataProvider
	// public Object[][] getData()
	// {
	// return new Object[][]
	// {{"vinay@rahulshettyacademy.com","Vinay@rahul2024","ZARA COAT
	// 3"},{"vinay1@rahulshettyacademy.com","Tester@123","ADIDAS ORIGINAL"}};
	// }
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "vinay@rahulshettyacademy.com");
//	map.put("password", "Vinay@rahul2024");
//	map.put("productName", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "vinay1@rahulshettyacademy.com");
//	map1.put("password", "Tester@123");
//	map1.put("productName", "ADIDAS ORIGINAL");

}
