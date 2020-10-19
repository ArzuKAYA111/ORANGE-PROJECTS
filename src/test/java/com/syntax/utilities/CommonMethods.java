package com.syntax.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.syntax.testBase.PageInitializer;

public class CommonMethods extends PageInitializer {
	// TO SEE ALL METHODS CNTRL + O

	/**  1- 
	 * This method gives execution date of the test
	 * 
	 * @return
	 */
	public static String getTimeStemp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}

	/** 2-
	 *  This method takes ScreenShot of test and copy it to a file which passed
	 * /failed
	 * 
	 * @param filename
	 * @return
	 */
	public static String takeScreenShot(String filename) {

		// To add execution date screenshot
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String getTimeStemp = sdf.format(date.getTime());

		// TakesScreenshot interface downcasted and created object
		TakesScreenshot ts = (TakesScreenshot) driver;

		File file = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = Constants.SCREENSHOT_FILEPATH + filename + getTimeStemp + ".png";

		try {
			FileUtils.copyFile(file, new File(destinationFile));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return destinationFile;
	}

	/** 3- 
	 * - This method clicks on specific value of radiobutton/ Checkbox
	 * 
	 * @param element
	 * @param expectedValue
	 */
	public static void clickRadiOrCheckbox(List<WebElement> element, String expectedValue) {

		String actualValu;
		for (WebElement el : element) {

			actualValu = el.getAttribute("Value").trim();
			if (actualValu.equals(expectedValue)) {
				el.click();
				break;

			}
		}

	}

	/** 4-
	 * This method clears and sends text to text box.
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {

		element.clear();
		element.sendKeys(text);// sendKeys a Selenium method

	}
	
	/**  Select Drop Down
	 * 1- by Visible Text
	 * 2- by Index
	 * 3- by Value
	 */
	
	/** 5-
	 * This method checks drops down if specific text is there it selecs that text
	 * 
	 * @param element
	 * @param textToSelect
	 */
	public static void selectDdValue(WebElement element, String textToSelect) {
		try {

			Select select = new Select(element);

			List<WebElement> options = select.getOptions();
			for (WebElement el : options) {
				if (el.getText().equals(textToSelect)) {
					;
					select.selectByValue(textToSelect);
					break;
				}
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}

	/** 6-
	 * Method that check Drop down if a specific index is there and then select that
	 * index
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDdValue(WebElement element, int index) {
		try {// To eliminate exception we used try catch block We wrote our logic in try
				// catch block

			Select select = new Select(element);

			int size = select.getOptions().size();

			if (size > index) {
				select.selectByIndex(index);
			}

		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}

	/** 7-
	 * Method that check Drop down Select Drop Down By value of the Value attribute
	 * 
	 * @param element
	 * @param value
	 */
	public static void selectDdByValue(WebElement element, String value) {// value is the value of Value attribute in
																			// the DOM
		try {
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			for (WebElement el : options) {
				if (el.getText().equals(value)) {
					select.selectByValue("value");
					break;
				} else {
					System.err.println(value + " is NOT selected");
				}
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}
	
	// Handling simple alert/PopUps
	// Handling confirmation Alert
	// Handling Prompt alerts/confirmation alerts by providing some confirmation
			// message
	
	/** 8-
	 * methods that accept alerts and catches exception if alert is not present
	 * click on OK
	 */
	public static void acceptAlert() {// for simple alert

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
/** 9-
 * methods that dismiss alerts and catches exception if alert is not present 
 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
/** 10- 
 *  methods that get text of alert and catches exception if alert is not
	 present
 * @return
 */
	public static String getAlertTex() {
		String alertText = null;
		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		} catch (NoAlertPresentException e) {
			e.getStackTrace();
		}
		return alertText;
	
	}
	/** 11-
	 * methods that get text of alert and catches exception if alert is not
	 * present
	 * @param text
	 */
public static void SendAlertText(String text) {
	try {
		Alert alert=driver.switchTo().alert();
		alert.sendKeys(text);

	}catch(NoAlertPresentException e) {
		e.printStackTrace();
	}
	
}

/**Switch To Frame 
 * 1- by name Or id
 * 2- by element
 * 3- by index
 */ 

/** 12-
 * This method is used to switch to a Frame by Name Or Id
 * @param NameOrId
 */
 public static void switchToFrame(String NameOrId) {
	try {
		
		driver.switchTo().frame( NameOrId );
		
	}catch(NoSuchFrameException e) {
		e.printStackTrace();
	} 
 }
 
/** 13- 
 * Method that is used to switch to a Frame by WebElement 
 * @param element
 */
public static void switchToFrame(WebElement element) {
try {
	
	driver.switchTo().frame(element);
	
}catch(NoSuchFrameException e) {
	e.printStackTrace();
    }
}

/** 14-
 * 	Method that is used to switch to a Frame by index
 * @param index
 */
public static void switchToFrame(int index)	{
	
try {
	
	driver.switchTo().frame(index);
	
}catch(NoSuchFrameException e) {
	e.printStackTrace();
   }		
}

/** 15-
 * This method is used to switch to child window 
 */
public static void switchToChildWindow() {
	String mainWindow= driver.getWindowHandle();
	Set<String> windows =driver.getWindowHandles();
	
	for(String window :windows) {
	if(!window.equals(mainWindow)) {
	
		driver.switchTo().window(window);
		break;
		
	}
		
 }
}
/** 16-
 * Exolicit wait
 * Thid method is used for FindBy() method to wait element loading in DOM
 * @return
 */
public static WebDriverWait getWaitObject() {
	
	WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
	
	return wait;
}

/** 17-
 * 	This method is used to wait element to click
 * @param element
 * @return
 */
public static WebElement WaitForClickability(WebElement element ) {

 return getWaitObject().until(ExpectedConditions.elementToBeClickable(element ));	
 
}

/**18-
 * Wait for element to visibility
 * 
 * @param element
 * @return
 */
public static WebElement waitForVisibilityOfElement(WebElement element) {

	return getWaitObject().until(ExpectedConditions.visibilityOf(element));

}

/** 19-
 * This method is used to click element 
 * @param element
 */
public static void click(WebElement element) {
	getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	WaitForClickability(element);
	element.click();
}

/** 20-
 * This method provides sj object
 * @return js
 */
public static JavascriptExecutor getJSObject() {
	
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	return js;
}

/** 21-
 * This methods is used click
 * @param element
 */
public static void jsClick(WebElement element ) {
	getJSObject().executeScript("arguments[0].click();", element);
			
}

/** 22-
 * This method is used to  scroll 
 * @param element
 */
public static void scrollToElement(WebElement element) {
	getJSObject().executeScript("arguments[0].scrollIntoView(true);", element );
		
}

/** 23-
 * Method that will scroll the page down based on the passed pixel parameters
 * 
 * @param pixel
 */
public static void scrollDown(int pixel) {
	getJSObject().executeScript("window.scrollBy(0," + pixel + ")");
}

/** 24-
 * Method that will scroll the page up based on the passed pixel parameters
 * 
 * @param pixel
 */
public static void scrollUp(int pixel) {
	getJSObject().executeScript("window.scrollBy(0,-" + pixel + ")");

}

/**25
 * method wait for specific seconds
 * 
 * @param second
 * @throws InterruptedException
 */
public static void wait(int second) {
	try {
		Thread.sleep(second*1000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
}



	
}
