package com.practice.functions;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.practice.pages.HomePagePOJO;

public class HomePageFunctions {
		//private UserHomePagePOJO userHomePagePOJO;
		private HomePagePOJO homePagePOJO;
		
		public HomePageFunctions(EventFiringWebDriver driver) {
			super();
			//userHomePagePOJO = new UserHomePagePOJO(driver);
			homePagePOJO = new HomePagePOJO(driver);
		}
		
		public String findUserName(){
			String userName = "";
			try{
				userName = homePagePOJO.findUserName().getText();
			}catch(NoSuchElementException e){
				System.out.println("Login Failed");
			}
			return userName;
		}
		
		public void searchProduct(String productName){
			homePagePOJO.setSearchBox(productName);
			homePagePOJO.clickSearchButton();
		}
}
