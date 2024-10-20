package testCases;

import org.openqa.selenium.devtools.v127.profiler.model.ConsoleProfileFinished;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {	

	@Test (groups = {"Regression", "Master"})
	public void verify_account_registration() {
				
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer details");
		
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setTelephone(randomNumeric());
		
		String password = randomAlphaNumeric();
		
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		// validation of the confirmation message
		logger.info("Validating expected message");
		
		String confMsg = regPage.getConfirmationMsg();	
		Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Confirmation message mis-matched");
		logger.info("Test passed");
		} catch (Exception e) {
			logger.error("Test failed: "+ e.getMessage());
			Assert.fail("Test failed: "+ e.getMessage());
		}
		finally {
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
		/*
		if(confMsg.equals("Your Account Has Been Created!"))
			Assert.assertTrue(true);
		else {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		*/
//		Assert.assertEquals(congMsg, "Your Account Has Been Created!");
//		} catch (Exception e) {			
//			Assert.fail();
//		}		
	}	
}