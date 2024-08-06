package vinaySelenium.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinaySelenium.AbstractComponents.AbstractComponents;

public class landingPage extends AbstractComponents{
	WebDriver driver;

	public landingPage(WebDriver driver) { // constructor

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	

	// pagefactory

	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	//.ng-tns-c4-11.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	@FindBy(id="login")
	WebElement submit;

	// Action code
	public productCatalogues loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		productCatalogues ProductCatalogues = new productCatalogues(driver);
		return ProductCatalogues;

	}
	
	public String getErrorMessage()
	{
		waitForWebElementstoAppear(errorMessage);
		return errorMessage.getText();
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
