package com.practice.testsuite;

import java.util.NoSuchElementException;

import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.practice.base.WebDriverInstantiation;
import com.practice.utility.FileOperationsUtility;


@Listeners(com.practice.listener.CaptureEvidences.class)
public class LoginPageValidation extends WebDriverInstantiation{

@Test(dataProvider="userDetails")
public void validateLogin(String email, String password,String userName, String testcaseName, String status, ITestContext context) {
	log.info("validateLogin"); 
	extentTest = extentReports.createTest("validateLogin_"+testcaseName);
	context.setAttribute("tcName", testcaseName);
	loginFunctions.login(email,password);
	String actualUserName =null;
	actualUserName = homeFunctions.findUserName();
	if (actualUserName.equals(userName)){
		 utility.markStatus(testcaseName, "Pass");
	}
	else {
	 utility.markStatus(testcaseName, "Fail");
	 AssertJUnit.assertEquals(actualUserName, userName);
	}
}
  @DataProvider(name="userDetails")
  public Object[][] getUserDetailsFromDataProvider(){
	  String [][]userDetails = null;
	  String path = properties.getProperty("loginCredentials");
	  utility = new FileOperationsUtility(path, "loginDetails");
	  userDetails = utility.getUserDetailsFromExcel();
	  return userDetails;
  }
}
