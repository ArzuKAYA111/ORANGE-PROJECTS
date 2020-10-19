package com.syntax.TestCases;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.syntax.utilities.CommonMethods;
import com.syntax.utilities.ConfigsReader;

public class LoginHRMS extends CommonMethods {
	
@Test(enabled=true)
public void loginValidCredentials() {// Login with valid admin credentials
	
	test.info("Entered valid admin credantials to login HRMS and navigated to dashboared page");
	
	sendText(login.username, ConfigsReader.getProperty("username"));
	sendText(login.password, ConfigsReader.getProperty("password"));
	login.loginBtn.click();
	
	test.info("Login validation is done ");
	
	String actualtext=dashBoard.welcome.getText();
	String expectedtext="Welcome Admin";

	SoftAssert asrt=new SoftAssert();
	asrt.assertEquals(actualtext, expectedtext, "Text did not match");
	asrt.assertAll();
}

@Test(enabled=true)
public void loginInvalidCredentials() {// Login with invalid admin credentials
	
	test.info("Entered invalid admin credantials");
	
	sendText(login.username, ConfigsReader.getProperty("invalidusername"));
	sendText(login.password, ConfigsReader.getProperty("invaidpassword"));
	login.loginBtn.click();
	
	    String actualErrMsg=login.loginErrMsg.getText();
		String expectedErrMsg="Invalid credentials";
        System.out.println(actualErrMsg);

		SoftAssert asrt=new SoftAssert();
		asrt.assertEquals(actualErrMsg, expectedErrMsg, "Text did not match");
		test.info("User could not login and saw error message ");
		asrt.assertAll();
}

@Test()
public void LoginMissingUsername() {
	
	test.info("Just valid password entered not username ");
	sendText(login.password, ConfigsReader.getProperty("password"));
	login.loginBtn.click();
	
	    String actualErrMsg=login.loginErrMsg.getText();
	    
	    System.out.println(actualErrMsg);
		String expectedErrMsg="Username cannot be empty";
		SoftAssert asrt=new SoftAssert();
		asrt.assertEquals(actualErrMsg, expectedErrMsg, "Text did not match");
		test.info("User could not login and saw error message ");
		
		asrt.assertAll();
}

@Test(enabled=true)
public void LoginMissingPassword() {
	
	test.info("Just valid username entered not password ");
	sendText(login.username, ConfigsReader.getProperty("username"));
	login.loginBtn.click();
	
	String actualErrMsg=login.loginErrMsg.getText();
	
		String actualtext=login.loginErrMsg.getText();
		String expectedtext="Password cannot be empty";

		SoftAssert asrt=new SoftAssert();
		asrt.assertEquals(actualtext, expectedtext, "Text did not match");
	
		test.info("User could not login and saw error message ");
		
		asrt.assertAll();
}

}
