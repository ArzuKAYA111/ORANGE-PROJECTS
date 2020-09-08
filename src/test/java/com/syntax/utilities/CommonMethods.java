package com.syntax.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.syntax.testBase.PageInitializer;

public class CommonMethods extends PageInitializer {
	
	/** 1- 
	 * 
	 * This method clears and sends text to text box.
	 * @param element
	 * @param text
	 */
public static void sendText( WebElement element , String text) {
	
	element.clear();
	element.sendKeys(text);//sendKeys a Selenium method
	
}
/** 2-
 * This method clicks on specific value of radiobutton/ Checkbox
 * @param element
 * @param expectedValue
 */
public static void clickRadiOrCheckbox(List<WebElement> element, String expectedValue) {
	
	String actualValu;
	for (WebElement el:element) {
		
		actualValu=el.getAttribute("Value").trim();
		if(actualValu.equals(expectedValue)) {
			el.click();
			break;
			
		}
	}	
}

/**
 * This method gives execution date of test
 * @return
 */
public static String getTimeStemp() {
	Date date= new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	return sdf.format(date.getTime());
}


/**
 * This method takes ScreenShot of test and copy it to a file which passed /failed
 * @param filename
 * @return
 */
public static String takeScreenShot(String filename) {
	// TakesScreenshot interface downcasted and created object
TakesScreenshot ts=(TakesScreenshot)driver;	

	File file= ts.getScreenshotAs(OutputType.FILE);
String destinationFile=Constants.SCREENSHOT_FILEPATH+ filename+ getTimeStemp()+ ".png";

try {
	FileUtils.copyFile(file, new File(destinationFile));
} catch (IOException e) {
	
	e.printStackTrace();
}
return destinationFile;	
}




	
	
	
	
	
}
