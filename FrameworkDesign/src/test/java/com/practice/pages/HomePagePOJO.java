package com.practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePagePOJO {
	private EventFiringWebDriver driver;
	/*private By userName = By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div");
	private By searchBox = By.xpath("//input[@class = 'LM6RPg']");
	private By searchButton = By.xpath("//button[@class = 'vh79eN']");*/
	
	@FindBy(xpath="//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div")
	//@FindBy(xpath="//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div[1]/div/div")
	public WebElement userNameOnLogin;

	@FindBy(xpath="//input[@class = 'LM6RPg']")
	public WebElement searchBox;
	
	@FindBy(xpath="//button[@class = 'vh79eN']")
	public WebElement searchButton;
	
	public HomePagePOJO(EventFiringWebDriver driver1) {
		super();
		this.driver = driver1;
		PageFactory.initElements(driver, this);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public WebElement findUserName(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOf(userNameOnLogin));
	}
		
	public void setSearchBox(String searchString){
		searchBox.sendKeys(searchString);
	}
	
	public void clickSearchButton(){
		searchButton.click();
	}
}
