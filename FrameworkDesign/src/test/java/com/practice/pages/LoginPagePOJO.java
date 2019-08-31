package com.practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPagePOJO {
	private EventFiringWebDriver driver;
	/*private By email = By.xpath("//input[@class = '_2zrpKA _1dBPDZ']");
	private By password = By.xpath("//input[@class = '_2zrpKA _3v41xv _1dBPDZ']");
	private By loginButton = By.xpath("//button[@class = '_2AkmmA _1LctnI _7UHT_c']");*/
	
	//@FindBy(xpath="//input[@class = '_2zrpKA _1dBPDZ']")
	@FindBy(xpath="//input[@class = '_2zrpKA _1dBPDZ']")
	WebElement email;
	
	@FindBy(xpath="//input[@class = '_2zrpKA _3v41xv _1dBPDZ']")
	WebElement password;
	
	@FindBy(xpath="//button[@class = '_2AkmmA _1LctnI _7UHT_c']")
	WebElement loginButton;
	
	@FindBy (xpath="//button[@class='_2AkmmA _29YdH8']")
	WebElement cancelButton;
	
	public LoginPagePOJO(EventFiringWebDriver driver1) {
		super();
		this.driver = driver1;
		PageFactory.initElements(driver, this);
	}

	public void setEmail(String emailAddress){
		email.sendKeys(emailAddress);
	}
	
	public void setPassword(String pwd){
		password.sendKeys(pwd);
	}
	
	public void clickLoginButton(){
		loginButton.submit();
	}
	
	public void clickCancelButton(){
		cancelButton.click();
	}
}
