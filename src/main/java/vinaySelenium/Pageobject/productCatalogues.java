package vinaySelenium.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vinaySelenium.AbstractComponents.AbstractComponents;

public class productCatalogues extends AbstractComponents {
	WebDriver driver;

	public productCatalogues(WebDriver driver) { // constructor

		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> products= driver.findElements(By.cssSelector(".mb-3 "));

	// pagefactory

	@FindBy(css = ".mb-3 ")
	List<WebElement> products;

	@FindBy(css = ".ng-animating ")
	WebElement spining;

//not pagefactory
	By productsBy = By.cssSelector(".mb-3 ");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toasterMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementstoAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector(".card-body b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementstoAppear(toasterMessage);
		waitForElementstoDisappear(spining);
	}

}
