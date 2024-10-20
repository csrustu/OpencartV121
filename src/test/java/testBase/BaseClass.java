package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.internal.LogManagerStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;  // for Log4j
import org.apache.logging.log4j.LogManager;  // for Log4j

public class BaseClass {	
	
	public static WebDriver driver;
	public Logger logger;  // for log4j
	public Properties prop;
	
	@BeforeClass (groups = {"Sanity", "Regression", "Master"})
	@Parameters ({"os","browser"})
	public void setUp(String os, String br) throws IOException {
		
	// loading config.properties file
		FileReader file = new FileReader("./src//test//resources//Config.properties");
		prop = new Properties();
		prop.load(file);
		
		logger=LogManager.getLogger(this.getClass());  // loads log4j2.xml file (fetches from resources folder)
		
		if(prop.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// OS (if the execution environment is remote, then take the OS details from xml)
				if(os.equalsIgnoreCase("windows"))
					capabilities.setPlatform(Platform.WIN11);
				else if(os.equalsIgnoreCase("linux"))
					capabilities.setPlatform(Platform.LINUX);
				else if (os.equalsIgnoreCase("mac"))
					capabilities.setPlatform(Platform.MAC);
				else {
					System.out.println("No matching OS found");
					return;
				}
				
			// Browser (if the execution environment is remote, then take the browser details from xml)
				switch (br.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome");  break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;		
				case "firefox": capabilities.setBrowserName("firefox"); break;	
				default: System.out.println("No matching browser found"); return;
				}			
				
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			}
				if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
		
						switch(br.toLowerCase()) {
						case "chrome": driver = new ChromeDriver(); break;
						case "edge": driver = new EdgeDriver(); break;
						case "firefox": driver = new FirefoxDriver(); break;
						default: System.out.println("Invalid browser name"); return;
						}
				}
		
						driver.manage().deleteAllCookies();
						driver.manage().window().maximize();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						driver.get(prop.getProperty("appURL"));  // reading url from properties file
		}
	
	@AfterClass (groups = {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		 String generatedString = RandomStringUtils.randomAlphabetic(6);
		 return generatedString;
	}
	
	public String randomNumeric() {
		 String generatedNumber = RandomStringUtils.randomNumeric(10);
		 return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		 String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(6);
		 return generatedAlphaNumeric;
	}
	
	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;				
	}
}