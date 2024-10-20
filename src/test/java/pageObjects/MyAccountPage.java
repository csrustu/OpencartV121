package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	// constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	// locators
	@FindBy(xpath = "//h2[normalize-space()='My Account']")  // My Account page heading
	WebElement msgHeading;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")  // added as mentioned in step-6
	WebElement lnk_logout;
	
	
	// action methods
	public boolean isMyAccountPageExists() {
		try {
		return (msgHeading.isDisplayed());
		}catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnk_logout.click();
	}
}