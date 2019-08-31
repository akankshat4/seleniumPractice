package com.practice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.practice.functions.HomePageFunctions;
import com.practice.functions.LoginPageFunctions;
import com.practice.listener.EventListener;
import com.practice.utility.EmailUtility;
import com.practice.utility.FileOperationsUtility;
import com.practice.utility.LoggerUtility;

public class WebDriverInstantiation {
	protected static Properties properties;
	private WebDriver webDriver;
	protected static FileOperationsUtility utility ;
	protected LoginPageFunctions loginFunctions;
	protected HomePageFunctions homeFunctions;
	protected EventFiringWebDriver driver;
	protected EventListener eventHandler;
	protected static ExtentTest extentTest;
	protected static ExtentReports extentReports;
	protected static ExtentHtmlReporter extentHtmlReporter;
	protected static LoggerUtility loggerUtility;
	protected  Logger log = Logger.getLogger("devpinoyLogger");
	protected EmailUtility emailUtility;
	
	@BeforeSuite
	public void setProperties(){
		loggerUtility = new LoggerUtility();
		try {
			FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
			System.out.println(properties.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void startReport(){
		String path = System.getProperty("user.dir")+"\\test-output\\extentReports.html";
		System.out.println(path);
		extentHtmlReporter = new ExtentHtmlReporter(path);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		extentHtmlReporter.config().setDocumentTitle("Extent Report Demo");
		extentHtmlReporter.config().setReportName("Test Report");
		extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
		extentHtmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		
	}
	
	@BeforeMethod
	public void instantiateDriver(ITestContext context){
		//String path = properties.getProperty("chromeDriverPath");
		String path = "D:\\Selenium\\JAR\\chromedriver_win32 (4)\\chromedriver.exe";
		System.out.println(properties.toString());
		String url = properties.getProperty("url");
		System.setProperty("webdriver.chrome.driver",path);
		webDriver = new ChromeDriver();
		driver = new EventFiringWebDriver(webDriver);
		eventHandler = new EventListener();
		driver.register(eventHandler);
		context.setAttribute("driver", driver);
		driver.get(url);
		initializeObjects();
	}
	
	public void initializeObjects(){
		loginFunctions = new LoginPageFunctions(driver);
		homeFunctions = new HomePageFunctions(driver);
	}
	
	 @AfterMethod
	    public void getResult(ITestResult result) {
	        if(result.getStatus() == ITestResult.FAILURE) {
	            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
	            extentTest.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS) {
	        	extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
	        }
	        else {
	        	extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
	        	extentTest.skip(result.getThrowable());
	        }
	    }
	  @AfterMethod
	  public void teardown(){
		  driver.quit();
	  }
	  
	  @AfterSuite
	  public void publishAutomationTestRunReport(){
		  emailUtility = new EmailUtility();
		  emailUtility.sendEmail();
	  }
}
