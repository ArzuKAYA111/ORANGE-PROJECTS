package com.syntax.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syntax.testBase.BaseClass;
import com.syntax.utilities.CommonMethods;
import com.syntax.utilities.ConfigsReader;

public class LoginPageElements extends CommonMethods{
@FindBy(id="txtUsername")
public WebElement username;
	
@FindBy(id="txtPassword")
public WebElement password;	

@FindBy(id="btnLogin")
public WebElement loginBtn;

@FindBy(id="spanMessage")
public WebElement loginErrMsg;




@FindBy(xpath="//a[@id='welcome']")
public WebElement welcome;	





public LoginPageElements(){

PageFactory.initElements(BaseClass.driver, this);// we call the driver from base class
}

public static void login() {
	sendText(login.username, ConfigsReader.getProperty("invalidusername"));
	sendText(login.password, ConfigsReader.getProperty("invalidpassword"));
	login.loginBtn.click();	
	
}

}
