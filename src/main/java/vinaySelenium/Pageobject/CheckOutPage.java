package vinaySelenium.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vinaySelenium.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementstoAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}

	public ConfirmationPage SubmitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
