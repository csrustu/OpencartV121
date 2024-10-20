package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_confirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chk_policy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_confirmation;
	
	// Action methods
	
		public void setFirstName(String fname) {
			txt_firstName.sendKeys(fname);
		}
		
		public void setLastName(String lname) {
			txt_lastName.sendKeys(lname);
		}
		
		public void setEmail(String email) {
			txt_email.sendKeys(email);
		}
		
		public void setTelephone(String tele) {
			txt_telephone.sendKeys(tele);
		}
		
		public void setPassword(String pwd) {
			txt_password.sendKeys(pwd);
		}
		
		public void setConfirmPassword(String pwd) {
			txt_confirmPassword.sendKeys(pwd);
		}
		
		public void setPrivacyPolicy() {
			chk_policy.click();
		}
		
		public void clickContinue() {
			// solution-1
			btn_continue.click();
			
			// solution-2
//			btn_continue.submit();
			
			// solution-3
//			Actions act = new Actions(driver);
//			act.moveToElement(btn_continue).click().perform();
			
			// solution-4
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].click();", btn_continue);
			
			// solution-5
//			btn_continue.sendKeys(Keys.RETURN);
			
			// solution-6
//			WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			myWait.until(ExpectedConditions.elementToBeClickable(btn_continue)).click();			
		}
		
		public String getConfirmationMsg() {
			try {
			return(msg_confirmation.getText());
			} catch (Exception e) {
				return(e.getMessage());
			}
		}
    }