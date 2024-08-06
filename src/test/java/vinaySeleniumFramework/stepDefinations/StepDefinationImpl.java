package vinaySeleniumFramework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vinaySelenium.Pageobject.CartPage;
import vinaySelenium.Pageobject.CheckOutPage;
import vinaySelenium.Pageobject.ConfirmationPage;
import vinaySelenium.Pageobject.landingPage;
import vinaySelenium.Pageobject.productCatalogues;
import vinaySeleniumLearning.TestComponent.BaseTest;

public class StepDefinationImpl extends BaseTest{
	
	public landingPage landingpage;
	public productCatalogues ProductCatalogues;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		ProductCatalogues = landingpage.loginApplication(username,password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		List<WebElement> products = ProductCatalogues.getProductList();
		ProductCatalogues.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = ProductCatalogues.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.SelectCountry("India");
		confirmationPage = checkOutPage.SubmitOrder();
	}
	
	
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_on_ConfirmationPage(String string)
	 {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		 driver.close();
	 }
	 
	 @Then("^\"([^\"]*)\" message is displayed$")
	 public void something_message_is_displayed(String strAgr1) throws Throwable
	 {
		 Assert.assertEquals(strAgr1, landingpage.getErrorMessage());
		 driver.close();
	 }
	
}
