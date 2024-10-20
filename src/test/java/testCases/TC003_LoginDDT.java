package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"Datadriven"}) // getting DataProvider from different
																				// class
	public void verify_loginDDT(String email, String pwd, String exp_result) {

		logger.info("***** Starting TC003_LoginDDT *****");

		try {
			// Homepage
			logger.info("Navigate to Login page from Homepage");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login page
			logger.info("Log into the application");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// MyAccount page
			logger.info("Verify MyAccount page after Login");
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExists();

			// Validations
			/*
			 * 1) Data is valid ----> Login success --> Test Pass --> Logout Login failed
			 * ---> Test fail
			 * 
			 * 2) Data is invalid --> Login success --> Test fail --> Logout Login failed
			 * ---> Test pass
			 */

			logger.info("Validations");
			// 1) Data is valid
			if (exp_result.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					myAcc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp_result.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					myAcc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("***** Finished TC003_LoginDDT *****");
	}
}