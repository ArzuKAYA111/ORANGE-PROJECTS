package com.syntax.testBase;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.syntax.utilities.CommonMethods;

public class Listener implements ITestListener {
	
	/**
	 * This method start to create report and log out the Test Started
	 */
@Override	
public void onTestStart(ITestResult result)	{// this listener will execute when @Testmethod start
		
		System.out.println("Test Started " + result.getName());
		BaseClass.test=BaseClass.report.createTest(result.getName());
		
	}
/**
 * This method takes ScreenShot which passed test  and log out the Test Passed
 */
@Override
public void onTestSuccess(ITestResult result) {
	
	System.out.println("Test Passed " + result.getName());
	BaseClass.test.pass("Test Case pass "+ result.getName());
	
	try {
		BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenShot("passed/" +result.getName()));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}
/**
 *  This method takes ScreenShot which failed test  and log out the Test Failed
 */
@Override
public void onTestFailure(ITestResult result) {
	System.out.println("Test Failed " + result.getName());	
	BaseClass.test.fail("Test Case Failed "+ result.getName());
	 try {
		BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenShot("failed/"+ result.getName()));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}




}
