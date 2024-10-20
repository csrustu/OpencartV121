package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
		 * new Date(); String currentdatetimestamp = df.format(dt);
		 */
		// or
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp

		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify the location of report

		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of the Report
		sparkReporter.config().setReportName("Opencart Functional Testing"); // name of the Report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-module", "Customers");
		extent.setSystemInfo("User name", System.getProperty("user.name")); // to get the current system user name
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty())
			extent.setSystemInfo("Groups", includedGroups.toString()); // adds groups names to the report
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
			extent.flush();
			
			// to open the report automatically
			String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());  // opens the report on browser automatically
			} catch (IOException e) {
				e.printStackTrace();
			}		
					
		/*	try {
			  URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
						
			  // Create email message
				   ImageHtmlEmail email = new ImageHtmlEmail();
				   email.setDataSourceResolver(new setDataSourceUrlResolver(url));
				   email.setHostName("smtp.googlemail.com");
				   email.setSmtpPort(465);
				   email.setAuthenticator(new DefaultAuthenticator("csk@gmail.com","test@123"));
				   email.setSSLOnConnect(true);
				   email.setFrom("csk@gmail.com");  // sender
				   email.setSubject("Test Results");
				   email.setMsg("please find Attached Report...");
				   email.addTo("sck@gmail.com");  // Receiver
				   email.attach(url, "extent report", "Please check report...");
				   email.send();  // send the email
					} catch (Exception e) {
						e.printStackTrace();
					} */
	     }	
   }