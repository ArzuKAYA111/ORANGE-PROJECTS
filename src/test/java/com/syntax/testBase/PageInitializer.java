package com.syntax.testBase;

import com.syntax.pages.DashBoardPageElements;
import com.syntax.pages.LoginPageElements;

public class PageInitializer extends BaseClass {

public static LoginPageElements login;
public static DashBoardPageElements dashBoard;



public static void initialize() {
	login = new LoginPageElements();
	dashBoard=new DashBoardPageElements();
	
	
	
	
}


}
