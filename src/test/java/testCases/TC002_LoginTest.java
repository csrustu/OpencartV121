package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test (groups = {"Sanity","Master"})
	public void verify_login() {
		logger.info("***** Starting TC002_Login *****");
		
		try {
		// Homepage
		logger.info("Navigate to Login page from Homepage");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login page
		logger.info("Log into the application");
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));  // passing email from properties file
		lp.setPassword(prop.getProperty("password"));  // passing password from properties file
		lp.clickLogin();
		
		// MyAccount page
		logger.info("Verify MyAccount page after Login");
			MyAccountPage myAcc = new MyAccountPage(driver);
			
			boolean targetPage = myAcc.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);  // (or) Assert.assertEquals(targetPage, true, "Login failed");
		} catch (Exception e) {
			Assert.fail();
		}
			
			logger.info("***** Finished TC002_Login *****");
		
	}
	

}
