package com.practice.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CaptureEvidences implements ITestListener {
	private EventFiringWebDriver driver;
	private String fileLocationInProperties;
	private String destnFileWithPath;
	private String testCaseName;

	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		ITestContext context = result.getTestContext();
		getContextAttributes(context);
		fileLocationInProperties = this.readProperty("failEvidence");	
		setFileProperties(result);
		captureScreenshot();

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		// TODO Auto-generated method stub
		ITestContext context = result.getTestContext();
		getContextAttributes(context);
		fileLocationInProperties = this.readProperty("passEvidence");	
		System.out.println(fileLocationInProperties);
		setFileProperties(result);
		captureScreenshot();
	}
	
	public void setFileProperties(ITestResult result){
		String methodName = result.getInstanceName();
		String testEvidenceFileName = methodName+"_"+testCaseName+".jpg";
		destnFileWithPath =  fileLocationInProperties+"\\"+testEvidenceFileName;
		System.out.println(destnFileWithPath);
	}

	public String readProperty(String propertyName){
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/config.properties");
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties.getProperty(propertyName);
	}
	
	public void getContextAttributes(ITestContext context){
		driver = (EventFiringWebDriver) context.getAttribute("driver");
		testCaseName = (String) context.getAttribute("tcName");
		//System.out.println("Context attribute : "+testCaseName);
	}
	
	public void captureScreenshot(){
		System.out.println("captureScreenshot");
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destnFile = new File(destnFileWithPath);
		try {
			FileUtils.copyFile(srcFile, destnFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
