package com.syntax.testBase;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.syntax.utilities.ConfigsReader;
import com.syntax.utilities.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports report;
	public static ExtentTest test;
	
	
@BeforeMethod(alwaysRun=true)
/**
 * The ExtentHtmlReporter creates a rich standalone HTML file. It allows several
 * configuration options via the <code>config()</code> method.
 */

/**
 * This method generates an HTML report
 */
public void  GenerateReport() {
	
	//extendReporter class' Object
	htmlReport=new 	ExtentHtmlReporter(Constants.REPORT_FILEPATH);
	
	htmlReport.config().setDocumentTitle(ConfigsReader.getProperty("reportTitle"));
	htmlReport.config().setReportName(ConfigsReader.getProperty("reportName"));
	htmlReport.config().setTheme(Theme.DARK);
	
	
	
	report=new ExtentReports();
	report.attachReporter(htmlReport);
}

/**
 * this method write the report after test copleted
 */
@AfterTest(alwaysRun=true)
public void writeReport() {
	report.flush();
	
}

/**
 * this method initialize the browsers and navigate to url
 * @return
 */
	public static WebDriver setUp() {

		System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "true");
		 String headless=ConfigsReader.getProperty("headless"); // we add ' headless =true ' in config
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		switch (ConfigsReader.getProperty("browser").toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions cOptions=new ChromeOptions();
			if(headless.equalsIgnoreCase("true")){        //   we add this cOption parts for using Jenkins 
				cOptions.setHeadless(true);
				driver=new ChromeDriver(cOptions);
				
				}else{                      
					driver=new ChromeDriver(cOptions);// bunu buraya koyduktan sonra browser acilmadan code calisiyor test yaparken 
				                                     // This is ChromeBrowser Headless mode 
					                                 // we can write like cOptions codes for other repositories instead of ChromeOptions cOptions=new ChromeOptions(); we will use 
		}                                           //FirefoxOptions fOptions=new FirefoxOptions(); sonradan tamamla
			
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
			default:
				throw new RuntimeException("Browser is not supported");// we create explicit exception if code gets a problem about browseer this method throws exception
		}
driver.manage().window().maximize(); // we manage the driver to make page full screen
driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME,TimeUnit.SECONDS);//this code provides wait to findElemet method to load element 
driver.get(ConfigsReader.getProperty("url"));	//this code navigate to specified url (specified url is in the configuration filr)
		
		return driver;

	}


/**
 * this method close all windows/browser
 */
	@AfterMethod(alwaysRun=true)
	public static void tearDown() {// To close page

		if (driver != null) {
			driver.quit();// close all tabs/windows after test copleted
		}
	}

}
