package vinaySelenium.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinaySelenium.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	@FindBy(css=".cartSection h3")
	private List <WebElement> cartProducts;


	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);

		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	

	
	public Boolean VerifyProductDisplay(String productName) 
	{
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckOutPage goToCheckout()
	{
		CheckoutEle.click();
		return new CheckOutPage(driver);
	}
}
