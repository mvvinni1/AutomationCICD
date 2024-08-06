package vinaySelenium.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinaySelenium.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List <WebElement> productNames;


	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);

		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	

	
	public Boolean VerifyOrderDisplay(String productName) 
	{
		Boolean match = productNames.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
}
