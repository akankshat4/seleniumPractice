package com.practice.functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.practice.pages.LoginPagePOJO;

public class LoginPageFunctions {
	private LoginPagePOJO flipkartLoginPagePOJO;
	
	public LoginPageFunctions(EventFiringWebDriver driver) {
		flipkartLoginPagePOJO = new LoginPagePOJO(driver);
	}
	
	public void login(String userName, String password){
		flipkartLoginPagePOJO.setEmail(userName);
		flipkartLoginPagePOJO.setPassword(password);
		flipkartLoginPagePOJO.clickLoginButton();
	}
	
	public void cancelLogin(){
		flipkartLoginPagePOJO.clickCancelButton();
	}

}
