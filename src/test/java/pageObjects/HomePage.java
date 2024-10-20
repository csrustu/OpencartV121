package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// constructor
	public HomePage(WebDriver driver) {
		super(driver);  // calling the parent class (BasePage) driver variable
	}
	
	// Locators
	
		@FindBy(xpath = "//span[normalize-space()='My Account']")
		WebElement lnkMyAccount;
		
		@FindBy(xpath = "//a[normalize-space()='Register']")
		WebElement lnkRegister;
		
		@FindBy(linkText = "Login")  // Login link added as mentioned in step-5
		WebElement lnkLogin;
				
		// Action methods
		public void clickMyAccount() {
			lnkMyAccount.click();
		}
		
		public void clickRegister() {
			lnkRegister.click();
		}
		
		public void clickLogin() {
			lnkLogin.click();
		}
}