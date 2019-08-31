package com.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPOJO {
		private EventFiringWebDriver driver;
		private By label = By.xpath("//span[@class = '_2yAnYN']");
		private By productName = By.xpath("//*[@id='container']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div/span/span");
		
		public SearchResultsPOJO(EventFiringWebDriver driver) {
			super();
			this.driver = driver;
		}

		public WebElement findLabel(){
			WebDriverWait wait = new WebDriverWait(driver, 10);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(label));
		}
		
		public WebElement findProductName(){
			WebDriverWait wait = new WebDriverWait(driver, 10);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
		}
}
